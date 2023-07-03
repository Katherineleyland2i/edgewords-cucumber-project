package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrdersPage {
    private WebDriver driver;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
    }

    private void clickLink(String linkText){
        driver.findElement(By.linkText(linkText)).click();}

        public LoginPage clickLogout() {
            clickLink("Logout");
            return new LoginPage(driver);
        }
}
