import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Login {

    public static void login(AndroidDriver driver, String userCode, String password) {
        System.out.println("-----------------------------------------------------");
        System.out.println("[LOGIN MODULE] Starting authentication process...");
        System.out.println("-----------------------------------------------------");

        // 1. Enter Client Code / Username (Screenshot 1: 53196)
        System.out.println("[LOGIN] Entering Client Code / Username: " + userCode);
        WebElement userInput = Driver.waitForVisibility(driver,
            AppiumBy.xpath("//android.widget.EditText[contains(@resource-id, 'username') or contains(@resource-id, 'client_code') or @index='0']")
        );
        userInput.clear();
        userInput.sendKeys(userCode);

        // 2. Enter Password (Screenshot 1: 12345)
        System.out.println("[LOGIN] Entering Password...");
        WebElement passInput = driver.findElement(
            AppiumBy.xpath("//android.widget.EditText[contains(@resource-id, 'password') or @password='true']")
        );
        passInput.clear();
        passInput.sendKeys(password);

        // 3. Optional: Ensure 'Remember me' is checked
        try {
            WebElement rememberMe = driver.findElement(
                AppiumBy.xpath("//android.widget.CheckBox | //*[contains(@text, 'Remember me')]")
            );
            if (!"true".equalsIgnoreCase(rememberMe.getAttribute("checked"))) {
                rememberMe.click();
            }
        } catch (Exception ignored) {}

        // 4. Click 'Sign in' Button (Screenshot 1)
        System.out.println("[LOGIN] Tapping 'Sign in' button...");
        WebElement signInBtn = Driver.waitForClickable(driver,
            AppiumBy.xpath("//android.widget.Button[normalize-space(@text)='Sign in'] | //*[contains(@text, 'Sign in')]")
        );
        signInBtn.click();

        // 5. Handle Force Login / Active Session Alert (if present)
        handleForceLoginIfPresent(driver);

        System.out.println("[LOGIN] Login submission complete.");
    }

    private static void handleForceLoginIfPresent(AndroidDriver driver) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(4));
            WebElement continueBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.Button[contains(@text, 'Continue') or contains(@text, 'Force') or contains(@text, 'YES')]")
            ));
            System.out.println("[LOGIN ALERT] Active session detected! Tapping 'Continue' to force login...");
            continueBtn.click();
        } catch (Exception ignored) {}
    }
}
