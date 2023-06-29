Feature: Login

  Scenario: Login Test
    Given I am on the Ecommerce website
    When I login with username "ab@cd.com" and password "Frog1!Cats"
    Then I should be logged in

Feature: Add to Cart

  Scenario: Add Clothing Item to Cart
    Given I am on the Ecommerce website
    And I am logged in
    When I go to the shop page
    And I add a clothing item to my cart
    Then the item should be added to my cart

Feature: Apply Coupon

  Scenario: Apply Coupon Code
    Given I am on the Ecommerce website
    And I am logged in
    And I have items in my cart
    When I view the cart
    And I apply a coupon code "Edgewords"
    Then the coupon should be applied successfully

Feature: Checkout

  Scenario: Proceed to Checkout
    Given I am on the Ecommerce website
    And I am logged in
    And I have items in my cart
    When I view the cart
    And I proceed to checkout
    Then I should be on the checkout page

Feature: Complete Billing Details

  Scenario: Fill in Billing Details
    Given I am on the checkout page
    When I enter the billing details
    Then the details should be filled in successfully

Feature: Payment Method

  Scenario: Select Check Payments
    Given I am on the checkout page
    When I select "Check payments" as the payment method
    Then "Check payments" should be selected

Feature: Place Order

  Scenario: Place the Order
    Given I am on the checkout page
    When I place the order
    Then the order should be placed successfully

Feature: Order Number

  Scenario: Capture and Verify Order Number
    Given I have placed an order
    When I capture the order number
    And I go to "My Account" page
    And I select "Orders"
    Then the order number should be displayed

Feature: Logout

  Scenario: Logout from Account
    Given I am logged in
    When I logout
    Then I should be logged out





