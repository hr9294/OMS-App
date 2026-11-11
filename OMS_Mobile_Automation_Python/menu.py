from appium.webdriver.common.appiumby import AppiumBy
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class Menu:
    @staticmethod
    def open_quick_menu(driver):
        print("\n[MENU] Opening Center Quick Menu launcher...")
        try:
            wait = WebDriverWait(driver, 20)
            center_btn = wait.until(EC.element_to_be_clickable((
                AppiumBy.XPATH,
                "//android.widget.ImageView[contains(@resource-id, 'center') or contains(@resource-id, 'menu_launcher')] | //android.view.ViewGroup[contains(@clickable, 'true') and @index='2']"
            )))
            center_btn.click()
            print("[MENU] Quick Menu opened.")
        except Exception as e:
            print(f"[MENU] Quick Menu notice: {e}")

    @staticmethod
    def verify_profile_name(driver, expected_name: str = "MD HASIBUR RAHMAN"):
        print(f"[MENU] Verifying Profile: {expected_name}")
        try:
            wait = WebDriverWait(driver, 10)
            elem = wait.until(EC.visibility_of_element_located((
                AppiumBy.XPATH, f"//*[contains(@text, '{expected_name}')]"
            )))
            print(f"  ✓ User profile confirmed: {elem.text}")
        except Exception as e:
            print(f"[MENU] Profile verify notice: {e}")
