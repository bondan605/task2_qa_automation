package scenario_e2e;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddObjectE2E {

    public static int objectId;

    @Test(dependsOnGroups = "LoginGroup")
    public void addObject() {

        String bodyAddObject =   "{\n"
                + "  \"name\": \"iPhone 15 Pro Max\",\n"
                + "  \"data\": {\n"
                + "    \"year\": 2023,\n"
                + "    \"price\": 1800,\n"
                + "    \"cpu_model\": \"Apple A17 Pro\",\n"
                + "    \"hard_disk_size\": \"512 GB\",\n"
                + "    \"capacity\": \"128\",\n"
                + "    \"screen_size\": \"6.7 inch\",\n"
                + "    \"color\": \"Natural Titanium\"\n"
                + "  }\n"
                + "}";

        Response resAddObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + RegisterUserE2E.tokenLogin)
                .body(bodyAddObject)
                .log().all()
                .when()
                .post("/webhook/api/objects");

        objectId = resAddObject.jsonPath().getInt("[0].id");
        System.out.println("Object has been added successfully with ID: " + objectId);

        Assert.assertEquals(resAddObject.getStatusCode(), 200);
        Assert.assertNotNull(objectId, "'id' is null");
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].name"), "iPhone 15 Pro Max");
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.year"), "2023");
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.price"), "1800");
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.cpu_model"), "Apple A17 Pro");
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.hard_disk_size"), "512 GB");
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.capacity"), "128");
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.screen_size"), "6.7 inch");
        Assert.assertEquals(resAddObject.jsonPath().getString("[0].data.color"), "Natural Titanium");

        System.out.println("Response: " + resAddObject.asPrettyString());
    }

    @Test(dependsOnMethods = "addObject", groups = "assertAddObject", priority = 1)
    public void getListObjectbyId() {
        Response resGetListById = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + RegisterUserE2E.tokenLogin)
                .log().all()
                .when()
                .get("/webhook/api/objectslistId?id=" + objectId);

        System.out.println("Validate Get List By ID: " + objectId);

        Assert.assertEquals(resGetListById.getStatusCode(), 200);
        Assert.assertNotNull(resGetListById.jsonPath().getInt("[0].id"));
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].name"), "iPhone 15 Pro Max");
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.year"), "2023");
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.price"), "1800");
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.cpu_model"), "Apple A17 Pro");
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.hard_disk_size"), "512 GB");
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.capacity"), "128");
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.screen_size"), "6.7");
        Assert.assertEquals(resGetListById.jsonPath().getString("[0].data.color"), "Natural Titanium");

        System.out.println("Response: " + resGetListById.asPrettyString());
    }

    @Test(dependsOnMethods = "getListObjectbyId", groups = "assertAddObject", priority = 2)
    public void getSingleObject() {
        Response resGetSingleObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + RegisterUserE2E.tokenLogin)
                .log().all()
                .when()
                .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + objectId);

        System.out.println("Validate Get Single Object for ID: " + objectId);

        Assert.assertEquals(resGetSingleObject.getStatusCode(), 200);
        Assert.assertNotNull(resGetSingleObject.jsonPath().getInt("id"));
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("name"), "iPhone 15 Pro Max");
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.year"), "2023");
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.price"), "1800");
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.cpu_model"), "Apple A17 Pro");
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.hard_disk_size"), "512 GB");
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.capacity"), "128");
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.screen_size"), "6.7");
        Assert.assertEquals(resGetSingleObject.jsonPath().getString("data.color"), "Natural Titanium");

        System.out.println("Response: " + resGetSingleObject.asPrettyString());
    }
}
