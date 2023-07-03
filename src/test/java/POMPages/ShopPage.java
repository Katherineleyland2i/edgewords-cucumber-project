package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShopPage {
    private WebDriver driver;
    public WebElement selectItem;

    public ShopPage(WebDriver driver) {this.driver = driver;}

    public void findItemToSelect(){selectItem = driver.findElement(By.xpath("//main[@id='main']/ul//a[@href='?add-to-cart=30']"));
    }

    public void clickItem(){
        findItemToSelect();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sunglasses = selectItem;
        js.executeScript("arguments[0].scrollIntoView(true);", sunglasses);
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        myWait.until(ExpectedConditions.elementToBeClickable(selectItem));
        selectItem.click();}
}


