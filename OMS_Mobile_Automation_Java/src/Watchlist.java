import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Watchlist {

    public static void openWatchlist(AndroidDriver driver) {
        System.out.println("-----------------------------------------------------");
        System.out.println("[WATCHLIST MODULE] Navigating to Watchlist...");
        System.out.println("-----------------------------------------------------");

        // Bottom Navigation -> Watchlist (Heart Icon)
        WebElement watchlistTab = Driver.waitForClickable(driver,
            AppiumBy.xpath("//android.widget.TextView[@text='Watchlist'] | //*[contains(@text, 'Watchlist')]")
        );
        watchlistTab.click();
        System.out.println("[WATCHLIST] Watchlist tab selected.");
    }

    public static void switchExchange(AndroidDriver driver, String exchange) {
        // Exchange toggle: DSE or CSE (Screenshot 2)
        System.out.println("[WATCHLIST] Switching exchange to: " + exchange);
        try {
            WebElement exchToggle = Driver.waitForClickable(driver,
                AppiumBy.xpath("//*[normalize-space(@text)='" + exchange + "']")
            );
            exchToggle.click();
        } catch (Exception e) {
            System.err.println("[WATCHLIST] Could not switch exchange: " + e.getMessage());
        }
    }

    public static boolean verifySymbol(AndroidDriver driver, String symbolName) {
        System.out.println("[WATCHLIST] Checking for symbol: " + symbolName);
        try {
            WebElement symbolElem = Driver.waitForVisibility(driver,
                AppiumBy.xpath("//*[contains(@text, '" + symbolName + "')]")
            );
            boolean isVisible = symbolElem.isDisplayed();
            System.out.println("[WATCHLIST] Symbol '" + symbolName + "' is visible: " + isVisible);
            return isVisible;
        } catch (Exception e) {
            System.err.println("[WATCHLIST] Symbol not found: " + symbolName);
            return false;
        }
    }

    public static void printWatchlistSymbols(AndroidDriver driver) {
        System.out.println("[WATCHLIST] Extracting visible Watchlist items...");
        try {
            List<WebElement> items = driver.findElements(
                AppiumBy.xpath("//android.widget.TextView[contains(@text, '[A]') or contains(@text, '[N]') or contains(@text, '[Z]')]")
            );
            for (WebElement item : items) {
                if (item.isDisplayed()) {
                    System.out.println("  -> Watchlist Symbol: " + item.getText());
                }
            }
        } catch (Exception ignored) {}
    }
}
