package yandex.tests;

import com.opencsv.CSVWriter;
import framework.BrowserTypes;
import framework.WDriver;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import yandex.pages.CategoryPage;
import yandex.pages.HomePage;
import yandex.pages.LoginPage;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestLoginPage {

    private LoginPage objLoginPage;
    private HomePage objHomePage;
    private CategoryPage objCatPage;
    private WebElement randomElement;


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

        List<WebElement> popList = objHomePage.getPopularList();
        Random rand = new Random();
        randomElement = popList.get(rand.nextInt(popList.size()));

        String randName=randomElement.getAttribute("data-department");
        System.out.println(randomElement.getAttribute("data-department"));
        randomElement.click();

        objCatPage = new CategoryPage(WDriver.getWebDriverInstance());
        Assert.assertTrue(objCatPage.rightCategoryThemeTitle(randName));

        objHomePage.navigate();

        //WDriver.getWebDriverInstance().getPageSource();


        try {
            CSVWriter writer = new CSVWriter(new FileWriter("yourfile.csv"), '\n');
            // feed in your array (or convert your data to an array)
            Thread.sleep(12000);
            WDriver.getWebDriverInstance().navigate().refresh();
            String[] entries = WDriver.getWebDriverInstance().getPageSource().split("[^>.+?</div></div></div>)]");
            writer.writeNext(entries);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        objHomePage.logOut();
        Assert.assertTrue(objHomePage.userEnterLabelSpanExists());

    }


    public void redirectionToLoginPage(){
        if(!WDriver.getWebDriverInstance().getCurrentUrl().contains("passport")){
            String[] handlers = new String[2];
            handlers = WDriver.getWebDriverInstance().getWindowHandles().toArray(handlers);
            WDriver.getWebDriverInstance().switchTo().window(handlers[1]);
        }
    }

    @AfterTest
    public void teardownTest(){
        WDriver.stopBrowser();
    }
}
