#!/bin/sh

process=`ps aux | grep spring-dev | grep -v grep`

echo "***********************************"
echo $process
echo "***********************************"

if [[ -n $process ]]; then
    ps aux | grep java | grep spring-dev | grep -v grep | awk '{print $2}' | xargs kill
fi

echo "success"