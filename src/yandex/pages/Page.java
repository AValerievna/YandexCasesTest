package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {
    protected String url;
    protected WebDriver wd;

    public Page(String url, WebDriver wd){
        this.url=url;
        this.wd = wd;
    }


    public void navigate() {
        WDriver.getWebDriverInstance().get(this.url);
    }

    protected boolean elementExists(By by) {
        return  (wd.findElements(by).size() != 0);
    }
}
