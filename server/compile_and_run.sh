#!/bin/bash
javac -cp "libs/*" server/GradeCalculator.java
java -cp .:libs/* server.GradeCalculator "$1" "$2" "$3" 2>&1 | tee -a server.log


# Paths to the Java source files
SRC_DIR="server"
SRC_FILES="GradeCalculator.java LectureInfo.java PsetInfo.java"

# Path to the algs4.jar file (assuming it's in the server/libs directory)
ALGS4_JAR_PATH="server/libs/algs4.jar"

# Compile the Java files with the algs4.jar in the classpath
javac -cp .:${ALGS4_JAR_PATH} ${SRC_DIR}/*.java

# Run the GradeCalculator class with the algs4.jar in the classpath
java -cp .:${ALGS4_JAR_PATH} ${SRC_DIR}.GradeCalculator
