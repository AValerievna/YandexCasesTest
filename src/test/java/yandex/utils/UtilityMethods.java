package yandex.utils;

import framework.WDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UtilityMethods {

    private static final String OPEN_TAG = "(<[^/][^>]*>[^<]*)";
    private static final String CLOSE_TAG = "(</[^>]*>[^<]*)";
    private static final String REGEX = "<!--[^>]*>";
    private static final String REGEX1 = "Популярные товары</h3>[^<]*</span>[^<]*</div>[^<]*";
    private static final String REGEX2 = "<div class=\"name[^\"]*\".*?>(.*?)</div>";
    private static final String REGEX3 = "\r?\n";
    private static final String FIGURE_BRACKET_OPEN = "{";
    private static final String FIGURE_BRACKET_CLOSE = "}";
    private static final String POPULAR = "(?<popular>(";
    private static final String BRACKET_OPEN = "(";
    private static final String REGEX4 = ")?";
    private static final String REGEX5 = ")*)";
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

    public static List<String> popularProducts(String yandexMarketMainPageContent) {
        List<String> result = new ArrayList<>();
        yandexMarketMainPageContent = yandexMarketMainPageContent.replaceAll(REGEX, REPLACEMENT);
        Pattern productsSectionPattern = Pattern.compile(String.join(DELIMITER,
                REGEX1, openTag(5),
                POPULAR,
                openTag(10), closeTag(3), openTag(7), closeTag(), openTag(), closeTag(2),
                BRACKET_OPEN, openTag(2), closeTag(3), openTag(3), closeTag(), openTag(), closeTag(2), REGEX4,
                closeTag(3), openTag(2), closeTag(9),
                REGEX5));
        Matcher productsSectionMatcher = productsSectionPattern.matcher(yandexMarketMainPageContent);
        boolean matches = productsSectionMatcher.find();
        if (!matches) {
            throw new IllegalArgumentException("Can not match given string");
        }

        String productsSection = productsSectionMatcher.group(GROUP_NAME);
        Pattern productNamePattern = Pattern.compile(REGEX2, Pattern.DOTALL);
        Matcher productNameMatcher = productNamePattern.matcher(productsSection);
        int start = 0;
        while (productNameMatcher.find(start)) {
            String matchedGroup = productNameMatcher.group(1);
            result.add(Arrays.stream(matchedGroup.split(REGEX3)).map(String::trim).collect(Collectors.joining(DELIMITER1)));
            start = productNameMatcher.end();
        }
        return result;
    }


    public static void redirectionToLoginPage(String urlFragment) {
        if (!WDriver.getWebDriverInstance().getCurrentUrl().contains(urlFragment)) {
            String[] handlers = new String[2];
            handlers = WDriver.getWebDriverInstance().getWindowHandles().toArray(handlers);
            System.out.println(handlers[1]);
            WDriver.getWebDriverInstance().switchTo().window(handlers[1]);
        }
    }
}
