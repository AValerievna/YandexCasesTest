package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class TestLoginPage {
    WebDriver driver;
    LoginPage objLogin;
    HomePage objHomePage;

    @BeforeTest
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://market.yandex.ru");
    }

    /**
     * This test case will login in http://autoqa.pp.ua/wp-login.php
     * Login to application
     * Verify the home page using Dashboard message
     */
    @Test(priority=0)
    public void test_Home_Page_Appear_Correct(){
        //Create Login Page object
        objLogin = new LoginPage(driver);
        //login to application
        objLogin.loginTo("subscriber", "subscriberpass");
        // go the next page
        objHomePage = new HomePage(driver);
        //Verify home page
        Assert.assertTrue(objHomePage.getHomePageDataZoneName().toLowerCase().contains("morda"));
    }
}
