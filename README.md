# Automation-BootcampAfterOffice-Selenium

# WEEK 3 (TASK 4) - Refactoring Code

1. How to run the test?
   - Using Terminal write
     `mvn test -DsuiteXml="src\test\resources\cucumber_suite.xml"`
   - The result will be displaying on Terminal
2. Location of the scripts :
   ```
   src\test\java\cucumber\Endpoints.java
   src\test\java\cucumber\TestContext.java
   src\test\java\helper\ConfigManager.java
   src\test\java\helper\GenerateReport.java
   .env-production
   [UPDATED] src\test\java\cucumber\definitions
   [UDATED] src\test\java\cucumber\hooks
   ```

# WEEK 2 (TASK 3) - Cucumber for API Testing with TestNG & POJO

1. How to run the test?
   - Using Terminal write
     `mvn test -DsuiteXml="src\test\resources\cucumber_suite.xml"`
   - The result will be displaying on Terminal
2. Test Scenario that I scripted;
   - Preconditions: Login User
   - Add a new Object
   - Update an Object (All)
   - Delete an Existing Object
   - Delete a Deleted Object
3. Location of the scripts :
   ```
   src\test\java\cucumber\runners
   src\test\java\cucumber\definitions
   src\test\resources\cucumber_suite.xml
   src\test\resources\object.feature
   ```

# WEEK 2 (TASK 2) - API Testing with TestNG & POJO

1. How to run the test?
   - Using Terminal write
     `mvn test -DsuiteXml="src\test\resources\testng_pojo_e2e.xml"`
     `mvn test -DsuiteXml="src\test\resources\testng_simple_suite.xml"`
   - The result will be displaying on Terminal & Test Results
2. E2E Test Scenario that i scripted;
   - Register and validation to login with registered data
   - Add Object & Validation on the list and single object after added the object
   - Delete Object & Validation on the list and single object after deleted the object
3. Location of the scripts :
   ```
   src\main\java\model
   src\main\java\response_model
   src\test\java\scenario_e2e\pojo
   src\test\resources\register_user_schema.json
   src\test\resources\add_object_schema.json
   ```
4. TestNG Annotations that I used;
   - @BeforeSuite
   - @BeforeClass
