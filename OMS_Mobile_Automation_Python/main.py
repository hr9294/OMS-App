"""
OMS / UFT FAST Mobile App Automation Runner (Python / VS Code)
Executes all 6 Modules: Login, Watchlist, Market, Portfolio, Trade, Menu
"""

import time
from driver_factory import create_driver, take_screenshot
from login import Login
from watchlist import Watchlist
from market import Market
from portfolio import Portfolio
from trade import Trade
from menu import Menu


def main():
    print("=====================================================")
    print("   UFT FAST / OMS MOBILE APP AUTOMATION (VS CODE)    ")
    print("=====================================================")

    driver = None
    try:
        # 1. Start Appium Driver
        driver = create_driver()

        # 2. Login Module (Screenshot 1: Client code 53196, Password 12345)
        Login.login(driver, user_code="53196", password="12345")
        take_screenshot(driver, "1_login_success.png")

        # 3. Watchlist Module (Screenshot 2: Watchlist & symbols)
        Watchlist.open_watchlist(driver)
        Watchlist.switch_exchange(driver, "DSE")
        Watchlist.verify_symbol(driver, "ABBANK")
        take_screenshot(driver, "2_watchlist_success.png")

        # 4. Market Module (Screenshots 3-6: Indices & stats)
        Market.open_market(driver)
        Market.verify_market_data(driver)
        take_screenshot(driver, "3_market_success.png")

        # 5. Portfolio Module (Screenshots 7-11: Holdings & subtabs)
        Portfolio.open_portfolio(driver)
        Portfolio.verify_client_code(driver, "53196")
        Portfolio.switch_sub_tab(driver, "Order Summary")
        Portfolio.switch_sub_tab(driver, "Portfolio")
        take_screenshot(driver, "4_portfolio_success.png")

        # 6. Trade Module (Screenshots 12-13: Buy/Sell screen)
        Trade.open_trade(driver)
        Trade.select_side(driver, "BUY")
        Trade.enter_order(driver, symbol="ABBANK", qty="10", rate="32.5")
        take_screenshot(driver, "5_trade_success.png")

        # 7. Menu Module (Screenshots 14-15: MD HASIBUR RAHMAN)
        Menu.open_quick_menu(driver)
        Menu.verify_profile_name(driver, "MD HASIBUR RAHMAN")
        take_screenshot(driver, "6_menu_success.png")

        print("\n>>> [PASS] ALL 6 OMS MOBILE MODULES COMPLETED SUCCESSFULLY! <<<")

    except Exception as e:
        print(f"\n[ERROR] Test run encountered error: {e}")
        if driver:
            take_screenshot(driver, "error_screenshot.png")
        raise
    finally:
        if driver:
            print("[TEARDOWN] Quitting mobile driver...")
            time.sleep(2)
            driver.quit()
        print("Session complete.")


if __name__ == "__main__":
    main()
