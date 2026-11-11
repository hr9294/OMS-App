import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Market {

    public static void openMarket(AndroidDriver driver) {
        System.out.println("-----------------------------------------------------");
        System.out.println("[MARKET MODULE] Navigating to Market overview...");
        System.out.println("-----------------------------------------------------");

        // Bottom Navigation -> Market (Pie Chart Icon)
        WebElement marketTab = Driver.waitForClickable(driver,
            AppiumBy.xpath("//android.widget.TextView[@text='Market'] | //*[contains(@text, 'Market')]")
        );
        marketTab.click();
        System.out.println("[MARKET] Market tab selected.");
    }

    public static void verifyMarketIndices(AndroidDriver driver) {
        System.out.println("[MARKET] Verifying Key Market Indices (DSEX, DS30, DSES)...");
        try {
            WebElement dsex = Driver.waitForVisibility(driver, AppiumBy.xpath("//*[contains(@text, 'DSEX')]"));
            WebElement ds30 = driver.findElement(AppiumBy.xpath("//*[contains(@text, 'DS30')]"));
            WebElement dses = driver.findElement(AppiumBy.xpath("//*[contains(@text, 'DSES')]"));

            System.out.println("  ✓ DSEX Card Detected: " + dsex.isDisplayed());
            System.out.println("  ✓ DS30 Card Detected: " + ds30.isDisplayed());
            System.out.println("  ✓ DSES Card Detected: " + dses.isDisplayed());
        } catch (Exception e) {
            System.err.println("[MARKET] Error detecting indices: " + e.getMessage());
        }
    }

    public static void verifyTurnoverAndStats(AndroidDriver driver) {
        System.out.println("[MARKET] Checking Turnover, Volume & Pressure Stats...");
        try {
            WebElement turnover = Driver.waitForVisibility(driver, AppiumBy.xpath("//*[contains(@text, 'Turnover')]"));
            WebElement volume = driver.findElement(AppiumBy.xpath("//*[contains(@text, 'Volume')]"));
            WebElement trade = driver.findElement(AppiumBy.xpath("//*[contains(@text, 'Trade')]"));

            System.out.println("  ✓ Turnover section present: " + turnover.isDisplayed());
            System.out.println("  ✓ Volume section present: " + volume.isDisplayed());
            System.out.println("  ✓ Trade section present: " + trade.isDisplayed());
        } catch (Exception e) {
            System.err.println("[MARKET] Could not read market stats: " + e.getMessage());
        }
    }

    public static void selectFeaturedCategory(AndroidDriver driver, String category) {
        // e.g. "Top Gainer", "Top Loser", "Top Trade", "Top Value" (Screenshot 4)
        System.out.println("[MARKET] Selecting Category Pill: " + category);
        try {
            WebElement pill = Driver.waitForClickable(driver,
                AppiumBy.xpath("//*[normalize-space(@text)='" + category + "']")
            );
            pill.click();
            System.out.println("[MARKET] Category selected: " + category);
        } catch (Exception e) {
            System.err.println("[MARKET] Failed to click pill: " + e.getMessage());
        }
    }
}
