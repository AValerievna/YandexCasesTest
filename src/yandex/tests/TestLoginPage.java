package yandex.tests;

import com.opencsv.CSVWriter;
import framework.Configuration;
import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import yandex.pages.CategoryPage;
import yandex.pages.HomePage;
import yandex.pages.LoginPage;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLoginPage {

    private static final int DEFAULT_TIME_OUT = 10;
    private static final String DATA_DEPARTMENT = "data-department";
    private static final String REGEX1 = "div[data-zone-data*=\"Популярные\"] div div";
    private static final String INNER_HTML = "innerHTML";
    private static final String REGEX2 = "<div class=\"name[^\"]*\".*?>(.*?)</div>";
    private Configuration conf;

    @BeforeTest
    public void setupTest() throws Exception {
        conf = new Configuration();
        WDriver.startBrowser(conf, DEFAULT_TIME_OUT);
    }

    @Test
    public void testScenario() throws Exception {

        HomePage objHomePage = new HomePage(conf.getHomeUrl(), WDriver.getWebDriverInstance());
        objHomePage.navigate();
        Assert.assertTrue(objHomePage.homePageDataZoneExists());


        objHomePage.clickLogin();
        redirectionToLoginPage();
        LoginPage objLoginPage = new LoginPage(conf.getLoginUrl(), WDriver.getWebDriverInstance());

        objLoginPage.waitForLoad();
        Assert.assertTrue(objLoginPage.passpPageDivExists());


        objLoginPage.loginTo(conf.getUsrName(), conf.getStrPass());
        objHomePage.returnToHomePage(conf.getHomeUrlFragment());

        List<WebElement> popList = objHomePage.getPopularList();
        Random rand = new Random();
        WebElement randomElement = popList.get(rand.nextInt(popList.size()));

        String randName = randomElement.getAttribute(DATA_DEPARTMENT);
        randomElement.click();

        CategoryPage objCatPage = new CategoryPage(WDriver.getWebDriverInstance());
        Assert.assertTrue(objCatPage.rightCategoryThemeTitle(randName));

        objHomePage.navigate();


        CSVWriter writer = new CSVWriter(new FileWriter(conf.getCsvFilePath()), conf.getSeparator());
        WDriver.getWebDriverInstance().navigate().refresh();
        popularProducts().stream()
                .map(pr -> new String[]{pr})
                .forEach(writer::writeNext);
        writer.close();

        objHomePage.logOut();
        Assert.assertTrue(objHomePage.userEnterLabelSpanExists());

    }

    private static List<String> popularProducts() {
        List<String> result = new ArrayList<>();
        String productsSection = WDriver.getWebDriverInstance().findElement(By.cssSelector(REGEX1)).getAttribute(INNER_HTML);
        Pattern productNamePattern = Pattern.compile(REGEX2);
        Matcher productNameMatcher = productNamePattern.matcher(productsSection);
        int start = 0;
        while (productNameMatcher.find(start)) {
            result.add(productNameMatcher.group(1));
            start = productNameMatcher.end();
        }
        return result;
    }


    private void redirectionToLoginPage() {
        if (!WDriver.getWebDriverInstance().getCurrentUrl().contains(conf.getUrlLoginPageFragment())) {
            String[] handlers = new String[2];
            handlers = WDriver.getWebDriverInstance().getWindowHandles().toArray(handlers);
            System.out.println(handlers[1]);
            WDriver.getWebDriverInstance().switchTo().window(handlers[1]);
        }
    }

    @AfterTest
    public void teardownTest() {
        WDriver.stopBrowser();
    }
}
