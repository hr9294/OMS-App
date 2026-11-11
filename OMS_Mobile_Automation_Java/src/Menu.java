import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Menu {

    public static void openQuickMenu(AndroidDriver driver) {
        System.out.println("-----------------------------------------------------");
        System.out.println("[MENU MODULE] Opening Quick Menu (Center Launcher)...");
        System.out.println("-----------------------------------------------------");

        // The center multi-color dots button on the bottom navigation bar (Screenshot 14)
        try {
            WebElement centerButton = Driver.waitForClickable(driver,
                AppiumBy.xpath("//android.widget.ImageView[contains(@resource-id, 'center') or contains(@resource-id, 'menu_launcher')] | //android.view.ViewGroup[contains(@clickable, 'true') and @index='2']")
            );
            centerButton.click();
            System.out.println("[MENU] Center Quick Menu opened.");
        } catch (Exception e) {
            System.err.println("[MENU] Could not open quick launcher: " + e.getMessage());
        }
    }

    public static void verifyUserProfile(AndroidDriver driver, String expectedName) {
        // Screenshot 14 & 15: MD HASIBUR RAHMAN
        System.out.println("[MENU] Verifying Profile Name: " + expectedName);
        try {
            WebElement nameElem = Driver.waitForVisibility(driver,
                AppiumBy.xpath("//*[contains(@text, '" + expectedName + "')]")
            );
            System.out.println("  ✓ User profile confirmed: " + nameElem.getText());
        } catch (Exception e) {
            System.err.println("[MENU] User profile name not found: " + e.getMessage());
        }
    }

    public static void openFullMenu(AndroidDriver driver) {
        // Screenshot 14: Click the grid icon labelled "Menu"
        System.out.println("[MENU] Opening Full Menu list...");
        try {
            WebElement fullMenuBtn = Driver.waitForClickable(driver,
                AppiumBy.xpath("//*[normalize-space(@text)='Menu'] | //*[contains(@resource-id, 'menu_btn')]")
            );
            fullMenuBtn.click();
            System.out.println("[MENU] Full Menu page opened.");
        } catch (Exception e) {
            System.err.println("[MENU] Could not open full menu: " + e.getMessage());
        }
    }

    public static void clickMenuItem(AndroidDriver driver, String menuText) {
        // e.g. "Limit Request", "Deposit/Withdraw", "Settings", "Reports", "Logout" (Screenshot 15)
        System.out.println("[MENU] Tapping menu item: " + menuText);
        try {
            WebElement item = Driver.waitForClickable(driver,
                AppiumBy.xpath("//*[contains(@text, '" + menuText + "')]")
            );
            item.click();
            System.out.println("[MENU] Selected: " + menuText);
        } catch (Exception e) {
            System.err.println("[MENU] Failed to select " + menuText + ": " + e.getMessage());
        }
    }

    public static void logout(AndroidDriver driver) {
        System.out.println("[MENU] Performing Logout...");
        clickMenuItem(driver, "Logout");
    }
}
