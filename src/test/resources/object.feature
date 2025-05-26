Feature: Managing Objects via API with authentication
As an authenticated user
I want to add a new object via API
So that the object is stored in the system

  Background: 
    Given I have set the base url "https://whitesmokehouse.com/webhook"
    And Send a http "POST" request to login "/api/login" with body:
      """
      {
          "email": "gunawan12@gmail.com",
          "password": "p@sswrld00"
      }
      """
    And I save a valid authentication token

  Scenario: Add a new object
    When I send a http "POST" request to "/api/objects" with body:
      """
      {
          "name": "MacBook Pro M3",
          "data": {
              "year": 2024,
              "price": 4200,
              "cpu_model": "Apple M3 Pro",
              "hard_disk_size": "2 TB",
              "capacity": "1024",
              "screen_size": "14",
              "color": "Space Black"
          }
      }
      """
    Then The response status code should be 200
    And The response id should not be null
    And I save an object id
    And The response name should be "MacBook Pro M3"
    And The response year should be "2024"
    And The response price should be 4200
    And The response cpu model should be "Apple M3 Pro"
    And The response hard disk size should be "2 TB"
    And The response capacity should be "1024"
    And The response screen size should be "14"
    And The response color should be "Space Black"

  Scenario: Update an existing object
    When I send a http "PUT" request to "update-object-url" with body:
      """
      {
          "name": "[PROMO] MacBook Pro M3",
          "data": {
              "year": 2025,
              "price": 3800,
              "cpu_model": "Apple M3 Max",
              "hard_disk_size": "4 TB",
              "capacity": "2048",
              "screen_size": "16",
              "color": "Silver"
          }
      }
      """
    Then The update response status code should be 200
    And The update response id should not be null
    And The update response name should be "[PROMO] MacBook Pro M3"
    And The update response year should be "2025"
    And The update response price should be 3800
    And The update response cpu model should be "Apple M3 Max"
    And The update response hard disk size should be "4 TB"
    And The update response capacity should be "2048"
    And The update response screen size should be "16"
    And The update response color should be "Silver"

  Scenario: Delete an existing object
    When I send a http "DELETE" request to "delete-object-url" with body:
      """
      {}
      """
    Then The delete response status code should be 200
    And The delete response status should be "deleted"
    And The delete response message should be "delete-message-positive"

  Scenario: Delete a deleted object
    When I send a http "DELETE" request to "delete-object-url" with body:
      """
      {}
      """
    Then The delete response status code should be 200
    And The delete response status should be "failed"
    And The delete response message should be "delete-message-negative"
