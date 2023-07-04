package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.linkText;

public class CartPage {

    private final WebDriver driver;
    public By couponCodeField = By.cssSelector("input#coupon_code");
    private static final By couponCodeClick = By.name("apply_coupon");
    public By getSubTotal = By.cssSelector("#post-5 > div > div > div.cart-collaterals > div > table > tbody > tr.cart-subtotal > td > span > bdi");

    public By getDiscount = By.cssSelector("#post-5 > div > div > div.cart-collaterals > div > table > tbody > tr.cart-discount.coupon-edgewords > td > span");

    public By getTotal = By.cssSelector("#post-5 > div > div > div.cart-collaterals > div > table > tbody > tr.order-total > td > strong > span");

    public By proceedToCheckoutButton = linkText("Proceed to checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }


    public CartPage addCoupon(String couponcode) {
        driver.findElement(couponCodeField).sendKeys(couponcode);
        driver.findElement(couponCodeClick).click();
        return this;
    }

    public int checkDiscount() {
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        myWait.until(ExpectedConditions.visibilityOfElementLocated(getDiscount));
        String discount = driver.findElement(getDiscount).getText();
        String cleanDiscount = discount.replaceAll("[^0-9]", "");
        int discountInt = Integer.parseInt(cleanDiscount);
        return discountInt;
    }

    public int checkSubTotal() {
        String subTotal = driver.findElement(getSubTotal).getText();
        String cleanSubTotal = subTotal.replaceAll("[^0-9]", "");
        return Integer.parseInt(cleanSubTotal);
    }

    public int checkTotal() {
        String total = driver.findElement(getTotal).getText();
        String cleanTotal = total.replaceAll("[^0-9]", "");
        return Integer.parseInt(cleanTotal);
    }


    public void getCheckoutPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");
        WebElement checkout = driver.findElement(linkText("Proceed to checkout"));
        js.executeScript("arguments[0].scrollIntoView(true);", checkout);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        myWait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        driver.findElement(proceedToCheckoutButton).click();
    }
}

