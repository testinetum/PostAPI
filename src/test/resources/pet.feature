Feature: Pet API

  Scenario: Create a new pet
    Given the pet data is read from "pet.json"
    When I send a POST request to "/pet"
    Then the response status should be 200
    And the response body should contain "id" with value 234
