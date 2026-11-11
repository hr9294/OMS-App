"""
OMS Mobile App Automation - Direct Runner
Target: Android Appium 2.x
"""

import time
import config
from driver_factory import create_driver, take_screenshot
from pages.login_page import LoginPage
from pages.dashboard_page import DashboardPage


def run_mobile_test():
    print("=====================================================")
    print("   RUNNING OMS MOBILE AUTOMATION TEST (VS CODE)      ")
    print("=====================================================")

    driver = None
    try:
        driver = create_driver()

        login_page = LoginPage(driver)
        dashboard_page = DashboardPage(driver)

        # Step 1: Perform Login
        login_page.login(config.DEFAULT_USERNAME, config.DEFAULT_PASSWORD)

        # Step 2: Verify Dashboard
        if dashboard_page.is_dashboard_loaded():
            print("\n>>> [PASS] OMS MOBILE DASHBOARD VERIFIED! <<<")
            take_screenshot(driver, "mobile_dashboard_success.png")
        else:
            print("\n>>> [FAIL] OMS DASHBOARD NOT LOADED! <<<")
            take_screenshot(driver, "mobile_dashboard_failed.png")

    except Exception as e:
        print(f"\n[ERROR] Test run encountered an exception: {e}")
        if driver:
            take_screenshot(driver, "mobile_test_exception.png")
        raise
    finally:
        if driver:
            print("[TEARDOWN] Quitting mobile driver...")
            time.sleep(2)
            driver.quit()
        print("Execution complete.")


if __name__ == "__main__":
    run_mobile_test()
