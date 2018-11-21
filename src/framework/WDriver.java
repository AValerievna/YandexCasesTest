package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class WDriver {
    private static WebDriverWait browserWait;
    private static WebDriver browser;
    private static Wait<WebDriver> wait;

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

    public static synchronized void startBrowser(Configuration conf, int defaultTimeOut ) {
        if (browser!=null) {
            throw new IllegalStateException();
        } else {
            switch (conf.getBrowser())
            {
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", conf.getGeckoDriverPath());
                    browser = new FirefoxDriver();
                    break;
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", conf.getChromeDriverPath());
                    browser = new ChromeDriver();
                    break;
                default:
                    break;
            }
        }
        browser.manage().timeouts().implicitlyWait(conf.getTimeouts(), TimeUnit.SECONDS);
        browser.manage().window().maximize();
        browserWait = new WebDriverWait(browser, defaultTimeOut);

        wait = new FluentWait<>(browser).withTimeout(conf.getDuration(),TimeUnit.SECONDS).pollingEvery(conf.getDuration1(),TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

    }

    public static synchronized void stopBrowser(){
        if (null != browser){
            browser.quit();
        }
        browser = null;
        browserWait = null;
        wait = null;
    }


    public static Wait<WebDriver> getWait() {
        if (wait == null || null == browser) {
            throw new IllegalStateException();
        } else {
            return wait;
        }
    }
}