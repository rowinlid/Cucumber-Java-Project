Feature: Delete products using DELETE API
  Scenario Outline: Validate delete product api status code works correctly
    Given I hit the url of delete products api endpoint
    When I pass the url of delete products in the request with <ProductNumber>
    Then I receive the response code as 200
  Examples:
    |ProductNumber|
    |1            |