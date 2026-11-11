import pytest
import config
from driver_factory import create_driver, take_screenshot
from pages.login_page import LoginPage
from pages.dashboard_page import DashboardPage


@pytest.fixture(scope="function")
def driver():
    drv = create_driver()
    yield drv
    drv.quit()


def test_oms_mobile_login(driver):
    login_page = LoginPage(driver)
    dashboard_page = DashboardPage(driver)

    login_page.login(config.DEFAULT_USERNAME, config.DEFAULT_PASSWORD)

    is_loaded = dashboard_page.is_dashboard_loaded()
    if is_loaded:
        take_screenshot(driver, "test_login_success.png")
    else:
        take_screenshot(driver, "test_login_failed.png")

    assert is_loaded, "Mobile dashboard was not loaded after login."
