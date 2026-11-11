# OMS Mobile Automation (Python / VS Code)

A dedicated, standalone Appium 2.x Mobile Test Automation project for the OMS Android Application.

## Project Structure
- `.vscode/launch.json` - Ready for F5 Run and Debug in VS Code
- `config.py` - Server URL, Package, Activity, Credentials
- `driver_factory.py` - Appium UiAutomator2 driver session manager
- `pages/` - Page Object Model (LoginPage, DashboardPage)
- `tests/` - Pytest test cases
- `main.py` - Direct runner script

## Quick Start in VS Code

1. Open VS Code.
2. Click **File** -> **Open Folder...**
3. Select:
   `D:\Anitgravity\OMS_Mobile_Automation_Python`
4. Open the integrated terminal (`Ctrl + ~`) and install requirements:
   ```bash
   pip install -r requirements.txt
   ```
5. Ensure Appium is running in another terminal (`appium`).
6. Run the test:
   - Either press **F5** in VS Code.
   - Or run `python main.py` in the terminal.
   - Or run `pytest tests/ -v -s`.
