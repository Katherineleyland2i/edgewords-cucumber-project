package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private WebDriver driver;
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

        public MyAccountPage clickOrders(){
        By getOrdersLink = By.cssSelector("li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--orders");
            driver.findElement(getOrdersLink).click();
            return null;

        }
    private void clickLink(String linkText){
        driver.findElement(By.linkText(linkText)).click();}

    public LoginPage clickLogout() {
        clickLink("Logout");
        return new LoginPage(driver);
    }}
