package framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.logging.Logger;

/**
 * Common for tests
 */
public class BaseTest {
    public static Configuration conf;
    protected static Logger log;
    protected WebDriver wd;

    @BeforeTest
    public void setupTest() throws Exception {
        log = Logger.getGlobal();
        conf = new Configuration();
        wd = Browser.getWebDriverInstance();
    }

    @AfterTest
    public void teardownTest() {
        Browser.stopBrowser();
    }
}
