package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

public class NavBarPage {

  private WebDriver driver;

    public NavBarPage(WebDriver driver) {
        this.driver = driver;
    }

    private void clickLink(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }
    public HomePage clickHome() {
        clickLink("Home");
        return new HomePage(driver);
    }

    public CartPage clickCart() {
        clickLink("Cart");
        return new CartPage(driver);
    }
    public MyAccountPage clickMyAccount(){
        int maxAttempts = 3;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                driver.findElement(By.xpath("//*[@id=\"menu-item-46\"]")).click();
                break; // Exit the loop if the click operation succeeds
            } catch (StaleElementReferenceException e) {
                attempt++;
            }
        }
        return new MyAccountPage(driver);
    }
    public ShopPage  clickShop(){
        clickLink("Shop");
        return new ShopPage(driver);
    }

    public CheckoutPage  clickCheckout(){
        clickLink("Checkout");
        return new CheckoutPage(driver);
    }

}
