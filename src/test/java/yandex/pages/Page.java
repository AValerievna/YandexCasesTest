package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Page {
    private String url;
    WebDriver wd;

    Page(WebDriver wd){
        this.wd = wd;
    }
    Page(String url, WebDriver wd){
        this(wd);
        this.url=url;
    }


    public String getUrl() {
        if(url!=null) {
            return this.url;
        } else {
            throw  new NullPointerException();
        }
    }

    boolean elementExists(By by) {
        WDriver.getWebDriverWaitInstance().until(ExpectedConditions.presenceOfElementLocated(by));
        return (wd.findElements(by).size() != 0);
    }

}
