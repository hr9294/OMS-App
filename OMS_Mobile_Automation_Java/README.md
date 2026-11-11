# OMS Mobile Automation (Java / Eclipse)

A standalone Appium 2.x mobile test automation project for the OMS Android Application.

## How to Import into Eclipse

1. Open Eclipse.
2. Go to **File** -> **Import...**
3. Select **Maven** -> **Existing Maven Projects** (or **General** -> **Existing Projects into Workspace**).
4. Click **Browse...** and select:
   `D:\Anitgravity\OMS_Mobile_Automation_Java`
5. Click **Finish**.

## How to Run

1. Make sure Appium is running:
   ```bash
   appium
   ```
2. Connect your Android device or emulator (`adb devices`).
3. In Eclipse, right-click `OMSMobileTest.java` -> **Run As** -> **Java Application**.
