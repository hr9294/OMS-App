@echo off
setlocal
echo ========================================================
echo   Syncing OMS-App across Git and Local Workspaces
echo ========================================================
python "%~dp0sync_oms_app.py" %*
pause
