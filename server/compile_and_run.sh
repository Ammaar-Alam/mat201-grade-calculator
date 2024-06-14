#!/bin/bash
javac server/GradeCalculator.java server/PsetInfo.java server/LectureInfo.java
java -cp server GradeCalculator
