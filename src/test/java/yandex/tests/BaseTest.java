package yandex.tests;

import framework.Configuration;
import framework.WDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public static Configuration conf;

    @BeforeTest
    public void setupTest() throws Exception {
        conf = new Configuration();
        WDriver.getWebDriverInstance();
    }
    @AfterTest
    public void teardownTest() {
        WDriver.stopBrowser();
    }
}
