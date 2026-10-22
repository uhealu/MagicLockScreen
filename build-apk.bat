@echo off
echo ========================================
echo Magic LockScreen APK Builder
echo ========================================
echo.

echo [1/3] Checking environment...
if not exist "gradlew.bat" (
    echo ERROR: Please run this script in project root directory
    pause
    exit /b 1
)

echo [2/3] Cleaning old build files...
call gradlew.bat clean

echo.
echo [3/3] Building APK...
echo This may take 5-10 minutes for first time (downloading dependencies)
echo.
call gradlew.bat assembleDebug

echo.
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo ========================================
    echo BUILD SUCCESS!
    echo ========================================
    echo.
    echo APK Location:
    echo %cd%\app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo Next Steps:
    echo 1. Transfer app-debug.apk to your phone
    echo 2. Install APK on phone
    echo 3. Allow "Install from Unknown Sources"
    echo 4. Launch the app
    echo.
    echo You can also upload to Gitee Releases:
    echo https://gitee.com/uhealu/magic-lock-screen/releases
    echo.
    explorer app\build\outputs\apk\debug
) else (
    echo ========================================
    echo BUILD FAILED
    echo ========================================
    echo.
    echo Please check the error messages above
    echo.
)

pause
