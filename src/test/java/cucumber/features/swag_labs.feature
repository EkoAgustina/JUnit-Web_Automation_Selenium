@smokeTest
Feature: Swag Labs
  @Login
  Scenario: User logs in with a valid account
    Given User open "https://www.saucedemo.com"
    And Fill in "login:usernameField" with "user_data:username"
    And Fill in "login:passwordField" with "user_data:password"
    And User click "login:loginButoon"
    And Wait 2 seconds
    Then Element "home:home_header" will be displayed
    Then User take screenshot with file name "Homepage"

  @addProduct
  Scenario: User successfully added item to the cart
    Given User open "https://www.saucedemo.com"
    And Fill in "login:usernameField" with "user_data:username"
    And Fill in "login:passwordField" with "user_data:password"
    And User click "login:loginButoon"
    And Wait 2 seconds
    Then Element "home:home_header" will be displayed
    And User click "home:add_backpack"
    And User click "home:add_jacket"
    And User click "home:cart"
    And Wait 2 seconds
    Then Element "cart:list_cart1" is equal with data "cart_data:backback"
    Then Element "cart:list_cart2" is equal with data "cart_data:jacket"
    Then User take screenshot with file name "CART"