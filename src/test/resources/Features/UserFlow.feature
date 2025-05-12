Feature: User Registration, Sign In and Add to Cart
	
   @smoke
  	Scenario: Register a new user successfully
    Given user navigate to url 
    When user opens the registration page
    And enters registration data
    Then user account should be created
    
    @smoke
  	Scenario: Registration unsuccessful with duplicate user ID
    Given user opens the registration page
    And enters existing username as "M_fae0a23" and password as "test1234" and register
    Then user should see an error message
    
    @smoke
  	Scenario: Login unsuccessful with invalid user credentials
    Given user opens the login page
    And enters invalid username as "hello" and password as "test1234" and signin
    Then user should see an invalid signin error message
    
   	@smoke 
    Scenario: Sign in with valid user
    Given user opens the login page
    When user enters valid credentials
    Then user should be signed in
  
  	 @smoke 																										
    Scenario Outline: Add item to cart
  	Given user is signed in
  	When user choose "<category>" category
  	And select "<subCategory>"
  	And select "<product>" and add to cart
  	Then item should appear in the shopping cart

	Examples:
  	| category | subCategory | product |
  	| dogs     | Dalmation   | Spotless Male Puppy Dalmation|
  	| fish     | Angelfish   | Large Angelfish|
  	| birds    | Finch		   | Adult Male Finch|  	
    
    @smoke 
    Scenario: No checkout on cart clear
    Given user opens AddtoCart page
    When cart is cleared
    Then User should not be able to Proceed to Checkout
    