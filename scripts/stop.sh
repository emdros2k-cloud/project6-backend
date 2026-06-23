#!/bin/bash
CURRENT_PID=$(pgrep -f book-backend) # 본인의 jar 파일명이나 프로젝트 키워드
if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi