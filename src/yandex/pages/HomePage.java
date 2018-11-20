package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends Page{
    private static final String homeUrl="https://market.yandex.ru";
    private By homePageIdent = By.xpath("//div[@data-zone-name='morda_context']");
    private By btnLogin = By.xpath("//div[@class='n-passport-suggest-popup-opener']//a");


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
        WDriver.getWebDriverWaitInstance(30).until(ExpectedConditions.presenceOfElementLocated(homePageIdent));
    }




}
