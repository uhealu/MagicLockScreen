@rem Gradle startup script for Windows

@if "%DEBUG%"=="" @echo off
setlocal enabledelayedexpansion

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.

set GRADLE_APP_NAME=Gradle
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

set CLASSPATH=%DIRNAME%gradle\wrapper\gradle-wrapper.jar

java.exe %DEFAULT_JVM_OPTS% %JAVA_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
