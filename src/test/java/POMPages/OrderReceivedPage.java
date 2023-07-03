package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderReceivedPage {

    private WebDriver driver;

    public OrderReceivedPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getOrderNumber() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int orderNumber = Integer.parseInt(driver.findElement
                (By.cssSelector(".woocommerce-order-overview__order > strong")).getText());
        System.out.println("OrderNumber: " + orderNumber);
        return orderNumber;
    }

}
