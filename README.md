# OMS-App: Mobile Automation Testing Suite

Enterprise Mobile Appium test automation framework for the **OMS (Order Management System) Android Application**.

## Overview
This repository contains two dedicated, fully configured test suites:
- **`OMS_Mobile_Automation_Java/`** - Java + Appium 2.x + Selenium 4 + TestNG (Ready for **Eclipse**)
- **`OMS_Mobile_Automation_Python/`** - Python 3 + Appium-Python-Client + Pytest (Ready for **VS Code**)

---

## 1. Quick Start - Java (Eclipse)
1. Open Eclipse -> **File** -> **Import...** -> **Maven** -> **Existing Maven Projects** (or **Existing Projects into Workspace**).
2. Select `OMS_Mobile_Automation_Java`.
3. Right-click `OMSMobileTest.java` -> **Run As** -> **Java Application**.

## 2. Quick Start - Python (VS Code)
1. Open folder `OMS_Mobile_Automation_Python` in VS Code.
2. Install requirements:
   ```bash
   pip install -r requirements.txt
   ```
3. Run the automation:
   ```bash
   python main.py
   ```
   Or press **F5** in VS Code.

---

## Prerequisites
- **Appium Server 2.x**: Running on `http://127.0.0.1:4723` (`appium`).
- **Android Device / Emulator**: Connected with USB debugging enabled (`adb devices`).
- **Target App**: Package `com.quantbd.oms`, Activity `.ui.login.LoginActivity`.
