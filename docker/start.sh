#! /bin/bash
if [ -z "${CONTAINER_ENGINE}" ]; then
    CONTAINER_ENGINE=docker
fi

fail() {
    echo "${1}"
    printHelp
    exit 1
}

printArgHelp() {
    echo -e "${1}\t${2}"
}

printHelp() {
    echo "Start ${CONTAINER_ENGINE} with the Jakarta EE 11 TCK ready for testing."
    echo "Usage: ${0##*/} ~/projects/wildfly/wildfly/dist/target/wildfly-\${VERSION}.zip"
}

WILDFLY_ZIP="${1}"
if [ -f "${WILDFLY_ZIP}" ]; then
    WILDFLY_ZIP="$(readlink -m "${WILDFLY_ZIP}")"
else
    fail "The path to a WildFly ZIP file is required."
fi
shift

# If the second argument is a number the Java version is being specified

CONTAINER_NAME="wildfly-jakarta-tck-11-container"
IMAGE_NAME="wildfly-jakarta-tck-11-image"

# Check if the container exists, if not build it
if ! ${CONTAINER_ENGINE} images | grep -q "${IMAGE_NAME}"
then
    echo "Could not find container ${IMAGE_NAME}, building image."
    ./build.sh "${IMAGE_NAME}"
fi
if [ "$(${CONTAINER_ENGINE} ps -qa -f name="${CONTAINER_NAME}")" ]; then
    # Check if the container has already been started
    if [ "$(${CONTAINER_ENGINE} ps -q -f name="${CONTAINER_NAME}")" ]; then
        ${CONTAINER_ENGINE} stop "${CONTAINER_NAME}"
    fi
    # Delete the container
    ${CONTAINER_ENGINE} rm "${CONTAINER_NAME}" &> /dev/null
fi

${CONTAINER_ENGINE} run -u jboss:jboss --hostname=localhost --name "${CONTAINER_NAME}" \
    -v "${WILDFLY_ZIP}":/home/jboss/wildfly.zip:Z "${CONFIG_ARG[@]}" "${APPCLIENT_CONFIG_ARG[@]}" \
    ${DOCKER_ARGS} -it "${IMAGE_NAME}" "$@"
