#!/bin/bash
function okOut {
    if [ $? = 0 ] ; then
        echo OK
    else
        echo FAIL
    fi
}

for f in tests/*.ast; do
    file=${f%.*}; \
    echo "Test $(basename ${file}): "; \
    if [[ $(basename ${file}) != *_bad ]] ; then
        printf " - Typing = "; \
        [ "$(tail -n 1 ${file}.ast)" == "Typing: OK" ]; okOut; \
        printf " - Running assembly = "; \
        java -jar tammachine.jar ${file}.tamo > ${file}.res 2> /dev/null; okOut; \
        printf " - Comparing output = "; \
        diff <(sed -e '$a\' ${file}.res) <(sed -e '$a\' ${file}.out); okOut
    else
        printf " - Bad Typing = "; \
        [ "$(tail -n 1 ${file}.ast)" == "Typing: ERROR" ]; okOut
    fi
    echo ''
done
