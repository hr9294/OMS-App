package com.quantbd.oms.mobile.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BasePage {

    // Mobile Locators (support generic edittext attributes and specific IDs)
    private By usernameInput = AppiumBy.xpath(
        "//android.widget.EditText[contains(@resource-id, 'username') or contains(@text, 'User') or @index='0']"
    );
    private By passwordInput = AppiumBy.xpath(
        "//android.widget.EditText[contains(@resource-id, 'password') or contains(@text, 'Password') or @password='true']"
    );
    private By loginButton = AppiumBy.xpath(
        "//android.widget.Button[contains(@text, 'Login') or contains(@resource-id, 'login_button')]"
    );
    private By activeSessionContinueBtn = AppiumBy.xpath(
        "//android.widget.Button[contains(@text, 'Continue') or contains(@text, 'Force') or contains(@text, 'YES')]"
    );

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        System.out.println("[LOGIN] Entering username: " + username);
        WebElement elem = waitForVisibility(usernameInput);
        elem.clear();
        elem.sendKeys(username);
    }

    public void enterPassword(String password) {
        System.out.println("[LOGIN] Entering password...");
        WebElement elem = waitForVisibility(passwordInput);
        elem.clear();
        elem.sendKeys(password);
    }

    public void clickLogin() {
        System.out.println("[LOGIN] Clicking Login button...");
        waitForClickable(loginButton).click();
    }

    public void handleActiveSessionIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(4));
            WebElement continueBtn = shortWait.until(ExpectedConditions.elementToBeClickable(activeSessionContinueBtn));
            System.out.println("[LOGIN] Active session dialog detected! Forcing login...");
            continueBtn.click();
        } catch (Exception ignored) {
            // Popup did not appear; continue normally
        }
    }

    public void performFullLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        handleActiveSessionIfPresent();
    }
}
