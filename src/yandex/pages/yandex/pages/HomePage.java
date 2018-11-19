package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page{
    WebDriver driver;
    private static final String homeUrl="https://market.yandex.ru";
    public static final String HOME_PAGE_IDENT = "//div[@data-zone-name='morda-context']";

    /*public HomePage(WebDriver driver){
        this.driver = driver;
    }*/

    public HomePage(){
        super(homeUrl);
    }

  /*  public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }
*/
    public String getHomePageDataZoneName(){
        return	driver.findElement(By.xpath(HOME_PAGE_IDENT)).getText();
    }

}
