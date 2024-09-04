#! /bin/bash

set -e

# Global variables
javaVersion=""
verbose=false

remote="origin"
branch="main"
doReset=false
profile="full"

# Functions

startDerby() {
    # Start JavaDB
    pushd "${DERBY_HOME}/bin"
    source ./setNetworkServerCP
    nohup ./startNetworkServer -noSecurityManager > "${TCK_LOG_DIR}/derby.log" 2> "${TCK_LOG_DIR}/derby.err" < /dev/null &
    popd
}

fail() {
    echo "${1}"
    exit 1
}

gitReset() {
    git fetch "${remote}"
    git checkout main
    git clean -f -d
    git reset --hard "${remote}"/"${branch}"
}

# Parse incoming parameters
while getopts ":j:rp:v" opt; do
    case "${opt}" in
        j)
            javaVersion="${OPTARG}"
            ;;
        p)
            profile="${OPTARG}"
            ;;
        r)
            doReset=true
            ;;
        v)
            verbose=true
            ;;
        \?)
            echo "Invalid option: -${OPTARG}" >&2
            printHelp
            exit 1
            ;;
        :)
            echo "Option -${OPTARG} requires an argument" >&2
            exit 1
            ;;
    esac
done

shift $((OPTIND - 1))

if [ -n "${javaVersion}" ]; then
    if [ ${verbose} = true ]; then
        . javaenv.sh -v "${javaVersion}"
    else
        . javaenv.sh "${javaVersion}"
    fi
fi

ANT_ARGS=""
if [ ${verbose} = true ]; then
    ANT_ARGS="-v"
fi

. tck-shutdown.sh

if [ ${doReset} == true ]; then
    echo "Resetting ${JEETCK_MODS}..."
    pushd "${JEETCK_MODS}"
    gitReset
    popd
fi

WILDFLY_ZIP="${HOME}/wildfly.zip"
if [ ! -f "${WILDFLY_ZIP}" ]; then
    fail "The WildFly zip ${WILDFLY_ZIP} does not exist"
fi

if [ ! -d "${TCK_LOG_DIR}" ]; then
    mkdir -p "${TCK_LOG_DIR}"
fi

# Unzip the server
unzip -o -q -d "${TS_FOLDER}/tmp" "${WILDFLY_ZIP}"

# WildFly Home
rm -rf "${JBOSS_HOME}"

if [ -d "${TS_FOLDER}/tmp/jboss-eap-8.0" ]; then
    mv "${TS_FOLDER}/tmp/jboss-eap-8.0" "${JBOSS_HOME}/"
else 
    mv "${TS_FOLDER}/tmp/"wildfly* "${JBOSS_HOME}/"
fi

cd "${JEETCK_MODS}"

# Delete the ant.properties if it exists
antProperties="${JEETCK_MODS}/ant.properties"
if [ -f "${antProperties}" ]; then
    rm "${antProperties}"
fi
echo "jeetck.mods=${JEETCK_MODS}
jboss.home=${JBOSS_HOME}
ts.home=${TS_HOME}
javaee.home=${JAVAEE_HOME}
javaee.home.ri=${JAVAEE_HOME_RI}
derby.home=${DERBY_HOME}
" >> "${antProperties}"

cat "${antProperties}"

# Copy the jca-tck-properties.txt
cp -v "${JEETCK_MODS}/etc/jca-tck-properties.txt" "${JBOSS_HOME}/bin"

ant ${ANT_ARGS} clean
ant ${ANT_ARGS} -Dprofile="${profile}"

startDerby

# Initialize the Tables needed for the CTS Run
cd "${TS_HOME}/bin"
CTS_DB=javadb
ant ${ANT_ARGS} init.${CTS_DB}
ant ${ANT_ARGS} config.vi -Dcts.db=${CTS_DB}

# Create the CTS Config
cd "${TS_HOME}/bin"
ant ${ANT_ARGS} config.ri
ant ${ANT_ARGS} update.jaxrs.wars
#tck-start-wildfly &
#sleep 5
#ant build.special.webservices.clients -Dbuild.vi=true

ant ${ANT_ARGS} -Dbuild.vi=true tsharness

# Copy the security files
mkdir -p "${JBOSS_HOME}/standalone/configuration/security"
cp "${JEETCK_MODS}"/etc/security/*.properties "${JBOSS_HOME}/standalone/configuration/security"

# Check if we need to add YAML support
YAML_SERVICE_DIR="${JBOSS_HOME}/modules/system/layers/base/org/jboss/as/controller/main/dir/META-INF/services/"
if [ ! -d "${YAML_SERVICE_DIR}" ]; then
    mkdir -p "${YAML_SERVICE_DIR}"
    echo 'org.jboss.as.controller.persistence.yaml.YamlConfigurationExtension' > "${YAML_SERVICE_DIR}/org.jboss.as.controller.persistence.ConfigurationExtension"
fi
#echo "Starting $(basename "${JBOSS_HOME}")"
#cd "${JBOSS_HOME}/bin"
#"./standalone.sh" -c "standalone-full.xml" "-y=${JEETCK_MODS}/etc/wildfly-tck.yml:${JEETCK_MODS}/etc/derby.yml" > "${TCK_LOG_DIR}/server.out" 2>&1 &

# Build current dependent projects
cd "${HOME}"
if [ ! -d "platform-tck" ]; then
    git clone https://github.com/jakartaee/platform-tck.git
fi

pushd platform-tck
curBranch=$(git symbolic-ref --short -q HEAD)
# This project seems to require Java 17
. javaenv.sh -v 17
git fetch origin
git reset --hard origin/"${curBranch}"
mvn -Pstaging install
popd

if [ ! -d "jakartaee-tck-tools" ]; then
    git clone https://github.com/eclipse-ee4j/jakartaee-tck-tools.git
fi
pushd jakartaee-tck-tools
curBranch=$(git symbolic-ref --short -q HEAD)
git fetch origin
git reset --hard origin/"${curBranch}"
cd arquillian
mvn clean install
popd

# Reset the Java version
if [ -n "${javaVersion}" ]; then
    if [ ${verbose} = true ]; then
        . javaenv.sh -v "${javaVersion}"
    else
        . javaenv.sh "${javaVersion}"
    fi
fi

# Fetch the latest runner and reset
cd "${TCK_RUNNER}"
git fetch origin
git reset --hard origin/main
cp "src/test/resources/standalone-full.xml" "${JBOSS_HOME}/standalone/configuration/"
cp "lib/tsharness.jar" "${JBOSS_HOME}/modules/system/layers/base/com/sun/ts/main"
cp "modules/com.sun.ts-module.xml" "${JBOSS_HOME}/modules/system/layers/base/com/sun/ts/main/module.xml"

echo ""
echo "JAVA_HOME:      ${JAVA_HOME}"
echo "TS_HOME:        ${TS_HOME}"
echo "TCK_RUNNER:     ${TCK_RUNNER}"
echo "JEETCK_MODS     ${JEETCK_MODS}"
echo "DERBY_HOME:     ${DERBY_HOME}"
echo "JBOSS_HOME:     ${JBOSS_HOME}"
echo "JAVAEE_HOME:    ${JAVAEE_HOME}"
echo "JAVAEE_HOME_RI: ${JAVAEE_HOME_RI}"

echo ""

#echo "Example command: ant -Dkeywords=\"javaee\" runclient"
#echo "                 ant runclient -Dtest=testName_from_<transport> -Dtest.client=<ClassName|Client>"

"$@"
