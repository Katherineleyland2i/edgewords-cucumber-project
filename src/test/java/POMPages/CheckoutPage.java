package POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.By.cssSelector;

public class CheckoutPage {

    WebDriver driver;
    public By billingFirstnameField = cssSelector("#billing_first_name");
    public By billingLastNameField = cssSelector("#billing_last_name");
    public By countryRegionSelector = By.cssSelector("#billing_country");
    public By streetAddressField = By.id("billing_address_1");
    public By townCityField = By.id("billing_city");
    public By postcodeField = By.id("billing_postcode");
    public By phoneField = By.id("billing_phone");
    By emailField = By.id("billing_email");
    By placeOrderButton = By.id("place_order");
    public By chequePayment = By.id("payment_method_cheque");

    By newPasswordField = By.id("account_password");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }


    public void setFirstName(String firstname) {
        driver.findElement(billingFirstnameField).clear();
        driver.findElement(billingFirstnameField).sendKeys(firstname);
    }

    public void setLastName(String lastname) {
        driver.findElement(billingLastNameField).clear();
        driver.findElement(billingLastNameField).sendKeys(lastname);
    }

    public void setCountryRegion(String country) {
        WebElement dropdown = driver.findElement(countryRegionSelector);
        Select select = new Select(dropdown);
        select.selectByValue(country);
    }

    public void setStreet(String street) {
        driver.findElement(streetAddressField).clear();
        driver.findElement(streetAddressField).sendKeys(street);
    }

    public void setTown(String town) {
        driver.findElement(townCityField).clear();
        driver.findElement(townCityField).sendKeys(town);
    }

    public void setPostcode(String postcode) {

        driver.findElement(postcodeField).clear();
        driver.findElement(postcodeField).sendKeys(postcode);
    }

    public void setPhone(String phonenumber) {
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phonenumber);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }
    public void setNewPassword(String password){
        driver.findElement(newPasswordField).clear();
        driver.findElement(newPasswordField).sendKeys(password);
    }

    public void clickCheckPayment() {
        WebElement radioButton = driver.findElement(chequePayment);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public OrderReceivedPage clickPlaceOrder() {
        int maxAttempts = 3;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                driver.findElement(placeOrderButton).click();
                break;
            } catch (StaleElementReferenceException e) {
                attempt++;
            }
        }

        return new OrderReceivedPage(driver);
    }
}
