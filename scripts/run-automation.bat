@echo off
setlocal

cd /d "%~dp0.."
set HEADLESS=false
mvn clean test

endlocal
