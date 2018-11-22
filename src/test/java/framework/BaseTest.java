package framework;

import framework.Configuration;
import framework.WDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.logging.Logger;

/**
 * Common for tests*/
public class BaseTest {
    public static Configuration conf;
    protected static Logger log;

    @BeforeTest
    public void setupTest() throws Exception {
        log = Logger.getGlobal();
        conf = new Configuration();
        WDriver.getWebDriverInstance();
    }
    @AfterTest
    public void teardownTest() {
        WDriver.stopBrowser();
    }
}
