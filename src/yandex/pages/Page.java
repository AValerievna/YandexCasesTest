package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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


    public void navigate() {
        if(url!=null) {
            WDriver.getWebDriverInstance().get(this.url);
        } else {
            throw  new NullPointerException();
        }
    }

    boolean elementExists(By by) {
        return  (wd.findElements(by).size() != 0);
    }

}
