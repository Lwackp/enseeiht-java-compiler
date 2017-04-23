#!/bin/bash

set -e

# Building .bloc test files
make tests
echo ''

# Building assembly files
for file in tests/*.tam; do
    echo "Assembling ${file}"
    java -jar aspartam.jar ${file} 2> /dev/null
done
