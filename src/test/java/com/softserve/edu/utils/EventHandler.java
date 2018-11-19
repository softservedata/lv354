package com.softserve.edu.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.util.Arrays;


public class EventHandler extends AbstractWebDriverEventListener {
    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Driver navigates to " + s + " page");
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Driver navigated to " + s + " page");

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("WebElement with \"" + by + "\"  locator is to be found ");
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("WebElement with \"" + by + "\"  locator is found ");

    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println(webElement + " element is to be clicked ");

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println(webElement + " element is clicked ");

    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("The " + Arrays.toString(charSequences) + " is to be typed into " + webElement + " field.");
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("The " + Arrays.toString(charSequences) + " is typed into " + webElement + " field.");

    }


    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        System.out.println("The exception " + throwable + " need to be handled");
    }
}

   /* @Override
    public void beforeAlertAccept(WebDriver webDriver) {
    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {
    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {
    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        CustomReporter.log("Navigate to " + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        CustomReporter.log("Navigate back");
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        CustomReporter.log("Current URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        CustomReporter.log("Navigate forward");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        CustomReporter.log("Current URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        CustomReporter.log("Refresh page");
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        CustomReporter.log("Current URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        CustomReporter.log("Search for element " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        if (element != null) {
            CustomReporter.log("Found element " + element.getTagName());
        }
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        CustomReporter.log("Click on element " + element.getTagName());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String value = Arrays.stream(keysToSend).map(CharSequence::toString).collect(Collectors.joining());
        CustomReporter.log(String.format("Change value of %s: %s\n", element.getTagName(), value));
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        CustomReporter.log(String.format("Changed element " + element.getTagName()));
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        CustomReporter.log("Execute script: " + script);
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        CustomReporter.log("Script executed");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        // already logged by reporter
    }
}
*/