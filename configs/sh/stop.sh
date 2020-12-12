#!/bin/bash

APP_NAME=echain-web.jar

PROCESS=`ps -ef|grep $APP_NAME|grep -v grep|grep -v PPID|awk '{ print $2}'`
for i in $PROCESS
do
  echo "Kill the $1 process [ $i ]"
  kill -9 $i
done


