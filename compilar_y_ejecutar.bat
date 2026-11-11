@echo off
REM Windows: doble clic o ejecutar desde cmd (requiere JDK 11 o superior)
chcp 65001 >nul
if not exist out mkdir out
dir /s /b src\*.java > fuentes.txt
javac -encoding UTF-8 -d out @fuentes.txt && java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out parcial.Main
del fuentes.txt
pause
