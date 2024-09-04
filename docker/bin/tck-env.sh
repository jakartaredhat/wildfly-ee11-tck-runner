#! /bin/bash

set -e

createDir() {
    if [ ! -d "${1}" ]; then
        mkdir -p "${TS_FOLDER}"
    fi
}

cd "${HOME}"

# Set the default directory for the base TCK home
createDir "${TS_FOLDER}"
pushd "${TS_FOLDER}"
if [ ! -d "cts-10-mods" ]; then
    git -c http.sslVerify=false clone https://gitlab.cee.redhat.com/j2eects/cts-10-mods.git
fi
cd "cts-10-mods"
# Ignore SSL verification
git config --local http.sslVerify false
popd

createDir "${TCK_LOG_DIR}"

# Clone the EE 11 TCK Runner repository
if [ ! -d "wildfly-ee11-tck-runner" ]; then
    git clone https://github.com/jakartaredhat/wildfly-ee11-tck-runner.git
fi

if [ ! -d "platform-tck" ]; then
    git clone https://github.com/jakartaee/platform-tck.git
fi

if [ ! -d "jakartaee-tck-tools" ]; then
    git clone https://github.com/eclipse-ee4j/jakartaee-tck-tools.git
fi

TCK_VERSION="10.0.5"
[ ! -d "jakartaeetck" ] && wget https://download.eclipse.org/ee4j/jakartaee-tck/jakartaee10/staged/eftl/jakarta-jakartaeetck-${TCK_VERSION}.zip && unzip jakarta-jakartaeetck-${TCK_VERSION}.zip

# Set the RI
GLASSFISH_VERSION="7.0.4"
[ ! -d "glassfish7" ] && wget https://download.eclipse.org/ee4j/glassfish/glassfish-${GLASSFISH_VERSION}.zip &&  unzip glassfish-${GLASSFISH_VERSION}.zip
