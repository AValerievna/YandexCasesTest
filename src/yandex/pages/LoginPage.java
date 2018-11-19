package yandex.pages;

import framework.WDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page{
   // private WebDriver driver;
    private WebElement inpUser;
    private WebElement inpPwd;
    private WebElement btnLogin;
    private static final String loginUrl="https://passport.yandex.ru/auth";

/*    public LoginPage(WebDriver driver){
        this.driver = driver;
    }*/

    public LoginPage(){
        super(loginUrl);
    }



    public void loginTo(String strUserName,String strPassword){

        inpUser = WDriver.browser.findElement(By.name("login"));
        inpUser.sendKeys(strUserName);

        inpPwd = WDriver.browser.findElement(By.name("passwd"));
        inpPwd.sendKeys(strPassword);

        btnLogin = WDriver.browser.findElement(By.xpath("//button[@type ='submit']"));
        btnLogin.click();

    }

}
