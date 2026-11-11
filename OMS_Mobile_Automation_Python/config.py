import os
import json

CONFIG_FILE = os.path.join(os.path.dirname(__file__), "local_config.json")

# Default values
APPIUM_SERVER_URL = "http://127.0.0.1:4723"
APP_PACKAGE = "com.quantbd.oms"
APP_ACTIVITY = ".ui.login.LoginActivity"
DEFAULT_USER_CODE = "53196"
DEFAULT_PASSWORD = "12345"
EXPLICIT_WAIT = 20
IMPLICIT_WAIT = 5

# Override from local_config.json if present
if os.path.exists(CONFIG_FILE):
    try:
        with open(CONFIG_FILE, "r", encoding="utf-8") as f:
            data = json.load(f)
            APPIUM_SERVER_URL = data.get("appium_server_url", APPIUM_SERVER_URL)
            APP_PACKAGE = data.get("app_package", APP_PACKAGE)
            APP_ACTIVITY = data.get("app_activity", APP_ACTIVITY)
            DEFAULT_USER_CODE = data.get("user_code", DEFAULT_USER_CODE)
            DEFAULT_PASSWORD = data.get("password", DEFAULT_PASSWORD)
            EXPLICIT_WAIT = data.get("timeout_seconds", EXPLICIT_WAIT)
            print("[CONFIG] Loaded local configuration from local_config.json")
    except Exception as e:
        print(f"[CONFIG ERROR] Could not read local_config.json: {e}")
