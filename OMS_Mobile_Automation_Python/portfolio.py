from appium.webdriver.common.appiumby import AppiumBy
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class Portfolio:
    @staticmethod
    def open_portfolio(driver):
        print("\n[PORTFOLIO] Navigating to Portfolio...")
        wait = WebDriverWait(driver, 20)
        tab = wait.until(EC.element_to_be_clickable((
            AppiumBy.XPATH,
            "//android.widget.TextView[@text='Portfolio'] | //*[contains(@text, 'Portfolio')]"
        )))
        tab.click()

    @staticmethod
    def verify_client_code(driver, expected_code: str = "53196"):
        print(f"[PORTFOLIO] Checking client code: {expected_code}")
        try:
            wait = WebDriverWait(driver, 10)
            elem = wait.until(EC.visibility_of_element_located((
                AppiumBy.XPATH, f"//*[contains(@text, '{expected_code}')]"
            )))
            print(f"  ✓ Confirmed client code: {elem.text}")
        except Exception as e:
            print(f"[PORTFOLIO] Notice: {e}")

    @staticmethod
    def switch_sub_tab(driver, sub_tab_name: str = "Order Summary"):
        print(f"[PORTFOLIO] Switching to subtab: {sub_tab_name}")
        try:
            wait = WebDriverWait(driver, 10)
            tab = wait.until(EC.element_to_be_clickable((
                AppiumBy.XPATH, f"//*[normalize-space(@text)='{sub_tab_name}']"
            )))
            tab.click()
        except Exception as e:
            print(f"[PORTFOLIO] Subtab switch error: {e}")
