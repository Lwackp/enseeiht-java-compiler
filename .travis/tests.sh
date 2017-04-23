#!/bin/bash

allTests=0

function okOut {
    if [ $? = 0 ] ; then
        echo OK
    else
        allTests=1
        echo FAIL
    fi
}

for f in tests/*.ast; do
    file=${f%.*}
    echo "Test $(basename ${file}): "
    if [[ $(basename ${file}) != *_bad ]] ; then
        printf " - Format = "
        [ "$(tail -n 2 ${file}.ast | head -n 1)" == "Format: OK" ]; okOut
        printf " - Typing = "
        [ "$(tail -n 1 ${file}.ast)" == "Typing: OK" ]; okOut
        printf " - Running assembly = "
        java -jar tammachine.jar ${file}.tamo > ${file}.res 2> /dev/null; okOut
        printf " - Comparing output = "
        diff <(sed -e '$a\' ${file}.res) <(sed -e '$a\' ${file}.out); okOut
    else
        printf " - Failing = "
        [ "$(tail -n 2 ${file}.ast | head -n 1 | tail -c 3 | head -c 2)" != "$(tail -n 1 ${file}.ast | tail -c 3 | head -c 2)" ]; \
        okOut
    fi
    echo ''
done

printf "Tests: "
[ ${allTests} = 0 ]; okOut

exit ${allTests}
