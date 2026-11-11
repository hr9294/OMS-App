package com.quantbd.oms.mobile.driver;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.quantbd.oms.mobile.config.AppConfig;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverManager {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static AndroidDriver initDriver() {
        try {
            System.out.println("[DRIVER] Configuring Android UiAutomator2 options...");
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setDeviceName("Android Device")
                    .setAppPackage(AppConfig.APP_PACKAGE)
                    .setAppActivity(AppConfig.APP_ACTIVITY)
                    .setNoReset(false)
                    .setAutoGrantPermissions(true);

            System.out.println("[DRIVER] Connecting to Appium at " + AppConfig.APPIUM_SERVER_URL + "...");
            driver = new AndroidDriver(URI.create(AppConfig.APPIUM_SERVER_URL).toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(AppConfig.IMPLICIT_WAIT_SECONDS));
            System.out.println("[DRIVER] Driver successfully connected and session started.");
            return driver;
        } catch (Exception e) {
            System.err.println("[DRIVER ERROR] Failed to connect to Appium: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void takeScreenshot(String fileName) {
        if (driver == null) return;
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(fileName);
            FileHandler.copy(src, dest);
            System.out.println("[SCREENSHOT] Saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("[SCREENSHOT ERROR] Could not save screenshot: " + e.getMessage());
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            System.out.println("[DRIVER] Closing Appium mobile session...");
            driver.quit();
            driver = null;
        }
    }
}
