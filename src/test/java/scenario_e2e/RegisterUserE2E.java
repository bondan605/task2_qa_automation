package scenario_e2e;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RegisterUserE2E {

    public static String tokenLogin;

    @BeforeSuite
    public void setUp() {
        // Set up any necessary configurations or initializations here
        RestAssured.baseURI = "https://whitesmokehouse.com";
    }

    /*
     * SCENARIO REGISTER USER :
     * 1. Register User
     * 2. Login User
     */

    @Test
    public void RegisterUser() {

        String bodyRegister = "{"
                + "\"email\": \"gunawan00@gmail.com\","
                + "\"full_name\": \"Gunawan\","
                + "\"password\": \"p@sswrld00\","
                + "\"department\": \"Finance\","
                + "\"phone_number\": \"08123456789\""
                + "}";

        Response resRegister = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRegister)
                .log().all()
                .when()
                .post("/webhook/api/register");

        Assert.assertEquals(resRegister.getStatusCode(), 200,
                "Expected status code 200, but got " + resRegister.statusCode());
        Assert.assertNotNull(resRegister.jsonPath().get("id"), "'id' is null");
        Assert.assertEquals(resRegister.jsonPath().getString("email"), "gunawan00@gmail.com",
                "Expected email gunawan00@gmail.com, but got " + resRegister.jsonPath().getString("email"));
        Assert.assertEquals(resRegister.jsonPath().getString("full_name"), "Gunawan",
                "Expected full_name Gunawan, but got " + resRegister.jsonPath().getString("full_name"));
        Assert.assertEquals(resRegister.jsonPath().getString("department"), "Finance",
                "Expected department Finance, but got " + resRegister.jsonPath().getString("department"));
        Assert.assertEquals(resRegister.jsonPath().getString("phone_number"), "08123456789",
                "Expected phone_number 08123456789, but got " + resRegister.jsonPath().getString("phone_number"));
        Assert.assertNotNull(resRegister.jsonPath().get("create_at"), "'created_at' is null");
        Assert.assertNotNull(resRegister.jsonPath().get("update_at"), "'updated_at' is null");

        System.out.println("Response: " + resRegister.asPrettyString());
    }

    @Test(dependsOnMethods = "RegisterUser", groups = "LoginGroup")
    public void LoginUser() {

        String bodyLogin = "{\n"
                + "  \"email\": \"gunawan00@gmail.com\",\n"
                + "  \"password\": \"p@sswrld00\"\n"
                + "}";

        Response resLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyLogin)
                .log().all()
                .when()
                .post("/webhook/api/login");

        // Extract value token & simpan ke variable token
        tokenLogin = resLogin.jsonPath().getString("token");

        Assert.assertEquals(resLogin.getStatusCode(), 200,
                "Expected status code 200, but got " + resLogin.statusCode());
        Assert.assertNotNull(resLogin.jsonPath().get("token"), "'token' is null");

        System.out.println("Response: " + resLogin.asPrettyString());
    }

}