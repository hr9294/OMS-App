package com.quantbd.oms.mobile.tests;

import com.quantbd.oms.mobile.config.AppConfig;
import com.quantbd.oms.mobile.driver.DriverManager;
import com.quantbd.oms.mobile.pages.DashboardPage;
import com.quantbd.oms.mobile.pages.LoginPage;

import io.appium.java_client.android.AndroidDriver;

public class OMSMobileTest {

    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("   RUNNING OMS MOBILE AUTOMATION TEST (JAVA / ECLIPSE) ");
        System.out.println("=====================================================");

        AndroidDriver driver = null;
        try {
            driver = DriverManager.getDriver();

            LoginPage loginPage = new LoginPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);

            // Step 1: Login
            loginPage.performFullLogin(AppConfig.DEFAULT_USERNAME, AppConfig.DEFAULT_PASSWORD);

            // Step 2: Verification
            boolean isLoaded = dashboardPage.isDashboardLoaded();
            if (isLoaded) {
                System.out.println("\n>>> [PASS] MOBILE DASHBOARD VERIFIED SUCCESSFULLY! <<<");
                DriverManager.takeScreenshot("mobile_dashboard_success.png");
            } else {
                System.err.println("\n>>> [FAIL] DASHBOARD VERIFICATION FAILED! <<<");
                DriverManager.takeScreenshot("mobile_dashboard_failed.png");
            }

        } catch (Exception e) {
            System.err.println("\n[ERROR] Test run failed: " + e.getMessage());
            e.printStackTrace();
            DriverManager.takeScreenshot("mobile_test_exception.png");
        } finally {
            DriverManager.quitDriver();
            System.out.println("Execution finished.");
        }
    }
}
