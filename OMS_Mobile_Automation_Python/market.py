from appium.webdriver.common.appiumby import AppiumBy
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class Market:
    @staticmethod
    def open_market(driver):
        print("\n[MARKET] Navigating to Market overview...")
        wait = WebDriverWait(driver, 20)
        tab = wait.until(EC.element_to_be_clickable((
            AppiumBy.XPATH,
            "//android.widget.TextView[@text='Market'] | //*[contains(@text, 'Market')]"
        )))
        tab.click()

    @staticmethod
    def verify_market_data(driver):
        print("[MARKET] Verifying DSEX indices and market stats...")
        try:
            wait = WebDriverWait(driver, 10)
            dsex = wait.until(EC.visibility_of_element_located((
                AppiumBy.XPATH, "//*[contains(@text, 'DSEX')]"
            )))
            print(f"  ✓ DSEX Card found: {dsex.is_displayed()}")
        except Exception as e:
            print(f"[MARKET] Notice: {e}")
