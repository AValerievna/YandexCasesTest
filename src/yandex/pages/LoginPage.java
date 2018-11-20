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
    private By loginPageIdent = By.xpath("//div[@class='passp-page']");

/*    public LoginPage(WebDriver driver){
        this.driver = driver;
    }*/

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


    //FIX!!!
    public String getPasspPageDiv(){
        System.out.println(wb.findElement(By.xpath("//div[@xpath='1']")).getAttribute("class"));
        return wb.findElement(loginPageIdent).getAttribute("class");
    }

}
