package scenario_e2e.pojo;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import model.UserModel;
import response_model.LoginUserResponse;
import response_model.RegisterUserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PojoRegisterUserE2E {


    @BeforeSuite
    public void setUp() {

        StaticVar.user = new UserModel();

        StaticVar.user.setEmail("gunawan12@gmail.com");
        StaticVar.user.setFullName("Gunawan");
        StaticVar.user.setPassword("p@sswrld00");
        StaticVar.user.setDepartment("Finance");
        StaticVar.user.setPhoneNumber("08123456789");
    }

    /*
     * SCENARIO REGISTER USER :
     * 1. Register User
     * 2. Login User
     */

    @Test
    public void RegisterUser() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyRegister = objectMapper.writeValueAsString(StaticVar.user);

        Response resRegister = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRegister)
                .log().all()
                .when()
                .post(StaticVar.BASE_URL + "/api/register");

        System.out.println("Response: " + resRegister.asPrettyString());

        Assert.assertEquals(resRegister.getStatusCode(), 200,
                "Expected status code 200, but got " + resRegister.statusCode());

        resRegister.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("register_user_schema.json"));

        RegisterUserResponse registerUserResponse = objectMapper.readValue(resRegister.body().asString(), RegisterUserResponse.class);

        Assert.assertNotNull(registerUserResponse.getId(), "'id' is null");
        Assert.assertEquals(registerUserResponse.getEmail(), StaticVar.user.getEmail(),
                "Expected email " + StaticVar.user.getEmail() + ", but got " + registerUserResponse.getEmail());
        Assert.assertEquals(registerUserResponse.getFullName(), StaticVar.user.getFullName(),
                "Expected full_name " + StaticVar.user.getFullName() + ", but got " + registerUserResponse.getFullName());
        Assert.assertEquals(registerUserResponse.getDepartment(), StaticVar.user.getDepartment(),
                "Expected department " + StaticVar.user.getDepartment() + ", but got " + registerUserResponse.getDepartment());
        Assert.assertEquals(registerUserResponse.getPhoneNumber(), StaticVar.user.getPhoneNumber(),
                "Expected phone_number " + StaticVar.user.getPhoneNumber() + ", but got " + registerUserResponse.getPhoneNumber());
        Assert.assertNotNull(registerUserResponse.getCreatedAt(), "'created_at' is null");
        Assert.assertNotNull(registerUserResponse.getUpdatedAt(), "'updated_at' is null");

        System.out.println("Response: " + resRegister.asPrettyString());

    }

    @Test(dependsOnMethods = "RegisterUser", groups = "LoginGroup")
    public void LoginUser() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyLogin = objectMapper.writeValueAsString(StaticVar.user);

        Response resLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyLogin)
                .log().all()
                .when()
                .post(StaticVar.BASE_URL + "/api/login");

        LoginUserResponse loginUserResponse = objectMapper.readValue(resLogin.body().asString(), LoginUserResponse.class);

        Assert.assertEquals(resLogin.getStatusCode(), 200,
                "Expected status code 200, but got " + resLogin.statusCode());

        Assert.assertNotNull(loginUserResponse.getToken(), "'token' is null");
        // Extract value token & simpan ke variable token
        StaticVar.tokenLogin = loginUserResponse.getToken();

        System.out.println("Response: " + resLogin.asPrettyString());
    }

}