@echo off
chcp 65001 >nul
echo ========================================
echo 🎭 魔术锁屏 APK 构建脚本
echo ========================================
echo.

echo [1/3] 检查环境...
if not exist "gradlew.bat" (
    echo ❌ 错误: 请在项目根目录运行此脚本
    pause
    exit /b 1
)

echo [2/3] 清理旧的构建文件...
call gradlew.bat clean

echo.
echo [3/3] 开始构建 APK...
echo ⏳ 这可能需要 5-10 分钟（首次运行会下载依赖）
echo.
call gradlew.bat assembleDebug

echo.
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo ========================================
    echo ✅ 构建成功！
    echo ========================================
    echo.
    echo 📦 APK 位置:
    echo %cd%\app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo 文件大小:
    for %%F in ("app\build\outputs\apk\debug\app-debug.apk") do echo %%~zF 字节 (约 %%~zF KB)
    echo.
    echo 📋 下一步:
    echo 1. 将 app-debug.apk 传输到手机
    echo 2. 在手机上安装 APK
    echo 3. 允许"安装未知来源"权限
    echo 4. 启动应用开始使用
    echo.
    echo 💡 提示: 你也可以手动上传到 Gitee Releases
    echo    访问: https://gitee.com/uhealu/magic-lock-screen/releases
    echo.
) else (
    echo ========================================
    echo ❌ 构建失败
    echo ========================================
    echo.
    echo 请检查上面的错误信息
    echo.
)

pause
