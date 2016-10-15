@echo off 
call mvn package -Dmaven.test.skip=true -Pdev
echo. & pause