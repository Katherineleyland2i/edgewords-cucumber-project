Feature: Purchase test

  Background: Be on the Ecommerce shop page
    Given I am on the Ecommerce website shop page

  Scenario Outline: Login Test
    Given I am on the Ecommerce website
    When I login with <username> and <password>
    Then I should be logged in
    Examples:
      | username    | password     |
      | "ab@cd.com" | "Frog1!Cats" |


  Scenario: Add Clothing Item to Cart and Add coupon
    Given I am on the Ecommerce website shop page
    When I add a clothing item to my cart
    And I view the cart
    And I apply a coupon code "Edgewords"
    Then the coupon should be applied successfully

  Scenario: Check totals in cart are correct
    Given I have items in my cart
    When I view the cart
    And I apply a coupon code "Edgewords"
    Then The displayed total after discount and shipping is correct

  Scenario: Proceed to Checkout
    Given I am on the Ecommerce website shop page
    And I have items in my cart
    When I view the cart
    And I proceed to checkout
    Then I should be on the checkout page


  Scenario: Fill in Billing Details
    Given I have items in my cart
    Given I am on the checkout page
    When I enter the billing details
    Then the details should be filled in successfully


  Scenario: Select Check Payments
    And I have items in my cart
    Given I am on the checkout page
    And I have entered the billing details
    When I select Check payments as the payment method
    And I click to place the order
    Then the order should be placed successfully


  Scenario: Capture and Verify Order Number
    Given I am logged in
    And I have placed an order
    When I capture the order number
    And I go to My Account page
    And I select Orders
    Then the same order number should be displayed


  Scenario: Logout from Account
    Given I am logged in
    When I logout
    Then I should be logged out





