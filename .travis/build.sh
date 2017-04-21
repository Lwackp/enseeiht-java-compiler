#!/bin/bash

set -e

# Building .bloc test files
make tests

# Building assembly files
for file in tests/*.tam; do
    java -jar aspartam.jar $file
done
