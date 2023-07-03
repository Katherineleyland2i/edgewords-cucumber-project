package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
   private WebDriver driver;

    public LoginPage(WebDriver driver) {
       this.driver = driver;
   }
   By usernameField = By.id("username");
   By passwordField = By.id("password");
   By loginButton = By.cssSelector("#customer_login > div.u-column1.col-1 > form > p:nth-child(3) > button");

   public void enterUsername(String username){
       driver.findElement(usernameField).clear();
       driver.findElement(usernameField).sendKeys(username);
   }
   public void enterPassword(String password){
       driver.findElement(passwordField).clear();
       driver.findElement(passwordField).sendKeys(password);
   }
   public void findLogin(){
       WebElement buttonElement = driver.findElement(loginButton);
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("arguments[0].scrollIntoView(true);", buttonElement);}
       public void clickLogin(){
       driver.findElement(loginButton).click();
   }
}
