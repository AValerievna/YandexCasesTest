package yandex.tests;

import com.opencsv.CSVWriter;
import framework.WDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import yandex.pages.CategoryPage;
import yandex.pages.HomePage;
import yandex.pages.LoginPage;
import yandex.utils.UtilityMethods;

import java.io.FileWriter;
import java.util.List;
import java.util.Random;

import static yandex.utils.UtilityMethods.*;

public class TestScenario extends BaseTest{

    @Test
    public void testScenario() throws Exception {

        HomePage objHomePage = new HomePage(conf.getProperty("home.url"), WDriver.getWebDriverInstance());
        WDriver.getWebDriverInstance().get(objHomePage.getUrl());
        Assert.assertTrue(objHomePage.homePageDataZoneExists(),"Home page did not load");
        System.out.println("On Home Yandex Market Page");

        objHomePage.clickLogin();
        UtilityMethods.redirectionToLoginPage(conf.getProperty("url.login.page.fragment"));
        LoginPage objLoginPage = new LoginPage(conf.getProperty("login.url"), WDriver.getWebDriverInstance());

        objLoginPage.waitForLoad();
        Assert.assertTrue(objLoginPage.passpPageDivExists(), "Login page did not load");
        System.out.println("On Login Yandex Market Page");

        objLoginPage.loginTo(conf.getProperty("usr.name"), conf.getProperty("str.pass"));
        objHomePage.returnToHomePage(conf.getProperty("home.url.fragment"));
        System.out.println("Authorized");

        List<WebElement> popList = objHomePage.getPopularList();
        System.out.println("Got list of categories");

        Random rand = new Random();
        WebElement randomElement = popList.get(rand.nextInt(popList.size()));
        String randName = randomElement.getAttribute(conf.getProperty("random.element.ident"));
        WDriver.getWebDriverWaitInstance().until(ExpectedConditions.elementToBeClickable(randomElement));
        randomElement.click();
        System.out.println("Chose random category");

        CategoryPage objCatPage = new CategoryPage(WDriver.getWebDriverInstance());
        Assert.assertTrue(objCatPage.rightCategoryThemeTitle(randName),"Category page title is not correct");
        System.out.println("Category page has right title");

        WDriver.getWebDriverInstance().get(objHomePage.getUrl());

        CSVWriter writer = new CSVWriter(new FileWriter(conf.getProperty("csv.file.path")), conf.getCharProperty("separator"));
        WDriver.getWebDriverInstance().navigate().refresh();
        objHomePage.waitForPageLoading();

        String sources = WDriver.getWebDriverInstance().getPageSource();
        popularProducts(sources).stream()
                .map(pr -> new String[]{pr})
                .forEach(writer::writeNext);
        writer.close();
        System.out.println("Got list of popular things");

        objHomePage.logOut();
        Assert.assertTrue(objHomePage.userEnterLabelSpanExists(), "Logout did not happen");
        System.out.println("Log out");

    }

}