package yandex.utils;

import com.opencsv.CSVWriter;
import framework.WDriver;
import yandex.pages.HomePage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static yandex.tests.BaseTest.conf;

public class UtilityMethods {

    private static final String OPEN_TAG = "(<[^/][^>]*>[^<]*)";
    private static final String CLOSE_TAG = "(</[^>]*>[^<]*)";
    private static final String HTML_COMMENT_REGEX = "<!--[^>]*>";
    private static final String POPULAR_PRODUCTS_HEAD_REGEX = "Популярные товары</h3>[^<]*</span>[^<]*</div>[^<]*";
    private static final String PRODUCT_NAME_REGEX = "<div class=\"name[^\"]*\".*?>(.*?)</div>";
    private static final String NEW_LINE_REGEX = "\r?\n";
    private static final String FIGURE_BRACKET_OPEN = "{";
    private static final String FIGURE_BRACKET_CLOSE = "}";
    private static final String POPULAR = "(?<popular>(";
    private static final String CAPTURE_GROUP_START = "(";
    private static final String OPTIONAL_CAPTURE_GROUP_END = ")?";
    private static final String CAPTURE_GROUP_END = ")*)";
    private static final String GROUP_NAME = "popular";
    private static final String REPLACEMENT = "";
    private static final String DELIMITER = "";
    private static final String DELIMITER1 = " ";

    private static String openTag(int times) {
        return OPEN_TAG + FIGURE_BRACKET_OPEN + times + FIGURE_BRACKET_CLOSE;
    }

    private static String closeTag(int times) {
        return CLOSE_TAG + FIGURE_BRACKET_OPEN + times + FIGURE_BRACKET_CLOSE;
    }

    private static String openTag() {
        return openTag(1);
    }

    private static String closeTag() {
        return closeTag(1);
    }

    /**
     * Method makes regular expression work:
     * remove html comments
     * find start of section with its title with first openTag
     * catch product items with POPULAR
     * start a single product item
     * get sale element optionally
     * end of product
     */
    public static List<String> popularProducts(String yandexMarketMainPageContent) {
        List<String> result = new ArrayList<>();
        yandexMarketMainPageContent = yandexMarketMainPageContent.replaceAll(HTML_COMMENT_REGEX, REPLACEMENT);
        Pattern productsSectionPattern = Pattern.compile(String.join(DELIMITER,
                POPULAR_PRODUCTS_HEAD_REGEX, openTag(5),
                POPULAR,
                openTag(10), closeTag(3), openTag(7), closeTag(), openTag(), closeTag(2),
                CAPTURE_GROUP_START, openTag(2), closeTag(3), openTag(3), closeTag(), openTag(), closeTag(2), OPTIONAL_CAPTURE_GROUP_END,
                closeTag(3), openTag(2), closeTag(9),
                CAPTURE_GROUP_END));
        Matcher productsSectionMatcher = productsSectionPattern.matcher(yandexMarketMainPageContent);
        boolean matches = productsSectionMatcher.find();
        if (!matches) {
            throw new IllegalArgumentException("Can not match given string");
        }

        String productsSection = productsSectionMatcher.group(GROUP_NAME);
        Pattern productNamePattern = Pattern.compile(PRODUCT_NAME_REGEX, Pattern.DOTALL);
        Matcher productNameMatcher = productNamePattern.matcher(productsSection);
        int start = 0;
        while (productNameMatcher.find(start)) {
            String matchedGroup = productNameMatcher.group(1);
            result.add(Arrays.stream(matchedGroup.split(NEW_LINE_REGEX)).map(String::trim).collect(Collectors.joining(DELIMITER1)));
            start = productNameMatcher.end();
        }
        return result;
    }


    /**
     * Work with opening tag
     */
    public static void redirectionToLoginPage(String urlFragment) {
        if (!WDriver.getWebDriverInstance().getCurrentUrl().contains(urlFragment)) {
            String[] handlers = new String[2];
            handlers = WDriver.getWebDriverInstance().getWindowHandles().toArray(handlers);
            System.out.println(handlers[1]);
            WDriver.getWebDriverInstance().switchTo().window(handlers[1]);
        }
    }

    public static void writeToCSV(HomePage objHomePage) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(conf.getProperty("csv.file.path")), conf.getCharProperty("separator"));
        WDriver.getWebDriverInstance().navigate().refresh();
        objHomePage.waitForPageLoading();

        String sources = WDriver.getWebDriverInstance().getPageSource();
        popularProducts(sources).stream()
                .map(pr -> new String[]{pr})
                .forEach(writer::writeNext);
        writer.close();
    }
}
