package yandex.pages;

import framework.WDriver;

public class Page {
    private String url;

    public Page(String url){
        this.url=url;
    }

    public void navigate() {
        WDriver.browser.get(this.url);
    }
}
