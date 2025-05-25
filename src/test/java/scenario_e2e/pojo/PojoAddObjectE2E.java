package scenario_e2e.pojo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import response_model.AddObjectResponse;
import response_model.GetListObjectByIdResponse;
import response_model.GetSingleObjectResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import io.restassured.RestAssured;
import model.ObjectModel;
import io.restassured.response.Response;

public class PojoAddObjectE2E {

    @BeforeClass
    public void setUp() {

        StaticVar.object = new ObjectModel();
        StaticVar.objectDataDetail = new ObjectModel.DataDetail();

        StaticVar.object.setName("iPhone 14 Pro Max");
        StaticVar.objectDataDetail.setYear("2022");
        StaticVar.objectDataDetail.setPrice(1600.0);
        StaticVar.objectDataDetail.setCpuModel("A16 Bionic");
        StaticVar.objectDataDetail.setHardDiskSize("256 GB");
        StaticVar.objectDataDetail.setCapacity("4323");
        StaticVar.objectDataDetail.setScreenSize("6.7");
        StaticVar.objectDataDetail.setColor("Deep Purple");


        StaticVar.object.setData(StaticVar.objectDataDetail);
    }

    /*
     * SCENARIO ADD OBJECT :
     * 1. Add Object
     * 2. Get List By ID Object
     * 3. Get Single Object
     */

    @Test(dependsOnGroups = "LoginGroup")
    public void addObject() throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();
        String bodyAddObject = objectMapper.writeValueAsString(StaticVar.object);

