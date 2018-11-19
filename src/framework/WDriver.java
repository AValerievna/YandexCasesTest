package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;

public class WDriver {
    private static WebDriverWait browserWait;
    private static WebDriver browser;

    public static WebDriver getWebDriverInstance() {
        if (null == browser) {
            throw new IllegalStateException();
        } else {
            return browser;
        }
    }

    public static void StartBrowser(BrowserTypes browserType, int defaultTimeOut) {
        if (browser!=null) {
            throw new IllegalStateException();
        } else {
            switch (browserType)
            {
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", ".//src/main/resources/geckodriver.exe");
                    Driver.Browser = new FirefoxDriver();

                    break;
                case IE:
                    break;
                case CHROME:
                    break;
                default:
                    break;
            }
        }
        //BrowserWait = new WebDriverWait(Driver.Browser, TimeSpan.FromSeconds(defaultTimeOut));
    }
        //https://kreisfahrer.gitbooks.io/selenium-webdriver/content/page_object_pattern_arhitektura_testovogo_proekta/ispolzovanie_patterna_page_object.html
        //https://www.automatetheplanet.com/singleton-design-pattern/
        //https://www.automatetheplanet.com/advanced-page-object-pattern/
        //https://selenium2.ru/docs/webdriver
        //https://selenium2.ru/docs/test-design-considerations.html
        //http://seleniumwebdriver.org/how-to-use-singleton-class


    public static void stopBrowser(){
        if (null != browser){
            browser.quit();
        }
        browser = null;
    }


}