package com.learning.appium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    private final String appiumServerIP = "127.0.0.1";
    private final int appiumServerPort = 4724;
    private final String appiumServerPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
    private final String deviceName = "SubodhPixel";
    private final String apkPath = "src/test/resources/app/ApiDemos-debug.apk";

    private AppiumDriverLocalService service;
    protected AndroidDriver driver;

    @BeforeTest
    public void startAppiumServer(){
        // Start Appium server automatically
        service = new AppiumServiceBuilder().withAppiumJS(new File(appiumServerPath))
                .withIPAddress(appiumServerIP)
                .usingPort(appiumServerPort)
                .build();

        service.start();
    }
    @AfterTest
    public void stopAppiumServer(){
        service.stop();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        String appPath = new File(apkPath).getAbsolutePath();
        options.setApp(appPath);
        String ipPrefix = "http://";
        driver = new AndroidDriver(new URL(ipPrefix + appiumServerIP + ":" + appiumServerPort),options);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public void longPress(WebElement elem,int duration){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) elem).getId(),
                "duration",duration
        ));
    }
    public void swipe(WebElement elem, String direction, double percent){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) elem).getId(),
                "direction", direction,
                "percent", percent
        ));
    }
    public void dragAndDrop(WebElement elem, int xCoordinate, int yCoordinate){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) elem).getId(),
                "endX", xCoordinate,
                "endY", yCoordinate
        ));
    }
    public By scrollIntoViewUsingUiAutomator(String elementText){
        return AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementText+"\"));");
    }
}
