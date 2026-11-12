import os
from appium import webdriver
from appium.options.android import UiAutomator2Options

import config


def create_driver() -> webdriver.Remote:
    """Creates and starts an Android Appium session using UiAutomator2."""
    options = UiAutomator2Options()
    options.platform_name = "Android"
    options.automation_name = "UiAutomator2"
    options.device_name = "Android Device"
    options.app_package = config.APP_PACKAGE
    options.app_activity = config.APP_ACTIVITY
    options.no_reset = config.NO_RESET
    options.auto_grant_permissions = True

    print(f"[DRIVER] Connecting to Appium at {config.APPIUM_SERVER_URL}...")
    driver = webdriver.Remote(
        command_executor=config.APPIUM_SERVER_URL,
        options=options
    )
    driver.implicitly_wait(config.IMPLICIT_WAIT)
    print("[DRIVER] Successfully created mobile session.")
    return driver


def take_screenshot(driver: webdriver.Remote, filename: str) -> None:
    """Takes a screenshot of the mobile screen."""
    try:
        driver.save_screenshot(filename)
        print(f"[SCREENSHOT] Saved: {os.path.abspath(filename)}")
    except Exception as e:
        print(f"[SCREENSHOT ERROR] Could not save screenshot: {e}")
