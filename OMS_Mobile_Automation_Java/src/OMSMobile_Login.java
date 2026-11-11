import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class OMSMobile_Login {

    public static void login(AndroidDriver driver, String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(OMSMobile_Driver.TIMEOUT_SECONDS));

        // 1. Enter Username
        System.out.println("[LOGIN] Entering Username: " + username);
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.EditText[contains(@resource-id, 'username') or contains(@text, 'User') or @index='0']")
        ));
        userField.clear();
        userField.sendKeys(username);

        // 2. Enter Password
        System.out.println("[LOGIN] Entering Password...");
        WebElement passField = driver.findElement(
                AppiumBy.xpath("//android.widget.EditText[contains(@resource-id, 'password') or contains(@text, 'Password') or @password='true']")
        );
        passField.clear();
        passField.sendKeys(password);

        // 3. Click Login Button
        System.out.println("[LOGIN] Tapping Login button...");
        WebElement loginBtn = driver.findElement(
                AppiumBy.xpath("//android.widget.Button[contains(@text, 'Login') or contains(@resource-id, 'login_button')]")
        );
        loginBtn.click();

        // 4. Handle Active Session popup if present
        handleActiveSessionPopup(driver);
    }

    private static void handleActiveSessionPopup(AndroidDriver driver) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(4));
            WebElement continueBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.xpath("//android.widget.Button[contains(@text, 'Continue') or contains(@text, 'Force') or contains(@text, 'YES')]")
            ));
            System.out.println("[LOGIN] Active session detected! Clicking Continue to override...");
            continueBtn.click();
        } catch (Exception ignored) {
            // Popup not present
        }
    }
}
