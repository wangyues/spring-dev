#!/bin/sh

process=`ps aux | grep dms-agent | grep -v grep`

echo "***********************************"
echo $process
echo "***********************************"

if [[ -n $process ]]; then
    ps aux | grep java | grep dms-agent | grep -v grep | awk '{print $2}' | xargs kill
fi

echo "success"