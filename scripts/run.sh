#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Usage: compile and start lab. Pass lab number."
    exit 1
fi

LAB="lab_"$(printf "%02d" $1)
COMMON="common"

BIN_DIR="bin"
SRC_DIR="src"
LIB_DIR="lib"

SCRIPT_PATH=$(pwd)/$(dirname $0)
cd ${SCRIPT_PATH}/..

LIBS=""
for i in ${LIB_DIR}/*.jar
do
    if [ "$LIBS" != "" ]; then
        LIBS=${LIBS}:
    fi

    LIBS=${LIBS}${i}
done

mkdir -p ${BIN_DIR}/${LAB}
mkdir -p ${BIN_DIR}/${COMMON}

JAVA_EXT=".java"

COMPILATION="compilation"
echo "$COMPILATION started"
javac -classpath ${LIBS} -sourcepath ./${SRC_DIR} -d ${BIN_DIR} ${SRC_DIR}/${COMMON}/*${JAVA_EXT} ${SRC_DIR}/${LAB}/*${JAVA_EXT}
echo "$COMPILATION finished"

echo "runned..."
java -classpath ${LIBS}:./${BIN_DIR} ${LAB}.Main

rm -r ${BIN_DIR}