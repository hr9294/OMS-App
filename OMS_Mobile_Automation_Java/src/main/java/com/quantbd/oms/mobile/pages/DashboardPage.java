package com.quantbd.oms.mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class DashboardPage extends BasePage {

    private By dashboardElement = AppiumBy.xpath(
        "//*[contains(@text, 'Market') or contains(@text, 'Watchlist') or contains(@text, 'Portfolio') or contains(@resource-id, 'dashboard')]"
    );

    public DashboardPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDashboardLoaded() {
        System.out.println("[DASHBOARD] Verifying dashboard landing...");
        try {
            WebElement elem = waitForVisibility(dashboardElement);
            return elem.isDisplayed();
        } catch (Exception e) {
            System.err.println("[DASHBOARD] Could not detect dashboard element: " + e.getMessage());
            return false;
        }
    }
}
