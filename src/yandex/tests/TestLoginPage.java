package yandex.tests;

import framework.BrowserTypes;
import framework.WDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import yandex.pages.HomePage;
import yandex.pages.LoginPage;
import yandex.pages.PassportPage;

public class TestLoginPage {
    //WebDriver driver;

    LoginPage objLoginPage;
    HomePage objHomePage;
    PassportPage objPasspPage;

    @BeforeTest
    public void setupTest(){
        WDriver.startBrowser(BrowserTypes.FIREFOX, 30);

        //WDriver.browser.get("https://passport.yandex.ru/auth");
    }

    @Test
    public void test_Home_Page_Appear_Correct(){
        //objLogin = new LoginPage();
        //objLogin.navigate();
        //objLogin.loginTo("subscriber", "subscriberpass");

        objHomePage = new HomePage(WDriver.getWebDriverInstance());
        objHomePage.navigate();
        Assert.assertTrue(objHomePage.homePageDataZoneExists());


        objHomePage.clickLogin();
        redirectionToLoginPage();
        //redirectToLoginPage();
        objLoginPage = new LoginPage(WDriver.getWebDriverInstance());

        //FIX!!
        // Assert.assertTrue(objLoginPage.getPasspPageDiv().toLowerCase().contains("passp"));

        //objLoginPage.navigate();
        objLoginPage.loginTo("aleksia.denica", "Monkeyslut");
        objHomePage.returnToHomePage();
        //System.out.println("URL "+WDriver.getWebDriverInstance().getCurrentUrl());
        //objPasspPage= new PassportPage(WDriver.getWebDriverInstance());
        //System.out.println("URL "+ WDriver.getWebDriverInstance().getCurrentUrl());
       // Assert.assertTrue(objHomePage.homePageDataZoneExists());


    }

    //public void redirectToLoginPage(){
     //  WDriver.getWebDriverWaitInstance().until(ExpectedConditions.elementToBeClickable(By.id(id)));;
    //}
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
