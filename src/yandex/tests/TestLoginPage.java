package yandex.tests;

import framework.BrowserTypes;
import framework.WDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import yandex.pages.HomePage;
import yandex.pages.LoginPage;

public class TestLoginPage {
    //WebDriver driver;

    LoginPage objLoginPage;
    HomePage objHomePage;

    @BeforeTest
    public void setupTest(){
        WDriver.startBrowser(BrowserTypes.FIREFOX, 10);
    }

    @Test
    public void test_Home_Page_Appear_Correct(){

        objHomePage = new HomePage(WDriver.getWebDriverInstance());
        objHomePage.navigate();
        Assert.assertTrue(objHomePage.homePageDataZoneExists());


        objHomePage.clickLogin();
        redirectionToLoginPage();
        objLoginPage = new LoginPage(WDriver.getWebDriverInstance());

        Assert.assertTrue(objLoginPage.passpPageDivExists());


        objLoginPage.loginTo("aleksia.denica", "Monkeyslut");
        objHomePage.returnToHomePage();



    }


    public void redirectionToLoginPage(){
        if(!WDriver.getWebDriverInstance().getCurrentUrl().contains("passport")){
            String[] handlers = new String[2];
            handlers = WDriver.getWebDriverInstance().getWindowHandles().toArray(handlers);
            WDriver.getWebDriverInstance().switchTo().window(handlers[1]);
            System.out.println(WDriver.getWebDriverInstance().getCurrentUrl());
        }
    }

    @AfterTest
    public void teardownTest(){
        WDriver.stopBrowser();
    }
}
