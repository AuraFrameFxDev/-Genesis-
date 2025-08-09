@echo off
echo ============================================
echo 🚀 AURA FRAME FX - NUCLEAR BUILD FIX 🚀
echo ============================================
echo.

echo 1. Stopping Gradle daemon...
cd /d "C:\Genesis"
call gradlew --stop
timeout /t 2 /nobreak >nul

echo 2. Cleaning project completely...
call gradlew clean
timeout /t 3 /nobreak >nul

echo 3. Deleting build directories...
if exist "build" rmdir /s /q "build"
if exist "app\build" rmdir /s /q "app\build"
if exist ".gradle" rmdir /s /q ".gradle"
timeout /t 2 /nobreak >nul

echo 4. Deleting Gradle cache...
if exist "%USERPROFILE%\.gradle\caches" rmdir /s /q "%USERPROFILE%\.gradle\caches"
timeout /t 2 /nobreak >nul

echo 5. Rebuilding project with fresh state...
call gradlew clean
call gradlew build --no-daemon --no-build-cache --refresh-dependencies
timeout /t 2 /nobreak >nul

echo.
echo ============================================
echo ✅ BUILD FIX COMPLETE! ✅
echo ============================================
echo.
echo Next steps:
echo 1. Open Android Studio
echo 2. File → Invalidate Caches and Restart
echo 3. Build → Clean Project
echo 4. Build → Rebuild Project
echo.
pause
