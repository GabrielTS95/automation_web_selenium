@echo off
setlocal

cd /d "%~dp0.."
set HEADLESS=true
mvn clean test

endlocal
