package yandex.pages;

import framework.BasePage;
import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

import static framework.BaseTest.conf;

public class HomePage extends BasePage {

    private static final By homePageIdent = By.xpath("//div[@data-zone-name='morda_context']");
    private By btnLogin = By.xpath("//div[@class='n-passport-suggest-popup-opener']//a//span[contains(text(),\"Войти\")]");
    private By btnLogoutPopup = By.cssSelector("div.n-passport-suggest-popup-opener a");
    private By btnLogout = By.className("user__logout");
    private By lstTopMenu = By.xpath("//li[not(contains(@class,\"topmenu__item_mode_current\"))]");
    private By usrEntLbl = By.className("user__enter-label");
    private By productLocator = By.xpath("//div[@data-zone-name='product']");

    public HomePage() {
        super(homePageIdent);
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

    private List<WebElement> getPopularList() {
        Browser.getWebDriverWaitInstance().until(ExpectedConditions.elementToBeClickable(productLocator));
        return wd.findElements(lstTopMenu);
    }

    public void logOut() {
        wd.findElement(btnLogoutPopup).click();
        wd.findElement(btnLogout).click();
    }

    public boolean userEnterLabelSpanExists() {
        return elementExists(usrEntLbl);
    }


    public String goToRandomPage() {
        List<WebElement> popList = this.getPopularList();

        Random rand = new Random();
        WebElement randomElement = popList.get(rand.nextInt(popList.size()));
        String randName = randomElement.getAttribute(conf.getProperty("random.element.ident"));
        Browser.getWebDriverWaitInstance().until(ExpectedConditions.elementToBeClickable(randomElement));
        randomElement.click();
        return randName;
    }
}
