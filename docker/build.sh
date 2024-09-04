#! /bin/bash
if [ -z "${CONTAINER_ENGINE}" ]; then
    CONTAINER_ENGINE=docker
fi

if [ -z "${1}" ]; then
    IMAGE_NAME="wildfly-jakarta-tck-11-image"
else
    IMAGE_NAME="${1}"
fi

${CONTAINER_ENGINE} build -t "${IMAGE_NAME}" .
