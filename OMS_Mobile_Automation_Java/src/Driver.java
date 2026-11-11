import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.Duration;
import java.util.Properties;

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

    // --- Configuration Constants (Can be overridden by local.properties) ---
    public static String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static String APP_PACKAGE = "com.quantbd.oms";
    public static String APP_ACTIVITY = ".ui.login.LoginActivity";
    public static String DEFAULT_USER_CODE = "53196";
    public static String DEFAULT_PASSWORD = "12345";
    public static int TIMEOUT_SECONDS = 20;

    static {
        loadLocalProperties();
    }

    private static void loadLocalProperties() {
        Properties prop = new Properties();
        try {
            File localFile = new File("local.properties");
            if (localFile.exists()) {
                try (FileInputStream fis = new FileInputStream(localFile)) {
                    prop.load(fis);
                    System.out.println("[CONFIG] Loaded settings from local.properties");
                }
            } else {
                InputStream is = Driver.class.getResourceAsStream("/local.properties");
                if (is != null) {
                    prop.load(is);
                    System.out.println("[CONFIG] Loaded settings from classpath local.properties");
                }
            }

            if (prop.getProperty("appium.server.url") != null) APPIUM_SERVER_URL = prop.getProperty("appium.server.url").trim();
            if (prop.getProperty("app.package") != null) APP_PACKAGE = prop.getProperty("app.package").trim();
            if (prop.getProperty("app.activity") != null) APP_ACTIVITY = prop.getProperty("app.activity").trim();
            if (prop.getProperty("user.code") != null) DEFAULT_USER_CODE = prop.getProperty("user.code").trim();
            if (prop.getProperty("user.password") != null) DEFAULT_PASSWORD = prop.getProperty("user.password").trim();
            if (prop.getProperty("timeout.seconds") != null) TIMEOUT_SECONDS = Integer.parseInt(prop.getProperty("timeout.seconds").trim());

        } catch (Exception e) {
            System.out.println("[CONFIG] Using default configuration.");
        }
    }

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
