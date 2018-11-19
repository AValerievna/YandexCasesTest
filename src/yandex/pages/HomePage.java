package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    By homePageName = By.xpath("//div[@data-zone-name='morda-context']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Get the Page name from Home Page
    public String getHomePageDataZoneName(){
        return	driver.findElement(homePageName).getText();
    }

}
