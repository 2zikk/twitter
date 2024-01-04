#!/bin/bash

# DB 의존성을 감안하여 테스트 없이 빌드합니다
./gradlew build -x test

# 로컬머신 환경에 따라 docker-compose를 실행합니다
MACHINE_TYPE=$(uname -m)
if [ "$MACHINE_TYPE" = "x86_64" ]; then
    echo "Detected Intel machine."
    docker-compose -f docker-compose-intel.yml up -d
elif [ "$MACHINE_TYPE" = "arm64" ]; then
    echo "Detected Arm machine."
    docker-compose up -d
else
    echo "Unknown machine type: $MACHINE_TYPE. Exiting..."
    exit 1
fi

# 테스트를 실행합니다
./gradlew test
