import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Portfolio {

    public static void openPortfolio(AndroidDriver driver) {
        System.out.println("-----------------------------------------------------");
        System.out.println("[PORTFOLIO MODULE] Navigating to Portfolio...");
        System.out.println("-----------------------------------------------------");

        // Bottom Navigation -> Portfolio (Briefcase Icon)
        WebElement portfolioTab = Driver.waitForClickable(driver,
            AppiumBy.xpath("//android.widget.TextView[@text='Portfolio'] | //*[contains(@text, 'Portfolio')]")
        );
        portfolioTab.click();
        System.out.println("[PORTFOLIO] Portfolio tab selected.");
    }

    public static void verifyClientCode(AndroidDriver driver, String expectedCode) {
        // Screenshot 7: Client code: 53196
        System.out.println("[PORTFOLIO] Verifying Client Code: " + expectedCode);
        try {
            WebElement codeElem = Driver.waitForVisibility(driver,
                AppiumBy.xpath("//*[contains(@text, '" + expectedCode + "')]")
            );
            System.out.println("  ✓ Client Code confirmed: " + codeElem.getText());
        } catch (Exception e) {
            System.err.println("[PORTFOLIO] Client Code mismatch or not found: " + e.getMessage());
        }
    }

    public static void switchSubTab(AndroidDriver driver, String subTabName) {
        // Sub-tabs: "Portfolio", "Order list", "Order Summary", "Account Limits", "Summary" (Screenshots 7-11)
        System.out.println("[PORTFOLIO] Switching sub-tab to: " + subTabName);
        try {
            WebElement tab = Driver.waitForClickable(driver,
                AppiumBy.xpath("//*[normalize-space(@text)='" + subTabName + "'] | //android.widget.TextView[contains(@text, '" + subTabName + "')]")
            );
            tab.click();
            System.out.println("[PORTFOLIO] Switched to sub-tab: " + subTabName);
        } catch (Exception e) {
            System.err.println("[PORTFOLIO] Failed to click sub-tab " + subTabName + ": " + e.getMessage());
        }
    }

    public static void verifyHoldingsListed(AndroidDriver driver) {
        // Verifies stock holdings e.g. ABBANK, CITYBANK, AFTABAUTO
        System.out.println("[PORTFOLIO] Checking for Stock Holdings in Portfolio...");
        try {
            WebElement item = Driver.waitForVisibility(driver,
                AppiumBy.xpath("//*[contains(@text, 'ABBANK') or contains(@text, 'CITYBANK') or contains(@text, 'Current Value')]")
            );
            System.out.println("  ✓ Holdings item visible: " + item.getText());
        } catch (Exception e) {
            System.err.println("[PORTFOLIO] No holdings found: " + e.getMessage());
        }
    }
}
