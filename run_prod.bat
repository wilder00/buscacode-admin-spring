@echo off
setlocal

REM Ensure Docker is running
docker info >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo Docker is not running. Please start Docker and try again.
    exit /b 1
)

:: REM Navigate to the directory where docker-compose.yml is located
:: cd /d "%~dp0"

REM Run docker-compose using the .env file
:: docker-compose --env-file .env.production up -d
:: docker-compose --env-file .env.production up

docker-compose --env-file .env.production build --no-cache

echo Docker containers are starting...
exit /b 0
