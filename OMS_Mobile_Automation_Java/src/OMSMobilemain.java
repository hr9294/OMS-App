import io.appium.java_client.android.AndroidDriver;

public class OMSMobilemain {

    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("  STARTING OMS MOBILE APP AUTOMATION (JAVA / ECLIPSE) ");
        System.out.println("=====================================================");

        AndroidDriver driver = null;
        try {
            // Step 1: Initialize Mobile Driver
            driver = OMSMobile_Driver.initDriver();

            // Step 2: Login
            OMSMobile_Login.login(driver, OMSMobile_Driver.DEFAULT_USERNAME, OMSMobile_Driver.DEFAULT_PASSWORD);

            // Step 3: Verify Dashboard
            boolean isLoaded = OMSMobile_Dashboard.verifyDashboard(driver);

            if (isLoaded) {
                System.out.println("\n>>> [PASS] OMS MOBILE AUTOMATION TEST PASSED! <<<");
                OMSMobile_Driver.takeScreenshot(driver, "mobile_dashboard_success.png");
            } else {
                System.err.println("\n>>> [FAIL] DASHBOARD VERIFICATION FAILED! <<<");
                OMSMobile_Driver.takeScreenshot(driver, "mobile_dashboard_failed.png");
            }

        } catch (Exception e) {
            System.err.println("\n[ERROR] Test failed with exception: " + e.getMessage());
            e.printStackTrace();
            if (driver != null) {
                OMSMobile_Driver.takeScreenshot(driver, "mobile_test_error.png");
            }
        } finally {
            // Teardown
            if (driver != null) {
                OMSMobile_Driver.quitDriver(driver);
            }
            System.out.println("Test session completed.");
        }
    }
}
