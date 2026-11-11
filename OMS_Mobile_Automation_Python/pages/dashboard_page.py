from appium.webdriver.common.appiumby import AppiumBy

from pages.base_page import BasePage


class DashboardPage(BasePage):
    DASHBOARD_INDICATOR = (
        AppiumBy.XPATH,
        "//*[contains(@text, 'Market') or contains(@text, 'Watchlist') or contains(@text, 'Portfolio') or contains(@resource-id, 'dashboard')]"
    )

    def is_dashboard_loaded(self) -> bool:
        print("[DASHBOARD] Checking if Dashboard/Watchlist is visible...")
        try:
            elem = self.wait_for_visibility(self.DASHBOARD_INDICATOR)
            return elem.is_displayed()
        except Exception as e:
            print(f"[DASHBOARD] Failed to find dashboard element: {e}")
            return False
