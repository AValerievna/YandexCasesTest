package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


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

    public static WebDriverWait getWebDriverWaitInstance() {
        if (browserWait == null || null == browser) {
            throw new IllegalStateException();
        } else {
            return browserWait;
        }
    }

    public static void startBrowser(BrowserTypes browserType, int defaultTimeOut) {
        if (browser!=null) {
            throw new IllegalStateException();
        } else {
            switch (browserType)
            {
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
                    browser = new FirefoxDriver();
                    browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

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

    public static void stopBrowser(){
        if (null != browser){
            browser.quit();
        }
        browser = null;
        browserWait = null;
    }


}