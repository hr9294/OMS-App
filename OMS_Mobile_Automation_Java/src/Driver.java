import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Driver {

    // --- Configuration Constants ---
    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static final String APP_PACKAGE = "com.quantbd.oms";
    public static final String APP_ACTIVITY = ".ui.login.LoginActivity";
    public static final String DEFAULT_USER_CODE = "53196";
    public static final String DEFAULT_PASSWORD = "12345";
    public static final int TIMEOUT_SECONDS = 20;

    public static AndroidDriver initDriver() {
        try {
            System.out.println("[DRIVER] Starting UiAutomator2 Android Driver for UFT FAST OMS...");
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setDeviceName("Android Device")
                    .setAppPackage(APP_PACKAGE)
                    .setAppActivity(APP_ACTIVITY)
                    .setNoReset(false)
                    .setAutoGrantPermissions(true);

            AndroidDriver driver = new AndroidDriver(URI.create(APPIUM_SERVER_URL).toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            System.out.println("[DRIVER] App launched and session established.");
            return driver;
        } catch (Exception e) {
            System.err.println("[DRIVER ERROR] " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static WebElement waitForVisibility(AndroidDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(AndroidDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void takeScreenshot(AndroidDriver driver, String filename) {
        if (driver == null) return;
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(filename);
            FileHandler.copy(src, dest);
            System.out.println("[SCREENSHOT] Saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("[SCREENSHOT ERROR] " + e.getMessage());
        }
    }

    public static void quitDriver(AndroidDriver driver) {
        if (driver != null) {
            System.out.println("[DRIVER] Closing Appium driver session...");
            driver.quit();
        }
    }
}
