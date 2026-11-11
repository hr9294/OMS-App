from appium.webdriver.common.appiumby import AppiumBy
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class Watchlist:
    @staticmethod
    def open_watchlist(driver):
        print("\n[WATCHLIST] Selecting Watchlist tab...")
        wait = WebDriverWait(driver, 20)
        tab = wait.until(EC.element_to_be_clickable((
            AppiumBy.XPATH,
            "//android.widget.TextView[@text='Watchlist'] | //*[contains(@text, 'Watchlist')]"
        )))
        tab.click()

    @staticmethod
    def switch_exchange(driver, exchange: str = "DSE"):
        print(f"[WATCHLIST] Switching exchange to: {exchange}")
        try:
            wait = WebDriverWait(driver, 10)
            btn = wait.until(EC.element_to_be_clickable((
                AppiumBy.XPATH,
                f"//*[normalize-space(@text)='{exchange}']"
            )))
            btn.click()
        except Exception as e:
            print(f"[WATCHLIST] Exchange switch notice: {e}")

    @staticmethod
    def verify_symbol(driver, symbol_name: str = "ABBANK") -> bool:
        print(f"[WATCHLIST] Checking visibility of symbol: {symbol_name}")
        try:
            wait = WebDriverWait(driver, 10)
            elem = wait.until(EC.visibility_of_element_located((
                AppiumBy.XPATH,
                f"//*[contains(@text, '{symbol_name}')]"
            )))
            print(f"  ✓ Found symbol {symbol_name}: {elem.is_displayed()}")
            return elem.is_displayed()
        except Exception:
            return False
