@echo off 
call mvn package -Dmaven.test.skip=true -Pproduction
echo. & pause