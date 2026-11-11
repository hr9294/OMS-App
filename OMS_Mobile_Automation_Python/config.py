import os

APPIUM_SERVER_URL = os.getenv("APPIUM_SERVER_URL", "http://127.0.0.1:4723")
APP_PACKAGE = os.getenv("OMS_APP_PACKAGE", "com.quantbd.oms")
APP_ACTIVITY = os.getenv("OMS_APP_ACTIVITY", ".ui.login.LoginActivity")
DEFAULT_USERNAME = os.getenv("OMS_USERNAME", "Testadmin")
DEFAULT_PASSWORD = os.getenv("OMS_PASSWORD", "12345")
EXPLICIT_WAIT = 20
IMPLICIT_WAIT = 5
