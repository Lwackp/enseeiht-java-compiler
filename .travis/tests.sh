#!/bin/bash

set -e

# Tests typing
echo Tests typing
for file in tests/*.ast; do
    echo $(tail -n 1 $file) for $file; \
    [ "$(tail -n 1 $file)" == "Typing: OK" ]
done

# Running all assembly files
echo Running all assembly files
for file in tests/*.tamo; do
    echo Running assembly $file; \
    java -jar tammachine.jar $file > ${file%.*}.res
done

# Comparing results
echo Comparing results
for file in tests/*.out; do
    echo Checking result for $file; \
    diff <(sed -e '$a\' $file) <(sed -e '$a\' ${file%.*}.res)
done