        Response resAddObject = RestAssured.given()
                .header("Content-Type", "application/json")
                // Passing token dari APILogin ke API Add Object
                .header("Authorization", "Bearer " + StaticVar.tokenLogin)
                .body(bodyAddObject)
                .log().all()
                .when()
                .post(StaticVar.BASE_URL +"/api/objects")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("add_object_schema.json"))
                .extract()
                .response();

        // Extract value id & simpan ke variable id
        StaticVar.objectId = resAddObject.jsonPath().getInt("[0].id");
        System.out.println("Object has been added successfully with ID: " + StaticVar.objectId);

        Assert.assertEquals(resAddObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resAddObject.statusCode());

        List<AddObjectResponse> addObjectResponse = objectMapper.readValue(resAddObject.body().asString(),new TypeReference<List<AddObjectResponse>>() {});

        Assert.assertNotNull(addObjectResponse.get(0).getId(), "'ID' is null");

        Assert.assertEquals(addObjectResponse.get(0).getName(), StaticVar.object.getName(),
                "Expected name "+ StaticVar.object.getName() +", but got " + addObjectResponse.get(0).getName());

        Assert.assertEquals(addObjectResponse.get(0).getData().getYear(), StaticVar.objectDataDetail.getYear(),
                "Expected year "+ StaticVar.objectDataDetail.getYear() +", but got " + addObjectResponse.get(0).getData().getYear());

        Assert.assertEquals(addObjectResponse.get(0).getData().getPrice(), StaticVar.objectDataDetail.getPrice(),
                "Expected price "+ StaticVar.objectDataDetail.getPrice() +", but got " + addObjectResponse.get(0).getData().getPrice());

        Assert.assertEquals(addObjectResponse.get(0).getData().getCpuModel(), StaticVar.objectDataDetail.getCpuModel(),
                "Expected cpu_model "+ StaticVar.objectDataDetail.getCpuModel() +", but got " + addObjectResponse.get(0).getData().getCpuModel());

        Assert.assertEquals(addObjectResponse.get(0).getData().getHardDiskSize(), StaticVar.objectDataDetail.getHardDiskSize(),
                "Expected hard_disk_size "+ StaticVar.objectDataDetail.getHardDiskSize() +", but got " + addObjectResponse.get(0).getData().getHardDiskSize());

        Assert.assertEquals(addObjectResponse.get(0).getData().getCapacity(), StaticVar.objectDataDetail.getCapacity(),
                "Expected capacity "+ StaticVar.objectDataDetail.getCapacity() +", but got " + addObjectResponse.get(0).getData().getCapacity());

        Assert.assertEquals(addObjectResponse.get(0).getData().getScreenSize(), StaticVar.objectDataDetail.getScreenSize(),
                "Expected screen_size "+ StaticVar.objectDataDetail.getScreenSize() +", but got " + addObjectResponse.get(0).getData().getScreenSize());

        Assert.assertEquals(addObjectResponse.get(0).getData().getColor(), StaticVar.objectDataDetail.getColor(),
                "Expected color "+ StaticVar.objectDataDetail.getColor() +", but got " + addObjectResponse.get(0).getData().getColor());

        System.out.println("Response: " + resAddObject.asPrettyString());

    }


    @Test(dependsOnMethods = "addObject", groups = "assertAddObject", priority = 1)
    public void getListObjectbyId() throws Exception {

        Response resGetListById = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + StaticVar.tokenLogin)
                .log().all()
                .when()
                .get(StaticVar.BASE_URL +"/api/objectslistId?id=" + StaticVar.objectId);

        System.out.println("Validate Get List By ID: " + StaticVar.objectId);

        Assert.assertEquals(resGetListById.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetListById.statusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        List<GetListObjectByIdResponse> getListObjectByIdResponse = objectMapper.readValue(resGetListById.body().asString(), new TypeReference<List<GetListObjectByIdResponse>>() {});

        Assert.assertNotNull(getListObjectByIdResponse.get(0).getId(), "'ID' is null");

        Assert.assertEquals(getListObjectByIdResponse.get(0).getName(), StaticVar.object.getName(),
                "Expected name " + StaticVar.object.getName() + ", but got " + getListObjectByIdResponse.get(0).getName());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getYear(), StaticVar.objectDataDetail.getYear(),
                "Expected year " + StaticVar.objectDataDetail.getYear() + ", but got " + getListObjectByIdResponse.get(0).getData().getYear());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getPrice(), StaticVar.objectDataDetail.getPrice(),
                "Expected price " + StaticVar.objectDataDetail.getPrice() + ", but got " + getListObjectByIdResponse.get(0).getData().getPrice());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getCpuModel(), StaticVar.objectDataDetail.getCpuModel(),
                "Expected cpu_model " + StaticVar.objectDataDetail.getCpuModel() + ", but got " + getListObjectByIdResponse.get(0).getData().getCpuModel());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getHardDiskSize(), StaticVar.objectDataDetail.getHardDiskSize(),
                "Expected hard_disk_size " + StaticVar.objectDataDetail.getHardDiskSize() + ", but got " + getListObjectByIdResponse.get(0).getData().getHardDiskSize());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getCapacity(), StaticVar.objectDataDetail.getCapacity(),
                "Expected capacity " + StaticVar.objectDataDetail.getCapacity() + ", but got " + getListObjectByIdResponse.get(0).getData().getCapacity());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getScreenSize(), StaticVar.objectDataDetail.getScreenSize(),
                "Expected screen_size " + StaticVar.objectDataDetail.getScreenSize() + ", but got " + getListObjectByIdResponse.get(0).getData().getScreenSize());

        Assert.assertEquals(getListObjectByIdResponse.get(0).getData().getColor(), StaticVar.objectDataDetail.getColor(),
                "Expected color " + StaticVar.objectDataDetail.getColor() + ", but got " + getListObjectByIdResponse.get(0).getData().getColor());

        System.out.println("Response: " + resGetListById.asPrettyString());

    }

    @Test(dependsOnMethods = "getListObjectbyId", groups = "assertAddObject", priority = 2)
    public void getSingleObject() throws Exception {
        Response resGetSingleObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + StaticVar.tokenLogin)
                .log().all()
                .when()
                .get(StaticVar.BASE_URL +"/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + StaticVar.objectId);

        System.out.println("Validate Get Single Object for ID: " + StaticVar.objectId);

        Assert.assertEquals(resGetSingleObject.getStatusCode(), 200,
                "Expected status code 200, but got " + resGetSingleObject.statusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        GetSingleObjectResponse getSingleObjectResponse = objectMapper.readValue(resGetSingleObject.body().asString(), GetSingleObjectResponse.class);

        Assert.assertNotNull(getSingleObjectResponse.getId(), "'ID' is null");

        Assert.assertEquals(getSingleObjectResponse.getName(), StaticVar.object.getName(),
                "Expected name " + StaticVar.object.getName() + ", but got " + getSingleObjectResponse.getName());

        Assert.assertEquals(getSingleObjectResponse.getData().getYear(), StaticVar.objectDataDetail.getYear(),
                "Expected year " + StaticVar.objectDataDetail.getYear() + ", but got " + getSingleObjectResponse.getData().getYear());

        Assert.assertEquals(getSingleObjectResponse.getData().getPrice(), StaticVar.objectDataDetail.getPrice(),
                "Expected price " + StaticVar.objectDataDetail.getPrice() + ", but got " + getSingleObjectResponse.getData().getPrice());

        Assert.assertEquals(getSingleObjectResponse.getData().getCpuModel(), StaticVar.objectDataDetail.getCpuModel(),
                "Expected cpu_model " + StaticVar.objectDataDetail.getCpuModel() + ", but got " + getSingleObjectResponse.getData().getCpuModel());

        Assert.assertEquals(getSingleObjectResponse.getData().getHardDiskSize(), StaticVar.objectDataDetail.getHardDiskSize(),
                "Expected hard_disk_size " + StaticVar.objectDataDetail.getHardDiskSize() + ", but got " + getSingleObjectResponse.getData().getHardDiskSize());

        Assert.assertEquals(getSingleObjectResponse.getData().getCapacity(), StaticVar.objectDataDetail.getCapacity(),
                "Expected capacity " + StaticVar.objectDataDetail.getCapacity() + ", but got " + getSingleObjectResponse.getData().getCapacity());

        Assert.assertEquals(getSingleObjectResponse.getData().getScreenSize(), StaticVar.objectDataDetail.getScreenSize(),
                "Expected screen_size " + StaticVar.objectDataDetail.getScreenSize() + ", but got " + getSingleObjectResponse.getData().getScreenSize());

        Assert.assertEquals(getSingleObjectResponse.getData().getColor(), StaticVar.objectDataDetail.getColor(),
                "Expected color " + StaticVar.objectDataDetail.getColor() + ", but got " + getSingleObjectResponse.getData().getColor());

        System.out.println("Response: " + resGetSingleObject.asPrettyString());

    }

}