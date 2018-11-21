package framework;

import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private BrowserTypes browser;
    private String chromeDriverPath;
    private String usrName;
    private String strPass;
    private String urlLoginPageFragment;
    private String csvFilePath;
    private char separator;
    private String loginUrl;
    private String homeUrl;
    private String homeUrlFragment;
    private String geckoDriverPath;

    public Configuration() throws IOException {
        Properties prop = new Properties();
        prop.load(Configuration.class.getClassLoader().getResourceAsStream("configuration.properties"));
        usrName=prop.getProperty("usr.name");
        browser=BrowserTypes.valueOf(prop.getProperty("browser"));
        loginUrl=prop.getProperty("login.url");
        homeUrl=prop.getProperty("home.url");
        homeUrlFragment=prop.getProperty("home.url.fragment");
        geckoDriverPath=prop.getProperty("gecko.driver.path");
        chromeDriverPath=prop.getProperty("chrome.driver.path");
        strPass=prop.getProperty("str.pass");
        urlLoginPageFragment=prop.getProperty("url.login.page.fragment");
        csvFilePath=prop.getProperty("csv.file.path");
        separator=prop.getProperty("separator").charAt(0);

    }

    BrowserTypes getBrowser() {
        return browser;
    }

    String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getUsrName() {
        return usrName;
    }

    public String getStrPass() {
        return strPass;
    }

    public String getUrlLoginPageFragment() {
        return urlLoginPageFragment;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public char getSeparator() {
        return separator;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public String getHomeUrlFragment() {
        return homeUrlFragment;
    }

    String getGeckoDriverPath() {
        return geckoDriverPath;
    }

}
