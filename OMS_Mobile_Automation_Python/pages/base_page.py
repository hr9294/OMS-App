from appium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

import config


class BasePage:
    def __init__(self, driver: webdriver.Remote):
        self.driver = driver
        self.wait = WebDriverWait(driver, config.EXPLICIT_WAIT)

    def wait_for_visibility(self, locator):
        return self.wait.until(EC.visibility_of_element_located(locator))

    def wait_for_clickable(self, locator):
        return self.wait.until(EC.element_to_be_clickable(locator))
