package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;

//@Listeners({ TestListener.class })
@Epic("AllureTest")
public class AllureTest {
    
    @Description("Test Description: class AllureTest; testAllure1().")
    @Test
    public void testAllure1() throws Exception {
        System.out.println("surefire.reports.directory = "
                + System.getProperty("surefire.reports.directory"));
        System.out.println("selenium-version = "
                + System.getProperty("selenium.version"));
        System.out.println("database-password = "
                + System.getProperty("database.password"));
        Assert.assertTrue(true);
    }

    @Test
    public void testAllure2() throws Exception {
        System.out.println("Working ...");
    }

}
