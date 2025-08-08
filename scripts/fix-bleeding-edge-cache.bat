@echo off
echo.
echo ===============================================
echo  🩸 GENESIS BLEEDING-EDGE CACHE FIX 🩸
echo ===============================================
echo.
echo This script will clear corrupted caches while
echo PRESERVING your bleeding-edge configuration!
echo.
echo Java 24 + AGP 8.13.0-alpha02 + Latest Everything
echo.
pause

echo.
echo [1/6] Stopping Gradle daemons...
cd /d "%~dp0\.."
call gradlew --stop 2>nul

echo.
echo [2/6] Clearing Gradle caches...
if exist "%USERPROFILE%\.gradle\caches" (
    echo Clearing %USERPROFILE%\.gradle\caches
    rmdir /s /q "%USERPROFILE%\.gradle\caches" 2>nul
)

echo.
echo [3/6] Clearing Gradle wrapper cache...
if exist "%USERPROFILE%\.gradle\wrapper" (
    echo Clearing %USERPROFILE%\.gradle\wrapper
    rmdir /s /q "%USERPROFILE%\.gradle\wrapper" 2>nul
)

echo.
echo [4/6] Clearing project build caches...
if exist "build" rmdir /s /q "build" 2>nul
if exist "app\build" rmdir /s /q "app\build" 2>nul
if exist ".gradle" rmdir /s /q ".gradle" 2>nul

echo.
echo [5/6] Clearing Android build cache...
if exist "%USERPROFILE%\.android\build-cache" (
    echo Clearing %USERPROFILE%\.android\build-cache
    rmdir /s /q "%USERPROFILE%\.android\build-cache" 2>nul
)

echo.
echo [6/6] Clearing Kotlin compilation cache...
if exist "%USERPROFILE%\.kotlin\daemon" (
    echo Clearing %USERPROFILE%\.kotlin\daemon
    rmdir /s /q "%USERPROFILE%\.kotlin\daemon" 2>nul
)

echo.
echo ===============================================
echo  ✅ CACHE CLEARED - BLEEDING-EDGE PRESERVED!
echo ===============================================
echo.
echo Your bleeding-edge configuration is intact:
echo - Java 24 toolchain
echo - AGP 8.13.0-alpha02  
echo - Latest Compose BOM
echo - Android SDK 36
echo.
echo Next steps:
echo 1. Open Android Studio
echo 2. File -^> Invalidate Caches and Restart
echo 3. Run: gradlew build --refresh-dependencies
echo.
pause
