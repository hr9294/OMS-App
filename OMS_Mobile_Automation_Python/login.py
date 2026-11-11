import time
from appium.webdriver.common.appiumby import AppiumBy
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class Login:
    @staticmethod
    def login(driver, user_code: str = "53196", password: str = "12345"):
        print("\n[LOGIN] Starting login for client code:", user_code)
        wait = WebDriverWait(driver, 20)

        # 1. User code / username input (Screenshot 1: 53196)
        user_input = wait.until(EC.visibility_of_element_located((
            AppiumBy.XPATH,
            "//android.widget.EditText[contains(@resource-id, 'username') or contains(@resource-id, 'client_code') or @index='0']"
        )))
        user_input.clear()
        user_input.send_keys(user_code)

        # 2. Password input (Screenshot 1: 12345)
        pass_input = driver.find_element(
            AppiumBy.XPATH,
            "//android.widget.EditText[contains(@resource-id, 'password') or @password='true']"
        )
        pass_input.clear()
        pass_input.send_keys(password)

        # 3. Tap 'Sign in' button
        sign_in_btn = wait.until(EC.element_to_be_clickable((
            AppiumBy.XPATH,
            "//android.widget.Button[normalize-space(@text)='Sign in'] | //*[contains(@text, 'Sign in')]"
        )))
        sign_in_btn.click()

        # 4. Handle active session alert if shown
        try:
            short_wait = WebDriverWait(driver, 4)
            continue_btn = short_wait.until(EC.element_to_be_clickable((
                AppiumBy.XPATH,
                "//android.widget.Button[contains(@text, 'Continue') or contains(@text, 'Force') or contains(@text, 'YES')]"
            )))
            print("[LOGIN] Active session detected. Tapping Continue...")
            continue_btn.click()
        except Exception:
            pass

        print("[LOGIN] Login flow complete.")
