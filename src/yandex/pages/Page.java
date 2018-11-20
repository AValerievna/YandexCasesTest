package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {
    protected String url;
    protected WebDriver wb;

    public Page(String url, WebDriver wb){
        this.url=url;
        this.wb=wb;
    }


    public void navigate() {
        WDriver.getWebDriverInstance().get(this.url);
    }

    protected boolean elementExists(By by) {
        return  (wb.findElements(by).size() != 0);
    }
}
