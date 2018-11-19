package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class WDriver {
    private static WebDriverWait browserWait;
    public static WebDriver browser;

    public static WebDriver getWebDriverInstance() {
        if (null == browser) {
            throw new IllegalStateException();
        } else {
            return browser;
        }
    }

    public static WebDriverWait getWebDriverWaitInstance() {
        if (browserWait == null || null == browser) {
            throw new IllegalStateException();
        } else {
            return browserWait;
        }
    }
/*    public static WebDriverWait setWebDriverWaitInstance() {

    }*/

    public static void startBrowser(BrowserTypes browserType, int defaultTimeOut) {
        if (browser!=null) {
            throw new IllegalStateException();
        } else {
            switch (browserType)
            {
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", ".//src/main/resources/geckodriver.exe");
                    browser = new FirefoxDriver();
                    browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                    break;
                case IE:
                    break;
                case CHROME:
                    break;
                default:
                    break;
            }
        }
        browserWait = new WebDriverWait(browser, defaultTimeOut);
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
        browserWait = null;
    }


}