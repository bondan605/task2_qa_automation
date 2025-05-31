Feature: Update Object via API with authentication
As an authenticated user
I want to update an object via API
So that the object is updated in the system

  @withLogin
  Scenario: Update an existing Object with valid data
    When I send "PUT" request to "/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" with body:
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
