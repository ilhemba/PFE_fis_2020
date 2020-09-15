#!/bin/sh 
c=`ls ./log`
if [[ "$c" != "" ]]
then
rm ./log/*
fi
