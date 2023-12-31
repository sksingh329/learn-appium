package com.learning.appium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoAppAdvancedTest extends BaseTest{
    private final By viewsButton = AppiumBy.accessibilityId("Views");
    private final By expandableListsButton = AppiumBy.accessibilityId("Expandable Lists");
    private final By customAdapterButton = AppiumBy.accessibilityId("1. Custom Adapter");
    private final By peopleNamesTextView = AppiumBy.xpath("//android.widget.TextView[@text='People Names']");
    private final By sampleMenuPopupLabel = AppiumBy.id("android:id/title");
    private final By webViewButton = scrollIntoViewUsingUiAutomator("WebView");
    private final By galleryButton = AppiumBy.accessibilityId("Gallery");
    private final By photosButton = AppiumBy.accessibilityId("1. Photos");
    private final By dragAndDropButton = AppiumBy.accessibilityId("Drag and Drop");
    private final By dragDot1 = AppiumBy.accessibilityId("io.appium.android.apis:id/drag_dot_1");
    private final By dragDot2 = AppiumBy.accessibilityId("io.appium.android.apis:id/drag_dot_3");


    @Test
    public void longPressGestureTest(){
        driver.findElement(viewsButton).click();
        driver.findElement(expandableListsButton).click();
        driver.findElement(customAdapterButton).click();

        WebElement peopleNamesElem = driver.findElement(peopleNamesTextView);

        longPress(peopleNamesElem,2000);

        WebElement sampleMenuPopupElem = driver.findElement(sampleMenuPopupLabel);
        Assert.assertEquals(sampleMenuPopupElem.getText(),"Sample menu");
        Assert.assertTrue(sampleMenuPopupElem.isDisplayed());
    }
    @Test
    public void scrollTest(){
        driver.findElement(viewsButton).click();
        driver.findElement(webViewButton).click();
    }
    @Test
    public void swipeTest(){
        driver.findElement(viewsButton).click();
        driver.findElement(galleryButton).click();
        driver.findElement(photosButton).click();
        WebElement firstImageElem = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(firstImageElem.getAttribute("focusable"),"true");
        swipe(firstImageElem,"left",0.75);
        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),"false");
    }
    @Test
    public void dragAndDropTest(){
        driver.findElement(viewsButton).click();
        driver.findElement(dragAndDropButton).click();
        WebElement sourceElement = driver.findElement(dragDot1);
        dragAndDrop(sourceElement,800,600);
    }
}
