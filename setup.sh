#!/usr/bin/env bash

# Setup script for the project. By default this creates a ../wildflytckroot directory
# and populates with the necessary dependencies to run the tests. To override that
# location set wildflytckroot environment variable to the desired location.
if [ -z "$wildflytckroot" ]; then
  mkdir -p ../wildflytckroot
  export wildflytckroot=../wildflytckroot
fi

cd $wildflytckroot
echo "Using $wildflytckroot as the root directory for the WildFly EE 11 TCK"
echo "cloning and building EE11 TCK repository"
git clone https://github.com/jakartaee/platform-tck.git
cd platform-tck
mvn -Pstaging install

echo "cloning and building jakartaee-tck-tools repository"
cd $wildflytckroot
git clone https://github.com/eclipse-ee4j/jakartaee-tck-tools.git
cd jakartaee-tck-tools
cd arquillian
mvn install

echo "cloning the JBoss CTS scripts repository, needs VPN access"
cd $wildflytckroot
git clone https://gitlab.cee.redhat.com/j2eects/scripts.git
PATH=$PATH:$wildflytckroot/scripts
export testFolder=/dev/null
echo "Running the tcl10.sh script with dummy testFoler to init the environment"
bash tck10.sh 2>&1 | tee tck10.log





