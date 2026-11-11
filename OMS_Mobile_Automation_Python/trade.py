from appium.webdriver.common.appiumby import AppiumBy
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class Trade:
    @staticmethod
    def open_trade(driver):
        print("\n[TRADE] Navigating to Trade Terminal...")
        wait = WebDriverWait(driver, 20)
        tab = wait.until(EC.element_to_be_clickable((
            AppiumBy.XPATH,
            "//android.widget.TextView[@text='Trade'] | //*[contains(@text, 'Trade')]"
        )))
        tab.click()

    @staticmethod
    def select_side(driver, side: str = "BUY"):
        print(f"[TRADE] Selecting side: {side}")
        try:
            wait = WebDriverWait(driver, 10)
            btn = wait.until(EC.element_to_be_clickable((
                AppiumBy.XPATH, f"//*[normalize-space(@text)='{side.upper()}']"
            )))
            btn.click()
        except Exception as e:
            print(f"[TRADE] Side select notice: {e}")

    @staticmethod
    def enter_order(driver, symbol: str = "ABBANK", qty: str = "10", rate: str = "32.5"):
        print(f"[TRADE] Entering Order: Symbol={symbol}, Qty={qty}, Rate={rate}")
        try:
            wait = WebDriverWait(driver, 10)
            search_input = wait.until(EC.visibility_of_element_located((
                AppiumBy.XPATH, "//android.widget.EditText[contains(@text, 'Search symbol') or contains(@hint, 'Search symbol')]"
            )))
            search_input.clear()
            search_input.send_keys(symbol)
        except Exception as e:
            print(f"[TRADE] Symbol input error: {e}")
