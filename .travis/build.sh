#!/bin/bash

# Building .bloc test files
make tests
echo ''

e=0;

# Building assembly files
for file in tests/*.tam; do
    printf "Assembling ${file}: "
    java -jar aspartam.jar ${file} 2> /dev/null
    le=$?
    if [ $le -ne 0 ]; then
        echo FAIL
        e=$le
    else
        echo OK
    fi
done

exit $e
