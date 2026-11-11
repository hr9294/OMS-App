import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class OMSMobile_Driver {

    // --- Configuration Constants ---
    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static final String APP_PACKAGE = "com.quantbd.oms";
    public static final String APP_ACTIVITY = ".ui.login.LoginActivity";
    public static final String DEFAULT_USERNAME = "Testadmin";
    public static final String DEFAULT_PASSWORD = "12345";
    public static final int TIMEOUT_SECONDS = 20;

    public static AndroidDriver initDriver() {
        try {
            System.out.println("[DRIVER] Initializing UiAutomator2 for Android...");
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setDeviceName("Android Device")
                    .setAppPackage(APP_PACKAGE)
                    .setAppActivity(APP_ACTIVITY)
                    .setNoReset(false)
                    .setAutoGrantPermissions(true);

            System.out.println("[DRIVER] Connecting to Appium at " + APPIUM_SERVER_URL + "...");
            AndroidDriver driver = new AndroidDriver(URI.create(APPIUM_SERVER_URL).toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            System.out.println("[DRIVER] Mobile driver connected successfully.");
            return driver;
        } catch (Exception e) {
            System.err.println("[DRIVER ERROR] " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void takeScreenshot(AndroidDriver drv, String filename) {
        if (drv == null) return;
        try {
            File src = ((TakesScreenshot) drv).getScreenshotAs(OutputType.FILE);
            File dest = new File(filename);
            FileHandler.copy(src, dest);
            System.out.println("[SCREENSHOT] Saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("[SCREENSHOT ERROR] " + e.getMessage());
        }
    }

    public static void quitDriver(AndroidDriver drv) {
        if (drv != null) {
            System.out.println("[DRIVER] Closing mobile session...");
            drv.quit();
        }
    }
}
