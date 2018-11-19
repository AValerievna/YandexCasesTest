package yandex.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import yandex.pages.*;
import framework.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class TestLoginPage {
    //WebDriver driver;

    LoginPage objLogin;
    HomePage objHomePage;

    @BeforeTest
    public void setupTest(){
/*        System.setProperty("webdriver.gecko.driver", "resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/

        //WDriver.startBrowser(BrowserTypes.FIREFOX, 30);


        //WDriver.browser.get("https://passport.yandex.ru/auth");
    }

    @Test
    public void test_Home_Page_Appear_Correct(){
        //objLogin = new LoginPage();
        //objLogin.navigate();
        //objLogin.loginTo("subscriber", "subscriberpass");


        objHomePage = new HomePage();
        objHomePage.navigate();
        Assert.assertTrue(objHomePage.getHomePageDataZoneName().toLowerCase().contains("morda"));
    }

    @AfterTest
    public void teardownTest(){
        WDriver.stopBrowser();
    }
}
