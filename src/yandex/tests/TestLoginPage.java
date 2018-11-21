package yandex.tests;

import com.opencsv.CSVWriter;
import framework.Configuration;
import framework.WDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import yandex.pages.CategoryPage;
import yandex.pages.HomePage;
import yandex.pages.LoginPage;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestLoginPage {


    private static final String DATA_DEPARTMENT = "data-department";
    private Configuration conf;

    @BeforeTest
    public void setupTest() throws Exception {
        conf = new Configuration();
        WDriver.startBrowser(conf, conf.getDefaultTimeOut());
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
        WDriver.getWebDriverWaitInstance().until(ExpectedConditions.elementToBeClickable(randomElement));
        randomElement.click();

        CategoryPage objCatPage = new CategoryPage(WDriver.getWebDriverInstance());
        Assert.assertTrue(objCatPage.rightCategoryThemeTitle(randName));

        objHomePage.navigate();


        CSVWriter writer = new CSVWriter(new FileWriter(conf.getCsvFilePath()), conf.getSeparator());
        WDriver.getWebDriverInstance().navigate().refresh();
        objHomePage.waitForPageLoading();

        String sources = WDriver.getWebDriverInstance().getPageSource();
        popularProducts(sources).stream()
                .map(pr -> new String[]{pr})
                .forEach(writer::writeNext);
        writer.close();

        objHomePage.logOut();
        Assert.assertTrue(objHomePage.userEnterLabelSpanExists());

    }

    private static String openTag(int times) {
        String openTag = "(<[^/][^>]*>[^<]*)";
        return openTag + "{" + times + "}";
    }

    private static String closeTag(int times) {
        String openTag = "(</[^>]*>[^<]*)";
        return openTag + "{" + times + "}";
    }

    private static String openTag() {
        return openTag(1);
    }

    private static String closeTag() {
        return closeTag(1);
    }

    private static List<String> popularProducts(String yandexMarketMainPageContent) {
        List<String> result = new ArrayList<>();
        yandexMarketMainPageContent = yandexMarketMainPageContent.replaceAll("<!--[^>]*>", "");
        Pattern productsSectionPattern = Pattern.compile(String.join("",
                "Популярные товары</h3>[^<]*</span>[^<]*</div>[^<]*", openTag(5),
                "(?<popular>(", // catch product items
                openTag(10), closeTag(3), openTag(7), closeTag(), openTag(), closeTag(2),
                "(", openTag(2), closeTag(3), openTag(3), closeTag(), openTag(), closeTag(2), ")?",
                closeTag(3), openTag(2), closeTag(9),
                ")*)"));
        Matcher productsSectionMatcher = productsSectionPattern.matcher(yandexMarketMainPageContent);
        boolean matches = productsSectionMatcher.find();
        if (!matches) {
            throw new IllegalArgumentException("Can not match given string");
        }
        String productsSection = productsSectionMatcher.group("popular");
        Pattern productNamePattern = Pattern.compile("<div class=\"name[^\"]*\".*?>(.*?)</div>", Pattern.DOTALL);
        Matcher productNameMatcher = productNamePattern.matcher(productsSection);
        int start = 0;
        while (productNameMatcher.find(start)) {
            String matchedGroup = productNameMatcher.group(1);
            result.add(Arrays.stream(matchedGroup.split("\r?\n")).map(String::trim).collect(Collectors.joining(" ")));
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
