package yandex.tests;

import framework.BaseTest;
import framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import yandex.pages.CategoryPage;
import yandex.pages.HomePage;
import yandex.pages.LoginPage;
import yandex.utils.UtilityMethods;

import java.util.List;
import java.util.Random;

public class TestScenario extends BaseTest {

    @Test
    public void testScenario() throws Exception {

        Browser.getPage(conf.getProperty("home.url"));
        HomePage objHomePage = new HomePage();
        Assert.assertTrue(objHomePage.homePageDataZoneExists(), "Home page did not load");
        log.info("On Home Yandex Market BasePage");

        objHomePage.clickLogin();
        UtilityMethods.redirectionToLoginPage(conf.getProperty("url.login.page.fragment"));
        LoginPage objLoginPage = new LoginPage();

        log.info("On Login Yandex Market BasePage");

        objLoginPage.loginTo(conf.getProperty("usr.name"), conf.getProperty("str.pass"));
        objLoginPage.returnToHomePage(conf.getProperty("home.url.fragment"));
        log.info("Authorized");

        String randName = objHomePage.goToRandomPage();
        log.info("Gone to random category");

        CategoryPage objCatPage = new CategoryPage();
        Assert.assertTrue(objCatPage.rightCategoryThemeTitle(randName), "Category page title is not correct");
        log.info("Category page has right title");

        objCatPage.goToHomePage();
        objHomePage = new HomePage();
        Browser.refreshBr();

        UtilityMethods.writeToCSV(wd.getPageSource());
        log.info("Got list of popular things");

        objHomePage.logOut();
        Assert.assertTrue(objHomePage.userEnterLabelSpanExists(), "Logout did not happen");
        log.info("Log out");

    }


}
