from appium.webdriver.common.appiumby import AppiumBy
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from pages.base_page import BasePage


class LoginPage(BasePage):
    USERNAME_INPUT = (
        AppiumBy.XPATH,
        "//android.widget.EditText[contains(@resource-id, 'username') or contains(@text, 'User') or @index='0']"
    )
    PASSWORD_INPUT = (
        AppiumBy.XPATH,
        "//android.widget.EditText[contains(@resource-id, 'password') or contains(@text, 'Password') or @password='true']"
    )
    LOGIN_BUTTON = (
        AppiumBy.XPATH,
        "//android.widget.Button[contains(@text, 'Login') or contains(@resource-id, 'login_button')]"
    )
    ACTIVE_SESSION_CONTINUE = (
        AppiumBy.XPATH,
        "//android.widget.Button[contains(@text, 'Continue') or contains(@text, 'Force') or contains(@text, 'YES')]"
    )

    def enter_username(self, username: str):
        print(f"[LOGIN] Entering username: {username}")
        elem = self.wait_for_visibility(self.USERNAME_INPUT)
        elem.clear()
        elem.send_keys(username)

    def enter_password(self, password: str):
        print("[LOGIN] Entering password...")
        elem = self.wait_for_visibility(self.PASSWORD_INPUT)
        elem.clear()
        elem.send_keys(password)

    def click_login(self):
        print("[LOGIN] Tapping Login button...")
        self.wait_for_clickable(self.LOGIN_BUTTON).click()

    def handle_active_session_if_present(self):
        try:
            short_wait = WebDriverWait(self.driver, 4)
            btn = short_wait.until(EC.element_to_be_clickable(self.ACTIVE_SESSION_CONTINUE))
            print("[LOGIN] Active session detected! Tapping Continue to override...")
            btn.click()
        except Exception:
            pass

    def login(self, username: str, password: str):
        self.enter_username(username)
        self.enter_password(password)
        self.click_login()
        self.handle_active_session_if_present()
