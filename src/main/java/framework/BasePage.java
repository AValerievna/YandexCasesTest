package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class BasePage {
    protected WebDriver wd = Browser.getWebDriverInstance();

    public BasePage(By by) {
        Browser.getWait().until(ExpectedConditions.presenceOfElementLocated(by));
        Assert.assertTrue(elementExists(by), "Needed page did not load");
    }

    protected boolean elementExists(By by) {
        Browser.getWebDriverWaitInstance().until(ExpectedConditions.presenceOfElementLocated(by));
        return (wd.findElements(by).size() != 0);
    }
}
