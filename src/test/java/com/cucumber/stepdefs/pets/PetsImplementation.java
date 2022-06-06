package com.cucumber.stepdefs.pets;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;


public class PetsImplementation implements Serializable {
    private Response putPets = null;
    private Response postPets = null;
    private Response deletePets = null;

    @Before("@pet")
    public void beforePet() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet/";

    }

    @Given("the following get request that brings us the pets")
    public Response getPets() {
        Response responseGetPets = given().log().all().get("/666");
        return responseGetPets;
    }

    @Then("the response is 200")
    public void validateResponse() {
        assertTrue("The response is not 200", this.getPets().statusCode() == 200);


    }

    @Given("the following petsPost request to add pets")
    public void PostPets() {

        File bodyRequestPostPets = new File("src/main/resources/data/bodyRequestPostPets.json");
        postPets = given().contentType(ContentType.JSON).body(bodyRequestPostPets).post();

    }

    @And("the response is 200 for post")
    public void validateResponsePost() {
        assertTrue("The response is not 200", postPets.statusCode() == 200);
    }

    @Then("the body response contains the {string} of the new pet")
    public void validateResponsePostValueName(String valueName) {
        JsonPath jsonPathPets = new JsonPath(postPets.body().asString());
        String jsonPets = jsonPathPets.getString("name");
        assertEquals("The value of the name is not correct", valueName, jsonPets);

    }

    @Given("the following put request that update pets")
    public void putPets() {
        File bodyRequestPutPets = new File("src/main/resources/data/bodyRequestPutPets.json");
        putPets = given().contentType(ContentType.JSON).body(bodyRequestPutPets).put();
    }

    @And("the response is 200 for the put pets")
    public void validateResponsePut() {
        assertTrue("The response is not 200", putPets.statusCode() == 200);
    }

    @Then("the body response contains update {string} pets")
    public void validateResponsePutPetsUpdatedValueName(String updatedName) {
        JsonPath jsonPathPets = new JsonPath(putPets.body().asString());
        String jsonUserName = jsonPathPets.getString("name");
        assertEquals("The value of the name field is not what is expected", updatedName, jsonUserName);
    }

    @Given("The following get request that delete the pet")
    public Response deletePets() {
        Response responseDeletePets = given().log().all().delete("/666");
        return responseDeletePets;
    }


    @Then("the response for delete pets is 404")
    public void validateResponseDeletePets() {
        System.out.println(deletePets().statusCode());
        assertTrue("The response is not 404", deletePets().statusCode() == 404);

    }

}


