package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page{
   // private WebDriver driver;
    private By username = By.name("login");
    private By passwd = By.name("passwd");
    private By login = By.xpath("//button[@type ='submit']");
    private static final String loginUrl="https://passport.yandex.ru/auth";

/*    public LoginPage(WebDriver driver){
        this.driver = driver;
    }*/

    public LoginPage(){
        super(loginUrl);
    }



    public void loginTo(String strUserName,String strPassword){

        WebElement inpUser = WDriver.browser.findElement(username);
        inpUser.sendKeys(strUserName);

        WebElement inpPwd = WDriver.browser.findElement(passwd);
        inpPwd.sendKeys(strPassword);

        WebElement btnLogin = WDriver.browser.findElement(login);
        btnLogin.click();

    }

}
