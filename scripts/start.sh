#!/bin/bash
REPOSITORY="/home/ubuntu/app"
echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> $JAR_NAME 에 실행 권한 부여 및 구동"
chmod +x $JAR_NAME
nohup java -jar $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &