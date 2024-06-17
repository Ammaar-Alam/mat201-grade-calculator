#!/bin/bash

# Navigate to the directory where this script is located
cd "$(dirname "$0")"

# Print the current working directory for debugging purposes
echo "Current directory: $(pwd)"

# Set the classpath to include the required libraries
LIBS_PATH="libs/algs4.jar"

# Check if the server directory exists
if [ ! -d "server" ]; then
  echo "Error: server directory not found"
  exit 1
fi

# Print the contents of the server directory for debugging purposes
echo "Contents of server directory:"
ls -l server

# Compile the Java files and specify the destination directory for class files
echo "Compiling Java files..."
javac -cp "$LIBS_PATH" -d . server/*.java

# Check if compilation was successful
if [ $? -ne 0 ]; then
  echo "Compilation failed"
  exit 1
fi

echo "Compilation successful"

# Run the main class
echo "Running Java program..."
java -cp ".:$LIBS_PATH" server.GradeCalculator $1 $2 $3

# Check if execution was successful
if [ $? -ne 0 ]; then
  echo "Execution failed"
  exit 1
fi

echo "Execution successful"
