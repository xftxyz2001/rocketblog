#!/bin/bash
PID=$(ps -ef | grep rocketblog-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo Application is already stopped
else
    echo kill -9 $PID
    kill -9 $PID
fi

nohup java -jar rocketblog/target/rocketblog-0.0.1-SNAPSHOT.jar >> rocketblog.log &
# tail -f rocketblog.log
