Feature: Add Object via API with authentication
As an authenticated user
I want to add a new object via API
So that the object is stored in the system

  @withLogin
  Scenario: Add a new Object with valid data
    When I send "POST" request to "/api/objects" with body:
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
    Then The add response status code should be 200
    And The add response id should not be null
    And I save the object ID
    And The add response name should be "MacBook Pro M3"
    And The add response year should be "2024"
    And The add response price should be 4200
    And The add response cpu model should be "Apple M3 Pro"
    And The add response hard disk size should be "2 TB"
    And The add response capacity should be "1024"
    And The add response screen size should be "14"
    And The add response color should be "Space Black"
