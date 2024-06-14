#!/bin/bash

CATEGORY=$1
DAYNUMBER=$2
EARNEDPOINTS=$3

javac -cp "libs/algs4.jar:libs/*" server/*.java
java -cp "libs/algs4.jar:server:libs/*" server.GradeCalculator $CATEGORY $DAYNUMBER $EARNEDPOINTS
