package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    By userName = By.name("login");
    By password = By.name("passwd");
    By login = By.xpath("//button[@type ='submit']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    //Set user name in textbox
    public void setUserName(String strUserName){
        driver.findElement(userName).sendKeys(strUserName);
    }

    //Set password in password textbox
    public void setPassword(String strPassword){
        driver.findElement(password).sendKeys(strPassword);
    }

    //Click on login button
    public void clickLogin(){
        driver.findElement(login).click();
    }

    /**
     * This POM method will be exposed in test case to login in the application
     * @param strUserName
     * @param strPasword
     * @return
     */
    public void loginTo(String strUserName,String strPasword){
        //Fill user name
        this.setUserName(strUserName);
        //Fill password
        this.setPassword(strPasword);
        //Click Login button
        this.clickLogin();
    }

}
