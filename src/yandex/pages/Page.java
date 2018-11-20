package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {
    protected String url;
    protected WebDriver wd;

    public Page(WebDriver wd){
        this.wd = wd;
    }
    public Page(String url, WebDriver wd){
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

    protected boolean elementExists(By by) {
        return  (wd.findElements(by).size() != 0);
    }
}
