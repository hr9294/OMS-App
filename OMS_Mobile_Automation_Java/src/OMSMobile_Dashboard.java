import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class OMSMobile_Dashboard {

    public static boolean verifyDashboard(AndroidDriver driver) {
        System.out.println("[DASHBOARD] Verifying Dashboard / Navigation elements...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(OMSMobile_Driver.TIMEOUT_SECONDS));
            WebElement dashboardElem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.xpath("//*[contains(@text, 'Market') or contains(@text, 'Watchlist') or contains(@text, 'Portfolio') or contains(@resource-id, 'dashboard')]")
            ));
            return dashboardElem.isDisplayed();
        } catch (Exception e) {
            System.err.println("[DASHBOARD] Could not find dashboard element: " + e.getMessage());
            return false;
        }
    }
}
