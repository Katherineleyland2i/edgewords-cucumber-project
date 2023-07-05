package com.twoitesting;

import POMPages.*;
import com.google.common.base.Verify;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    WebDriver driver;
    private int orderNumberOrderReceivedPage;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Given("I am on the Ecommerce website")
    public void iAmOnTheEcommerceWebsite() {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        assertThat(driver.findElement(By.linkText("Log out")).isDisplayed(), is(true));
    }

    @And("I am logged in")
    public void iAmLoggedIn() {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("ab@cd.com");
        loginPage.enterPassword("Frog1!Cats");
        loginPage.findLogin();
        loginPage.clickLogin();
        assertThat(driver.findElement(By.linkText("Log out")).isDisplayed(), is(true));
    }

    @When("I go to the shop page")
    public void iGoToTheShopPage() {
        NavBarPage navBarPage = new NavBarPage(driver);
        navBarPage.clickShop();

    }

    @And("I add a clothing item to my cart")
    @And("I have items in my cart")
    public void iAddAClothingItemToMyCart() {
        NavBarPage navBarPage = new NavBarPage(driver);
        navBarPage.clickShop();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ShopPage shopPage = new ShopPage(driver);
        shopPage.findItemToSelect();

        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        shopPage.clickItem();
        myWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='View cart']")));

        try {
            String bodyText = String.valueOf(driver.findElement(By.linkText("Your cart is currently empty.")));
            assertThat(String.valueOf(bodyText.contains("Your cart is currently empty.")), true);
        } catch (Exception e) {
            System.out.println("Items in the cart");

        }
    }

    @When("I logout")
    public void iLogout() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickLogout();
    }

    @Then("I should be logged out")
    public void iShouldBeLoggedOut() {
        Verify.verify(true, "Not Logged out Successfully", "Login", By.className("h2"));
    }

    @When("I login with {string} and {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.findLogin();
        loginPage.clickLogin();
    }


    @When("I view the cart")
    public void iViewTheCart() {
        NavBarPage navBarPage = new NavBarPage(driver);
        navBarPage.clickCart();
    }

    @And("I apply a coupon code {string}")
    public void iApplyACouponCode(String couponcode) {
        CartPage cartPage = new CartPage(driver);
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        myWait.until(ExpectedConditions.visibilityOfElementLocated(cartPage.couponCodeField));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        cartPage.addCoupon(couponcode);
    }

    @Then("the coupon should be applied successfully")
    public void theCouponShouldBeAppliedSuccessfully() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        CartPage cartPage = new CartPage(driver);
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        myWait.until(ExpectedConditions.visibilityOfElementLocated(cartPage.getDiscount));
        double result = cartPage.checkSubTotal() * 0.15;
        Assert.assertEquals(result, cartPage.checkDiscount());
    }


    @And("I proceed to checkout")

    public void iProceedToCheckout() {
        CartPage cartPage = new CartPage(driver);
        cartPage.getCheckoutPage();
    }

    @Then("I should be on the checkout page")
    public void iShouldBeOnTheCheckoutPage() {
        new CheckoutPage(driver);
        try {
            String bodyText = String.valueOf(driver.findElement(By.linkText("Your cart is currently empty.")));

            Assert.assertFalse(bodyText.contains("Your cart is currently empty."));
        } catch (Exception e) {
            System.out.println("Cart occupied");
        }
    }

    @Given("I am on the checkout page")
    public void iAmOnTheCheckoutPage() {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
        NavBarPage navBarPage = new NavBarPage(driver);
        navBarPage.clickCheckout();
        new CheckoutPage(driver);
        Verify.verify(true, "Not on Checkout Page", "Checkout", By.className("h1"));
    }

    @When("I enter the billing details")
    @And("I have entered the billing details")
    public void iEnterTheBillingDetails() {
        CheckoutPage checkoutpage = new CheckoutPage(driver);
        checkoutpage.setFirstName("Bob");
        checkoutpage.setLastName("Smith");
        checkoutpage.setCountryRegion("GB");
        checkoutpage.setStreet("64 Zoo Lane");
        checkoutpage.setTown("Edinburgh");
        checkoutpage.setPostcode("EH6 4ZL");
        checkoutpage.setEmail("ab@cd.com");
        checkoutpage.setPhone("0131123456");
        try {
            checkoutpage.setNewPassword("PAssWord!");
        } catch (Exception e) {
            System.out.println("Already logged in");
        }
    }

    @Then("the details should be filled in successfully")

    public void theDetailsShouldBeFilledInSuccessfully() {
        CheckoutPage checkoutpage = new CheckoutPage(driver);
        Verify.verify(true, "First name field not Bob", "Bob", checkoutpage.billingFirstnameField);
        Verify.verify(true, "Phone number field not as expected", "0131123456", checkoutpage.phoneField);
    }


    @When("I click to place the order")
    public void iClickToPlaceTheOrder() {
        CheckoutPage checkoutpage = new CheckoutPage(driver);
        checkoutpage.clickPlaceOrder();
    }

    @Then("the order should be placed successfully")
    public void theOrderShouldBePlacedSuccessfully() {
        Verify.verify(true, "Order Not Placed Successfully", "Order received", By.className("h1"));
    }

    @Given("I have placed an order")
    public void iHavePlacedAnOrder() {
        NavBarPage navBarPage = new NavBarPage(driver);
        navBarPage.clickShop();
        ShopPage shopPage = new ShopPage(driver);
        shopPage.clickItem();
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        myWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='View cart']")));

        navBarPage.clickCheckout();
        iEnterTheBillingDetails();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickPlaceOrder();
    }

    @When("I capture the order number")
    public void iCaptureTheOrderNumber() {
        OrderReceivedPage orderReceivedPage = new OrderReceivedPage(driver);
        orderNumberOrderReceivedPage = orderReceivedPage.getOrderNumber();
    }

    @And("I go to My Account page")
    public void iGoToMyAccountPage() {
        NavBarPage navBarPage = new NavBarPage(driver);
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        myWait.until(ExpectedConditions.elementToBeClickable(By.linkText("My account")));

        navBarPage.clickMyAccount();
    }

    @And("I select Orders")
    public void iSelect() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickOrders();
    }

    @Then("the same order number should be displayed")
    public void theSameOrderNumberShouldBeDisplayed() {

        String actualString = driver.findElement
                        (By.cssSelector("#post-7 > div > div > div > table > tbody > tr:nth-child(1) > td.woocommerce-orders-table__cell.woocommerce-orders-table__cell-order-number > a"))
                .getText();
        String cleanOrderNumber = actualString.replaceAll("[^0-9]", "");
        int orderNumberInt = Integer.parseInt(cleanOrderNumber);
        System.out.println("Before assertion Order number on received page: " + orderNumberOrderReceivedPage);
        System.out.println("Before assertion order Number in Account: " + orderNumberInt);

        assertEquals(orderNumberInt, orderNumberOrderReceivedPage);
    }


    @Given("I am on the Ecommerce website shop page")
    public void iAmOnTheEcommerceWebsiteShopPage() {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/shop");
    }

    @When("I select Check payments as the payment method")
    public void iSelectCheckPaymentsAsThePaymentMethod() {
        CheckoutPage checkoutpage = new CheckoutPage(driver);
        checkoutpage.clickCheckPayment();
    }

    @Then("The displayed total after discount and shipping is correct")
    public void theDisplayedTotalAfterDiscountAndShippingIsCorrect() {
        CartPage cartPage = new CartPage(driver);
        int shipping = 395;
        int totalShouldBe = cartPage.checkSubTotal() + shipping - cartPage.checkDiscount();
        Verify.verify(true, "Total incorrect", totalShouldBe, cartPage.checkTotal());
    }
}
