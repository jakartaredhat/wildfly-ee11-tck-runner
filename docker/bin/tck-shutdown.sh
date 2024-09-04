#! /bin/bash

kill_glassfish() {
 (
    ps -eaf --columns 20000 | grep com.sun.enterprise.admin.server.core.jmx.AppServerMBeanServerBuilder | grep -v grep | awk '{ print $2; }' | xargs kill -9 &> /dev/null
    jps -l | grep com.sun.enterprise.glassfish.bootstrap.ASMain | grep -v grep | awk '{ print $1; }' | xargs kill -9 &> /dev/null
 ) || return 0
}

shutdown_wildfly() {
    running=$(jps | awk '{if ($2 == "jboss-modules.jar") { print "true" };}')
    if [ "$running" == "true" ]; then
        echo "Shutting down WildFly"
        cd "$JBOSS_HOME/bin"
        ./jboss-cli.sh -c ":shutdown"
    fi
}

shutdown_derby() {    
    running=$(jps | awk '{if ($2 == "NetworkServerControl") { print "true" };}')
    if [ "$running" == "true" ]; then
        echo "Shutting down derby"
        cd "$DERBY_HOME/bin"
        ./stopNetworkServer
    fi
}

shutdown_all() {
    kill_glassfish
    shutdown_derby
    shutdown_wildfly
}

shutdown_all