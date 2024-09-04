#!/bin/bash

setJavaHome() {
    if [ ! -d "${1}" ]; then
        echo "Invalid JAVA_HOME: ${1}"
        return "${err_status}"
    fi
    JAVA_HOME="${1}"
    export JAVA_HOME
}

setJavaOpts() {
    if [ -n "${1}" ]; then
        JAVA_OPTS="${1} ${JAVA_OPTS}"
        # Trim leading spaces
        JAVA_OPTS="${JAVA_OPTS##*( )}"
        # Trim trailing spaces
        JAVA_OPTS="${JAVA_OPTS%%*( )}"
        export JAVA_OPTS
    fi
}

reset() {
    unset JAVA_HOME
    unset JAVA_OPTS
    setJavaOpts "${DEFAULT_JAVA_OPTS}"
}

# Set the default path
DEFAULT_PATH="${DEFAULT_PATH}"
OPENJDK_PATH="/usr/lib/jvm/"
DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -enableassertions"
verbose=false
err_status=128
jdkVersion=
JDK_PATH=

if [ -z "${DEFAULT_PATH}" ]; then
    DEFAULT_PATH="${PATH}"
    export DEFAULT_PATH
fi

while [ "$#" -gt 0 ]
do
    case "${1}" in
        --debug)
            if [ -n "${2}" ] && [ "${2}" = "clear" ]; then
                unset JAVA_OPTS
                setJavaOpts "${DEFAULT_JAVA_OPTS}"
                shift
            else
                setJavaOpts "${DEBUG_OPTS}"
            fi
            ;;
        -v|--verbose)
            verbose=true
            ;;
        clear|reset|-c)
            reset
            ;;
        *)
            JDK_PATH="${1}"
            re='^[0-9\.]+$'
            if [[ ${JDK_PATH} =~ ${re} ]]; then
                jdkVersion=${1}
            fi
            ;;
    esac
    shift
done

if [ -n "${JDK_PATH}" ]; then
    if [ -n "${jdkVersion}" ]; then        
        JDK_PATH="${OPENJDK_PATH}/java-${jdkVersion}"
    fi
    setJavaHome "$(readlink -m "${JDK_PATH}")"
fi

PATH="${JAVA_HOME}/bin:${DEFAULT_PATH}"
# Trim the leading space
PATH="${PATH##*( )}"
# Trim the trailing space
PATH="${PATH%%*( )}"
export PATH

if [ ${verbose} = true ]; then
    echo "PATH:         ${PATH}"
    echo 
    echo "JAVA_OPTS:    ${JAVA_OPTS}"
    java -version
fi
