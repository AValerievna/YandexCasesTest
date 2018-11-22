package yandex.pages;

import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends Page {

    private By homePageIdent = By.xpath("//div[@data-zone-name='morda_context']");
    private By btnLogin = By.xpath("//div[@class='n-passport-suggest-popup-opener']//a//span[contains(text(),\"Войти\")]");
    private By btnPopup = By.xpath("//div[@class='n-passport-suggest-popup-opener']//a");
    private By btnLogoutPopup = By.cssSelector("div.n-passport-suggest-popup-opener a");
    private By btnLogout = By.className("user__logout");
    private By lstTopMenu = By.xpath("//li[not(contains(@class,\"topmenu__item_mode_current\"))]");
    private By usrEntLbl = By.className("user__enter-label");


    public HomePage(String homeUrl, WebDriver wb) {
        super(homeUrl, wb);
    }

    public boolean homePageDataZoneExists() {
        return elementExists(homePageIdent);
    }


    public void clickLogin() {
        Actions builder = new Actions(wd);
        WebElement refLogin = wd.findElement(btnLogin);
        Action mouseOverLogin = builder.moveToElement(refLogin).click().build();
        mouseOverLogin.perform();
    }

    public void returnToHomePage(String homeUrlFragment) {
        Browser.getWebDriverWaitInstance().until(ExpectedConditions.urlContains(homeUrlFragment));
        wd.switchTo().window(wd.getWindowHandles().iterator().next());
        wd.navigate().refresh();
        waitForPageLoading();
    }

    public void waitForPageLoading() {
        wd.findElement(btnPopup);
        Browser.getWebDriverWaitInstance().until(ExpectedConditions.elementToBeClickable(btnPopup));
    }


    public List<WebElement> getPopularList() {
        Browser.getWebDriverWaitInstance().until(ExpectedConditions.elementToBeClickable(lstTopMenu));
        return wd.findElements(lstTopMenu);
    }

    public void logOut() {
        WebElement refPopup = wd.findElement(btnLogoutPopup);
        refPopup.click();
        WebElement refLogout = wd.findElement(btnLogout);
        refLogout.click();
    }

    public boolean userEnterLabelSpanExists() {
        return elementExists(usrEntLbl);
    }

}
