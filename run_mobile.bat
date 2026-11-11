@echo off
title OMS / UFT FAST Mobile App Automation Runner
echo ==========================================================
echo        OMS / UFT FAST MOBILE AUTOMATION TEST RUNNER
echo ==========================================================
echo.

echo [1/3] Checking connected Android devices/emulators...
adb devices
echo.

echo [2/3] Checking Appium Server connectivity...
curl -s http://127.0.0.1:4723/status >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [WARNING] Appium server is not running on http://127.0.0.1:4723!
    echo Please open another CMD and start Appium by typing: appium
    echo.
) else (
    echo [OK] Appium Server is online.
)
echo.

echo [3/3] Choose which mobile automation suite to execute:
echo   1. Run Python Suite (VS Code engine)
echo   2. Run Java Maven Test
echo   3. Exit
echo.
set /p choice="Enter choice (1, 2, or 3): "

if "%choice%"=="1" (
    echo.
    echo Starting Python Mobile Suite...
    cd OMS_Mobile_Automation_Python
    python main.py
    pause
    exit /b
)

if "%choice%"=="2" (
    echo.
    echo Starting Java Mobile Suite via Maven...
    cd OMS_Mobile_Automation_Java
    mvn clean test
    pause
    exit /b
)

echo Exiting...
exit /b
