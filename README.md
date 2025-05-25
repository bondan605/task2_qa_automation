# Automation-BootcampAfterOffice-Selenium
# WEEK 2 (TASK 2) - API Testing with TestNG & POJO
1. How to run the test?
   - Using Terminal write
     ``` mvn test -DsuiteXml="src\test\resources\testng_pojo_e2e.xml" ```
     ``` mvn test -DsuiteXml="src\test\resources\testng_simple_suite.xml" ```
   - The result will be displaying on Terminal & Test Results  
2. E2E Test Scenario that i scripted;
   - Register and validation to login with registered data
   - Add Object & Validation on the list and single object after added the object
   - Delete Object & Validation on the list and single object after deleted the object
3. Location of the scripts :
   ```
   src\main\java\com\demo\testng\program\model
   src\main\java\com\demo\testng\program\response_model
   src\test\java\scenario_e2e\pojo
   src\test\resources\register_user_schema.json
   src\test\resources\add_object_schema.json
   ```
4. TestNG Annotations that I used;
   - @BeforeSuite
   - @BeforeClass