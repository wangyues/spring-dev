#!/bin/sh

#check JAVA_HOME & java
noJavaHome=false
if [ -z "$JAVA_HOME" ] ; then
    JAVA_HOME=/var/lib/jdk1.8.0_162
fi
if [ ! -e "$JAVA_HOME/bin/java" ] ; then
    noJavaHome=true
fi
if $noJavaHome ; then
    echo
    echo "Error: JAVA_HOME environment variable is not set."
    echo
    exit 1
fi
#==============================================================================
#set JAVA_OPTS
#GC线程设置为2个
JAVA_OPTS="-server -Xss256K -Xms64M -Xmx64M -XX:SurvivorRatio=4 -XX:+AggressiveOpts -XX:ParallelGCThreads=2"

#set HOME
CURR_DIR=`pwd`
cd `dirname "$0"`/..
SPRING_HOME=`pwd`

if [ -z "$SPRING_HOME" ] ; then
    echo
    echo "Error: SPRING_HOME environment variable is not defined correctly."
    echo
    exit 1
fi
#==============================================================================

#set CLASSPATH
MYCAT_CLASSPATH="$SPRING_HOME/conf:$SPRING_HOME/lib/classes"
for i in "$SPRING_HOME"/lib/*.jar
do
    MYCAT_CLASSPATH="$MYCAT_CLASSPATH:$i"
done
#==============================================================================
#java.compiler=NONE关闭了JIT
#startup Server
RUN_CMD="\"$JAVA_HOME/bin/java\""
RUN_CMD="$RUN_CMD -DSPRING_HOME=\"$SPRING_HOME\""
RUN_CMD="$RUN_CMD -Djava.compiler=NONE"
RUN_CMD="$RUN_CMD -classpath \"$MYCAT_CLASSPATH\""
RUN_CMD="$RUN_CMD $JAVA_OPTS"
RUN_CMD="$RUN_CMD com.cisco.Application $@"
RUN_CMD="$RUN_CMD >> \"$SPRING_HOME/logs/console.log\" 2>&1 &"
echo $RUN_CMD
eval $RUN_CMD
#==============================================================================
