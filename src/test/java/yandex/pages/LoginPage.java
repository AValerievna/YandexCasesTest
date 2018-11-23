package yandex.pages;

import framework.BasePage;
import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static framework.BaseTest.conf;

public class LoginPage extends BasePage {

    private By username = By.name("login");
    private By passwd = By.name("passwd");
    private By login = By.xpath("//button[@type ='submit']");
    private static final By loginPageIdent = By.xpath("//div[@class='passport-Page-Body']");

    public LoginPage() {
        super(loginPageIdent);
    }

    public void returnToHomePage(String homeUrlFragment) {
        Browser.getWebDriverWaitInstance().until(ExpectedConditions.urlContains(homeUrlFragment));
        wd.switchTo().window(wd.getWindowHandles().iterator().next());
        Browser.refreshBr();
    }

    public void loginTo(String strUserName, String strPassword) {

        WebElement inpUser = Browser.getWebDriverInstance().findElement(username);
        inpUser.sendKeys(strUserName);

        WebElement inpPwd = Browser.getWebDriverInstance().findElement(passwd);
        inpPwd.sendKeys(strPassword);

        WebElement btnLogin = Browser.getWebDriverInstance().findElement(login);
        btnLogin.click();
    }
}
