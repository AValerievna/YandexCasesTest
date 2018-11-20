package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page{

    private By username = By.name("login");
    private By passwd = By.name("passwd");
    private By login = By.xpath("//button[@type ='submit']");
    private static final String loginUrl="https://passport.yandex.ru/auth";
    private By loginPageIdent = By.xpath("//div[@class='passport-Page-Body']");



    public LoginPage(WebDriver wb){
        super(loginUrl, wb);
    }



    public void loginTo(String strUserName,String strPassword){

        WebElement inpUser = WDriver.getWebDriverInstance().findElement(username);
        inpUser.sendKeys(strUserName);

        WebElement inpPwd = WDriver.getWebDriverInstance().findElement(passwd);
        inpPwd.sendKeys(strPassword);

        WebElement btnLogin = WDriver.getWebDriverInstance().findElement(login);
        btnLogin.click();

    }

    public boolean passpPageDivExists(){
        return elementExists(loginPageIdent);
    }

}
