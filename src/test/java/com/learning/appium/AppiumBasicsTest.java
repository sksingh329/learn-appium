package com.learning.appium;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class AppiumBasicsTest extends BaseTest{
    private By preferenceButton = AppiumBy.accessibilityId("Preference");
    private By preferenceDependenciesButton = AppiumBy.accessibilityId("3. Preference dependencies");
    private By wifiCheckBox = AppiumBy.id("android:id/checkbox");
    private By wifiSettingsButton = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']");
    private By wifiSettingsInput = AppiumBy.id("android:id/edit");
    private By wifiSettingsOkButton = AppiumBy.className("android.widget.Button");
    private By wifiSettingsPopupTitle = AppiumBy.id("android:id/alertTitle");


    @Test
    public void DemoAppBasicsTest() {
        // Appium Supported Locator for Android - XPath, id, accessibility id, classname, androidUIAutomation
        driver.findElement(preferenceButton).click();
        driver.findElement(preferenceDependenciesButton).click();
        //Before clicking, check if checkbox is checked or not
        driver.findElement(wifiCheckBox).click();

        driver.findElement(wifiSettingsButton).click();

        String wifiSettingsAlertTitle = driver.findElement(wifiSettingsPopupTitle).getText();
        Assert.assertEquals(wifiSettingsAlertTitle,"WiFi settings");
        driver.findElement(wifiSettingsInput).sendKeys("subodh wifi");

        List<WebElement> wifiSettingsButtons = driver.findElements(wifiSettingsOkButton);

        for (WebElement button : wifiSettingsButtons){
            if(button.getAttribute("text").equals("OK")){
                button.click();
            }
        }
    }

}
