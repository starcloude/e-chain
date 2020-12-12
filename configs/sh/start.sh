#!/bin/bash

work_path=$(dirname $0)

APP_NAME=$work_path/echain-web.jar
LOG_FILE=$work_path/logback-spring.xml
LOG_PATH=/data/logs/api/catalina.out

#echo $APP_NAME "-" $LOG_FILE

nohup java -jar $APP_NAME --logging.config=$LOG_FILE --server.port=8010 >> $LOG_PATH 2>&1 &


