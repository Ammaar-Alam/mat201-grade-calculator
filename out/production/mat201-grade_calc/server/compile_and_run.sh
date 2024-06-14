#!/bin/bash

# Navigate to the directory where this script is located
cd "$(dirname "$0")"

# Set the classpath to include the required libraries
LIBS_PATH="libs/algs4.jar"

# Compile the Java files
javac -cp "$LIBS_PATH" server/*.java

# Check if compilation was successful
if [ $? -ne 0 ]; then
  echo "Compilation failed"
  exit 1
fi

# Run the main class
java -cp ".:$LIBS_PATH:server" server.GradeCalculator $1 $2 $3
