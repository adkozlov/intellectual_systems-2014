#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Usage: build grammar for lab. Pass lab number."
    exit 1
fi

LAB="lab_"$(printf "%02d" $1)

SRC_DIR="src"
LIB_DIR="lib"

SCRIPT_PATH=$(pwd)/$(dirname $0)
cd ${SCRIPT_PATH}/../${SRC_DIR}/${LAB}

ANTLR_EXT=".g4"

for i in ./*${ANTLR_EXT}
do
    name=$(basename ${i} ${ANTLR_EXT})
    rm -f ${name}*.tokens ${name}*.java
    java -jar ../../${LIB_DIR}/antlr-4.2-complete.jar ${name}${ANTLR_EXT}
done