#!/usr/bin/env bash

# wildflytckroot
# wildflyroot

runnerDir=$(dirname $0)

# Setup script for the project. By default this creates a ../wildflytckroot directory
# and populates with the necessary dependencies to run the tests. To override that
# location set wildflytckroot environment variable to the desired location.
if [ -z "$wildflytckroot" ]; then
  mkdir -p ../wildflytckroot
  export wildflytckroot=${PWD}/../wildflytckroot
fi

cd $wildflytckroot
echo "Using $wildflytckroot as the root directory for the WildFly EE 11 TCK"
if [ -d "platform-tck" ]; then
  echo "platform-tck directory already exists, skipping clone"
else
  echo "cloning and building EE11 TCK repository"
  git clone https://github.com/jakartaee/platform-tck.git
  cd platform-tck
  mvn -Pstaging install
fi

cd $wildflytckroot
if [ -d "jakartaee-tck-tools" ]; then
  echo "jakartaee-tck-tools directory already exists, skipping clone"
else
  echo "cloning and building jakartaee-tck-tools repository"
  git clone https://github.com/eclipse-ee4j/jakartaee-tck-tools.git
  cd jakartaee-tck-tools/arquillian
  mvn install
fi

cd $wildflytckroot
if [ -d "scripts" ]; then
  echo "scripts directory already exists, skipping clone"
else
  echo "cloning the JBoss CTS scripts repository, needs VPN access"
  git clone https://gitlab.cee.redhat.com/j2eects/scripts.git
fi

echo "Running the tck10.sh script with dummy testFolder to init the environment"
export testFolder=/dev/null
bash $wildflytckroot/scripts/tck10.sh 2>&1 | tee tck10.log

echo "Installing the standalone-full.xml configuration"
wfdir=`ls -d $wildflytckroot/wildfly/dist/target/wildfly*`
cp $runnerDir/src/test/resources/standalone-full.xml $wfdir/standalone/configuration/
