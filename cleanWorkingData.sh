#!/bin/sh 
c=`ls . | grep "WorkingData1"`
s=`ls . | grep "WorkingData2"`
if [[ "$c" == "WorkingData1" ]]
then
rm -r $c
else if [[ "$s" == "WorkingData2" ]]
then
rm -r $s
fi
fi
