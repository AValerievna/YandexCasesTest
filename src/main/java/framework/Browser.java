package framework;

import framework.enums.BrowserTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static framework.BaseTest.conf;


public class Browser {
    private static WebDriverWait browserWait;
    private static Wait<WebDriver> wait;
    private static WebDriver browser;

    public static synchronized WebDriver getWebDriverInstance() {
        if (null == browser) {
            switch (BrowserTypes.valueOf(conf.getProperty("browser"))) {
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", conf.getProperty("gecko.driver.path"));
                    browser = new FirefoxDriver();
                    break;
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", conf.getProperty("chrome.driver.path"));
                    browser = new ChromeDriver();
                    break;
                default:
                    break;
            }
            browser.manage().timeouts().implicitlyWait(conf.getIntProperty("timeouts"), TimeUnit.SECONDS);
            browser.manage().window().maximize();
            browserWait = new WebDriverWait(browser, conf.getIntProperty("default.time.out"));
            wait = new FluentWait<>(browser).withTimeout(Duration.ofSeconds(conf.getIntProperty("fluent.duration"))).pollingEvery(Duration.ofSeconds(conf.getIntProperty("fluent.duration.period"))).ignoring(NoSuchElementException.class);
        }
        return browser;
    }

    public static WebDriverWait getWebDriverWaitInstance() {
        if (browserWait == null || null == browser) {
            throw new IllegalStateException();
        } else {
            return browserWait;
        }
    }

    static synchronized void stopBrowser(){
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