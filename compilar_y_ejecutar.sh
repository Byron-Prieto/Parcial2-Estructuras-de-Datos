#!/bin/sh
# Linux / macOS:  sh compilar_y_ejecutar.sh   (requiere JDK 11 o superior)
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java") && java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out parcial.Main
