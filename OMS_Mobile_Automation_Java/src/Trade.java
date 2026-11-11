import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Trade {

    public static void openTrade(AndroidDriver driver) {
        System.out.println("-----------------------------------------------------");
        System.out.println("[TRADE MODULE] Navigating to Trade Terminal...");
        System.out.println("-----------------------------------------------------");

        // Bottom Navigation -> Trade (Candlestick Chart Icon)
        WebElement tradeTab = Driver.waitForClickable(driver,
            AppiumBy.xpath("//android.widget.TextView[@text='Trade'] | //*[contains(@text, 'Trade')]")
        );
        tradeTab.click();
        System.out.println("[TRADE] Trade tab selected.");
    }

    public static void selectSide(AndroidDriver driver, String side) {
        // "BUY" or "SELL" toggle (Screenshots 12, 13)
        System.out.println("[TRADE] Selecting order side: " + side);
        try {
            WebElement sideBtn = Driver.waitForClickable(driver,
                AppiumBy.xpath("//*[normalize-space(@text)='" + side.toUpperCase() + "']")
            );
            sideBtn.click();
        } catch (Exception e) {
            System.err.println("[TRADE] Could not toggle side: " + e.getMessage());
        }
    }

    public static void searchAndSelectSymbol(AndroidDriver driver, String symbol) {
        System.out.println("[TRADE] Searching for stock symbol: " + symbol);
        try {
            WebElement searchField = Driver.waitForVisibility(driver,
                AppiumBy.xpath("//android.widget.EditText[contains(@text, 'Search symbol') or contains(@hint, 'Search symbol')]")
            );
            searchField.clear();
            searchField.sendKeys(symbol);
        } catch (Exception e) {
            System.err.println("[TRADE] Could not enter symbol: " + e.getMessage());
        }
    }

    public static void enterOrderDetails(AndroidDriver driver, String quantity, String rate) {
        System.out.println("[TRADE] Setting Quantity=" + quantity + ", Rate=" + rate);
        try {
            // Locate Order Qty
            WebElement qtyField = driver.findElement(
                AppiumBy.xpath("//*[contains(@text, 'Order Qty')]/following-sibling::android.widget.EditText | //android.widget.EditText[@index='1']")
            );
            qtyField.clear();
            qtyField.sendKeys(quantity);

            // Locate Order Rate
            WebElement rateField = driver.findElement(
                AppiumBy.xpath("//*[contains(@text, 'Order rate')]/following-sibling::android.widget.EditText | //android.widget.EditText[@index='2']")
            );
            rateField.clear();
            rateField.sendKeys(rate);
        } catch (Exception e) {
            System.err.println("[TRADE] Could not enter qty/rate: " + e.getMessage());
        }
    }

    public static void submitOrder(AndroidDriver driver, String side) {
        // Green "BUY" button or Red "SELL" button at bottom
        System.out.println("[TRADE] Submitting " + side.toUpperCase() + " order...");
        try {
            WebElement submitBtn = Driver.waitForClickable(driver,
                AppiumBy.xpath("//android.widget.Button[normalize-space(@text)='" + side.toUpperCase() + "']")
            );
            submitBtn.click();
            System.out.println("[TRADE] Order submitted.");
        } catch (Exception e) {
            System.err.println("[TRADE] Order submit button failed: " + e.getMessage());
        }
    }
}
