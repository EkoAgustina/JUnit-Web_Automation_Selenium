@smokeTest
Feature: Swag Labs
  @Login
  Scenario: User logs in with a valid account
    Given User open "https://www.saucedemo.com"
    And User fills in "login.yml:usernameField" with "user_data.yml:username"
    And User fills in "login.yml:passwordField" with "user_data.yml:password"
    And User click "login.yml:loginButoon"
    And User wait 2 seconds
    Then Verify element "home.yml:home_header" will be displayed
    Then User take screenshot with file name "Homepage"

    @addProduct
  Scenario: User successfully added item to the cart
    Given User open "https://www.saucedemo.com"
    And User fills in "login.yml:usernameField" with "user_data.yml:username"
    And User fills in "login.yml:passwordField" with "user_data.yml:password"
    And User click "login.yml:loginButoon"
    And User wait 2 seconds
    Then Verify element "home.yml:home_header" will be displayed
    And User click "home.yml:add_backpack"
    And User click "home.yml:add_jacket"
    And User click "home.yml:cart"
    And User wait 2 seconds
    Then Verify value "cart.yml:list_cart1" is "equal" with data "cart_data.yml:backback"
    Then Verify value "cart.yml:list_cart2" is "equal" with data "cart_data.yml:jacket"
    Then User take screenshot with file name "CART"