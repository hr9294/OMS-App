import io.appium.java_client.android.AndroidDriver;

public class OMSMobilemain {

    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("   UFT FAST / OMS MOBILE APP AUTOMATION TEST SUITE   ");
        System.out.println("=====================================================");

        AndroidDriver driver = null;

        try {
            // 1. Launch App Driver
            driver = Driver.initDriver();

            // 2. Module 1: Login (Screenshot 1: Client code 53196, Password 12345)
            Login.login(driver, Driver.DEFAULT_USER_CODE, Driver.DEFAULT_PASSWORD);
            Driver.takeScreenshot(driver, "1_login_success.png");

            // 3. Module 2: Watchlist (Screenshot 2: Symbols like ABBANK, DSE/CSE toggle)
            Watchlist.openWatchlist(driver);
            Watchlist.switchExchange(driver, "DSE");
            Watchlist.verifySymbol(driver, "ABBANK");
            Watchlist.printWatchlistSymbols(driver);
            Driver.takeScreenshot(driver, "2_watchlist_success.png");

            // 4. Module 3: Market Overview (Screenshots 3-6: Indices, Turnover, Gainers)
            Market.openMarket(driver);
            Market.verifyMarketIndices(driver);
            Market.verifyTurnoverAndStats(driver);
            Driver.takeScreenshot(driver, "3_market_success.png");

            // 5. Module 4: Portfolio (Screenshots 7-11: Holdings, Limits, Summary)
            Portfolio.openPortfolio(driver);
            Portfolio.verifyClientCode(driver, Driver.DEFAULT_USER_CODE);
            Portfolio.verifyHoldingsListed(driver);
            Portfolio.switchSubTab(driver, "Order Summary");
            Portfolio.switchSubTab(driver, "Account Limits");
            Portfolio.switchSubTab(driver, "Portfolio");
            Driver.takeScreenshot(driver, "4_portfolio_success.png");

            // 6. Module 5: Trade Terminal (Screenshots 12-13: Buy/Sell screen)
            Trade.openTrade(driver);
            Trade.selectSide(driver, "BUY");
            Trade.searchAndSelectSymbol(driver, "ABBANK");
            Trade.enterOrderDetails(driver, "10", "32.5");
            Driver.takeScreenshot(driver, "5_trade_success.png");

            // 7. Module 6: Menu & User Profile (Screenshots 14-15: MD HASIBUR RAHMAN)
            Menu.openQuickMenu(driver);
            Menu.verifyUserProfile(driver, "MD HASIBUR RAHMAN");
            Driver.takeScreenshot(driver, "6_menu_success.png");

            System.out.println("\n>>> [PASS] ALL 6 OMS MOBILE MODULES TESTED SUCCESSFULLY! <<<");

        } catch (Exception e) {
            System.err.println("\n[ERROR] Test run failed: " + e.getMessage());
            e.printStackTrace();
            if (driver != null) {
                Driver.takeScreenshot(driver, "test_failure.png");
            }
        } finally {
            // Teardown
            if (driver != null) {
                Driver.quitDriver(driver);
            }
            System.out.println("Execution finished.");
        }
    }
}
