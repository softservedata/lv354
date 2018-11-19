package com.softserve.edu;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class RegistrationTest {
    private WebDriver driver;
    //@BeforeTest
    public void prepareForTest(){
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://taqc-opencart.epizy.com/");
        driver.findElement(By.name("search")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).click();
        Assert.assertTrue((driver.findElements(By.cssSelector("#content h1")).size() != 0), "Register page not found");
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("test1");
        //Second name
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("test1");
        //E-mail
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("test1@test.test");
        //Telephone
        driver.findElement(By.name("telephone")).click();
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("800800800");
        //Fax
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys("800800800");
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("ITAcademy");
        //Address1
        driver.findElement(By.name("address_1")).click();
        driver.findElement(By.name("address_1")).clear();
        driver.findElement(By.name("address_1")).sendKeys("test1");
        //Address2
        driver.findElement(By.name("address_2")).click();
        driver.findElement(By.name("address_2")).clear();
        driver.findElement(By.name("address_2")).sendKeys("test1");
        //City
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Kyiv");
        //Post Code
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("test");
        //Country
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.xpath("//option[text()='Ukraine']")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.xpath("//option[text()='Kyiv']")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("qwerty");
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        driver.findElement(By.name("confirm")).clear();
        driver.findElement(By.name("confirm")).sendKeys("qwerty");
        //Accept
        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
        driver.quit();
    }
    //@AfterTest
    public void stripping(){
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://192.168.234.130/opencart/upload/admin");

        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("altair");

        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("aezakmi");

        driver.findElement(By.xpath("//div[@class='text-right']//button")).click();

        driver.findElement(By.xpath("//div[text()='Total Customers ']/../div[@class='tile-footer']/a")).click();

        driver.findElement(By.xpath("//div[@class='table-responsive']//td[@style='width: 1px;']")).click();

        driver.findElement(By.xpath("//div[@class='pull-right']//button[@class='btn btn-danger']")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.quit();
    }
    @BeforeClass
    public void powerOnChromeDriver() {
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void powerOffChromeDriver() {
        driver.quit();
    }

    @BeforeMethod
    public void goToRegistrationPage() {
        driver.get("http://taqc-opencart.epizy.com/");

        driver.findElement(By.name("search")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).click();
        Assert.assertTrue((driver.findElements(By.cssSelector("#content h1")).size() != 0), "Register page not found");
    }

    @AfterMethod
    public void quitRegistration() {
    }

    @Test
    public void trueRegistrationTest() {
        //Personal Details
        //First name
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("Altair");
        //Second name
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Ahad");
        //E-mail
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("testemail@test.mail");
        //Telephone
        driver.findElement(By.name("telephone")).click();
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("0630234560");
        //Fax
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys("8800555");
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("ITAcademy");
        //Address1
        driver.findElement(By.name("address_1")).click();
        driver.findElement(By.name("address_1")).clear();
        driver.findElement(By.name("address_1")).sendKeys("Address1");
        //Address2
        driver.findElement(By.name("address_2")).click();
        driver.findElement(By.name("address_2")).clear();
        driver.findElement(By.name("address_2")).sendKeys("Address2");
        //City
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Kyiv");
        //Post Code
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("79007");
        //Country
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.xpath("//option[text()='Ukraine']")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.xpath("//option[text()='Kyiv']")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("aezakmi");
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        driver.findElement(By.name("confirm")).clear();
        driver.findElement(By.name("confirm")).sendKeys("aezakmi");
        //Accept
        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
    }

    /////////////////////////////////////////////////////////////////////////////
    @Test(expectedExceptions = java.lang.IllegalStateException.class)
    public void voidRegistration() {
        //Personal Details
        //First name
        driver.findElement(By.name("firstname")).click();
        //Second name
        driver.findElement(By.name("lastname")).click();
        //E-mail
        driver.findElement(By.name("email")).click();
        //Telephone
        driver.findElement(By.name("telephone")).click();
        //Fax
        driver.findElement(By.name("fax")).click();
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        //Address1
        driver.findElement(By.name("address_1")).click();
        //Address2
        driver.findElement(By.name("address_2")).click();
        //City
        driver.findElement(By.name("city")).click();
        //Post Code
        driver.findElement(By.name("postcode")).click();
        //Country
        driver.findElement(By.id("input-country")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        //Accept
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
    }

    /////////////////////////////////////////////////////////////////////////////
    @Test(expectedExceptions = java.lang.IllegalStateException.class)//Expected exception with screenshot  NOT DONE!
    public void duplicateEmailInRegistration() {
        //Personal Details
        //First name
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("test1");
        //Second name
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("test1");
        //E-mail
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("test1@test.test");
        //Telephone
        driver.findElement(By.name("telephone")).click();
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("800800800");
        //Fax
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys("800800800");
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("ITAcademy");
        //Address1
        driver.findElement(By.name("address_1")).click();
        driver.findElement(By.name("address_1")).clear();
        driver.findElement(By.name("address_1")).sendKeys("test1");
        //Address2
        driver.findElement(By.name("address_2")).click();
        driver.findElement(By.name("address_2")).clear();
        driver.findElement(By.name("address_2")).sendKeys("test1");
        //City
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Kyiv");
        //Post Code
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("test");
        //Country
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.xpath("//option[text()='Ukraine']")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.xpath("//option[text()='Kyiv']")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("qwerty");
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        driver.findElement(By.name("confirm")).clear();
        driver.findElement(By.name("confirm")).sendKeys("qwerty");
        //Accept
        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
    }

    /////////////////////////////////////////////////////////////////////////////
    @Test(expectedExceptions = java.lang.IllegalStateException.class)//Expected exception with screenshot  NOT DONE!
    public void registrationWithOutPrivacyPolicyButton() {
        //Personal Details
        //First name
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(" Privacy");
        //Second name
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Policy");
        //E-mail
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("testPrivacyPolicy@test.test");
        //Telephone
        driver.findElement(By.name("telephone")).click();
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("800800800");
        //Fax
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys("800800800");
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("ITAcademy");
        //Address1
        driver.findElement(By.name("address_1")).click();
        driver.findElement(By.name("address_1")).clear();
        driver.findElement(By.name("address_1")).sendKeys("Policy");
        //Address2
        driver.findElement(By.name("address_2")).click();
        driver.findElement(By.name("address_2")).clear();
        driver.findElement(By.name("address_2")).sendKeys("Privacy");
        //City
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Kyiv");
        //Post Code
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("PrivacyPolicy");
        //Country
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.xpath("//option[text()='Ukraine']")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.xpath("//option[text()='Kyiv']")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("qwerty");
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        driver.findElement(By.name("confirm")).clear();
        driver.findElement(By.name("confirm")).sendKeys("qwerty");
        //Accept
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
    }

    /////////////////////////////////////////////////////////////////////////////
    @Test(expectedExceptions = java.lang.IllegalStateException.class)//Expected exception with screenshot  NOT DONE!
    public void unValidEmailInRegistration() {
        //Personal Details
        //First name
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("test");
        //Second name
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("test");
        //E-mail
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("incorrectvalue");
        //Telephone
        driver.findElement(By.name("telephone")).click();
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("800800800");
        //Fax
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys("800800800");
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("ITAcademy");
        //Address1
        driver.findElement(By.name("address_1")).click();
        driver.findElement(By.name("address_1")).clear();
        driver.findElement(By.name("address_1")).sendKeys("test");
        //Address2
        driver.findElement(By.name("address_2")).click();
        driver.findElement(By.name("address_2")).clear();
        driver.findElement(By.name("address_2")).sendKeys("test");
        //City
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Kyiv");
        //Post Code
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("test");
        //Country
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.xpath("//option[text()='Ukraine']")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.xpath("//option[text()='Kyiv']")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("qwerty");
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        driver.findElement(By.name("confirm")).clear();
        driver.findElement(By.name("confirm")).sendKeys("qwerty");
        //Accept
        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
    }

    /////////////////////////////////////////////////////////////////////////////
    @Test//Expected exception with screenshot  NOT DONE!
    public void equivalencePartitioningTestInputValues() {
        //Personal Details
        //First name
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("TeSt_1");
        //Second name
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("RanDom/2");
        //E-mail
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("TeStIng|3@test.test");
        //Telephone
        driver.findElement(By.name("telephone")).click();
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("TeSt{1");
        //Fax
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys("TeSt(1");
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("TeSt12$");
        //Address1
        driver.findElement(By.name("address_1")).click();
        driver.findElement(By.name("address_1")).clear();
        driver.findElement(By.name("address_1")).sendKeys("TeS^1");
        //Address2
        driver.findElement(By.name("address_2")).click();
        driver.findElement(By.name("address_2")).clear();
        driver.findElement(By.name("address_2")).sendKeys("TeSt@1");
        //City
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("TeSt!!1");
        //Post Code
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("te12stA*");
        //Country
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.xpath("//option[text()='Ukraine']")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.xpath("//option[text()='Kyiv']")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("QweRty00!$");
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        driver.findElement(By.name("confirm")).clear();
        driver.findElement(By.name("confirm")).sendKeys("QweRty00!$");
        //Accept
        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
    }

    /////////////////////////////////////////////////////////////////////////////
    @Test//Expected exception with screenshot  NOT DONE!
    public void minimumInputBV() {
        //Personal Details
        //First name
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("a");
        //Second name
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("b");
        //E-mail
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("test@test.test");
        //Telephone
        driver.findElement(By.name("telephone")).click();
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("123");
        //Fax
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys("q");
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("t");
        //Address1
        driver.findElement(By.name("address_1")).click();
        driver.findElement(By.name("address_1")).clear();
        driver.findElement(By.name("address_1")).sendKeys("Twe");
        //Address2
        driver.findElement(By.name("address_2")).click();
        driver.findElement(By.name("address_2")).clear();
        driver.findElement(By.name("address_2")).sendKeys("c");
        //City
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("ab");
        //Post Code
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("56");
        //Country
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.xpath("//option[text()='Ukraine']")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.xpath("//option[text()='Kyiv']")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("1234");
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        driver.findElement(By.name("confirm")).clear();
        driver.findElement(By.name("confirm")).sendKeys("1234");
        //Accept
        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
    }

    /////////////////////////////////////////////////////////////////////////////
    @Test//Expected exception with screenshot  NOT DONE!
    public void maximumInputBV() {
        //Personal Details
        //First name
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("Lorem ipsum dolor sit amet, cons");
        //Second name
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Lorem ipsum dolor sit amet, cons");
        //E-mail
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("testing@test.test");
        //Telephone
        driver.findElement(By.name("telephone")).click();
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("Lorem ipsum dolor sit amet, cons");
        //Fax
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
        //Address1
        driver.findElement(By.name("address_1")).click();
        driver.findElement(By.name("address_1")).clear();
        driver.findElement(By.name("address_1")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
        //Address2
        driver.findElement(By.name("address_2")).click();
        driver.findElement(By.name("address_2")).clear();
        driver.findElement(By.name("address_2")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
        //City
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
        //Post Code
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("Lorem ipsu");
        //Country
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.xpath("//option[text()='Ukraine']")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.xpath("//option[text()='Kyiv']")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Lorem ipsum dolor si");
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        driver.findElement(By.name("confirm")).clear();
        driver.findElement(By.name("confirm")).sendKeys("Lorem ipsum dolor si");
        //Accept
        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
    }

    /////////////////////////////////////////////////////////////////////////////
    @Test(expectedExceptions = java.lang.IllegalStateException.class)//Expected exception with screenshot  NOT DONE!
    public void overMaximumInputBV() {
        //Personal Details
        //First name
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("Lorem ipsum dolor sit amet, cons123123");
        //Second name
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Lorem ipsum dolor sit amet, cons123123");
        //E-mail
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("testing@test.test");
        //Telephone
        driver.findElement(By.name("telephone")).click();
        driver.findElement(By.name("telephone")).clear();
        driver.findElement(By.name("telephone")).sendKeys("Lorem ipsum dolor sit amet, cons123123");
        //Fax
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
        //
        //
        //Address
        //Company
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
        //Address1
        driver.findElement(By.name("address_1")).click();
        driver.findElement(By.name("address_1")).clear();
        driver.findElement(By.name("address_1")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen123123");
        //Address2
        driver.findElement(By.name("address_2")).click();
        driver.findElement(By.name("address_2")).clear();
        driver.findElement(By.name("address_2")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
        //City
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen123123");
        //Post Code
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("Lorem ipsu123123");
        //Country
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.xpath("//option[text()='Ukraine']")).click();
        //Region/State
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.xpath("//option[text()='Kyiv']")).click();
        //Password/confirm
        //Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Lorem ipsum dolor si123123");
        //Confirm password
        driver.findElement(By.name("confirm")).click();
        driver.findElement(By.name("confirm")).clear();
        driver.findElement(By.name("confirm")).sendKeys("Lorem ipsum dolor si123123");
        //Accept
        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertTrue((driver.findElements(By.xpath("//div[@class = 'col-sm-9']//h1[text()='Your Account Has Been Created!']")).size() != 0), "Your Account Hasn't Been Created!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.findElement(By.cssSelector("#top-links .dropdown-toggle")).click();
        driver.findElement(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Logout']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//ul[@class = 'dropdown-menu dropdown-menu-right']//a[text()='Register']")).size() != 0), "Register button not found");
    }
}