package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PassportPage extends Page {
    private static final String passpUrl="https://passport.yandex.ru/auth";
    private By authorizedIdent = By.xpath("//div[@class='layout-inner']//a");


    public PassportPage(WebDriver wb){
        super(passpUrl, wb);
    }

    public boolean auhorizedPageDivExists(){
        System.out.println(wd.getCurrentUrl());
        System.out.println(wd.findElement(authorizedIdent).getAttribute("class"));
        return	elementExists(authorizedIdent);
    }
}
