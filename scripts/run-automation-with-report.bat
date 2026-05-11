@echo off
setlocal

cd /d "%~dp0.."

if "%HEADLESS%"=="" set HEADLESS=false

call mvn clean test
set TEST_EXIT_CODE=%ERRORLEVEL%

where allure >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    call allure generate --single-file target/allure-results -o target/Reporte_Allure_Local --clean
    if exist "target\Reporte_Allure_Local\index.html" (
        echo Reporte Allure local generado:
        echo %CD%\target\Reporte_Allure_Local\index.html
    )
) else (
    echo Allure CLI no esta instalado o no esta en el PATH.
    echo Se generara el reporte con Maven en target\allure-report.
    call mvn allure:report -DskipTests
    if exist "target\allure-report\index.html" (
        echo Reporte Allure Maven generado:
        echo %CD%\target\allure-report\index.html
    )
)

exit /b %TEST_EXIT_CODE%
