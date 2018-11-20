package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends Page{
    private static final String homeUrl="https://market.yandex.ru";
    private By homePageIdent = By.xpath("//div[@data-zone-name='morda_context']");
    private By btnLogin = By.xpath("//div[@class='n-passport-suggest-popup-opener']//a");
    private By lstTopMenu = By.xpath("//li[not(contains(@class,\"topmenu__item_mode_current\"))]");


    public HomePage(WebDriver wb){
        super(homeUrl, wb);
    }

    public boolean homePageDataZoneExists(){
        return	elementExists(homePageIdent);
    }

    public void clickLogin(){
        WebElement btnLogin = wd.findElement(this.btnLogin);
        btnLogin.click();
    }

    public void returnToHomePage(){
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WDriver.getWebDriverWaitInstance().until(ExpectedConditions.urlContains("market"));
        wd.switchTo().window(wd.getWindowHandles().iterator().next());
        wd.findElement(homePageIdent);
        WDriver.getWebDriverWaitInstance().until(ExpectedConditions.presenceOfElementLocated(homePageIdent));
    }


    public List<WebElement> getPopularList() {
        return wd.findElements(lstTopMenu);
    }
}
