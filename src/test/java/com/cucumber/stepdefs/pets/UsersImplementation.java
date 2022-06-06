package com.cucumber.stepdefs.pets;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.json.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;


public class UsersImplementation implements Serializable {
    private Response putUsers = null;
    private Response postUsers = null;
    private Response deleteUsers = null;

    @Before("@user")
    public void before() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/user";

    }
    @When("the following get request that brings us the users")
    public Response getUser() {
        Response responseGetUsers = given().log().all().get("/pperez");
        return responseGetUsers;
    }

    @Then("the response is 200 user")
    public void validateResponseUser() {
        assertTrue("The response is not 200", getUser().statusCode() == 200);

    }

    @Given("the following UserPost request that add users")
    public void setPostUsers() {
        File bodyRequestPostUser = new File("src/main/resources/data/bodyRequestPostUser.json");
        postUsers = given().contentType(ContentType.JSON).body(bodyRequestPostUser).post();
    }
    @And("the response is 200 for the UserPost")
    public void validateResponsePost() {
        assertTrue("The response is not 200", postUsers.statusCode() == 200);
    }
    @Then("the body response contains post update {string}")
            public void validateResponsePostBodyValueName (String addUsers) {
            JsonPath jsonPathUsers = new JsonPath(postUsers.body().asString());
            String jsonUserName = jsonPathUsers.getString("user");
            assertEquals("The value of the name field is not what is expected", addUsers, jsonUserName);
    }
    @Given("the following put request that update users")
    public void putUsers() {
        File bodyRequestPutPets = new File("src/main/resources/data/bodyRequestPutUser.json");
        putUsers = given().contentType(ContentType.JSON).body(bodyRequestPutPets).put();
    }

    @And("the response is 200 for the put user")
    public void validateResponsePut() {
        assertTrue("The response is not 200", putUsers.statusCode() == 200);
    }

    @Then("the body response contains put update {string} of the user")
    public void validateResponsePutBodyUpdatedValueName(String updatedName) {
        JsonPath jsonPathUsers = new JsonPath(putUsers.body().asString());
        String jsonUserName = jsonPathUsers.getString("name");
        assertEquals("The value of the name field is not what is expected", updatedName, jsonUserName);
    }

    @Given("The following get request that delete the user")
    public Response deleteUsers() {
        Response responseDeleteUsers = given().log().all().delete("/ccalvo") ;
        return responseDeleteUsers;
    }
    @Then("the response for delete users is 200")
    public void validateResponseDelete() {
        assertTrue("The response is not 200", deleteUsers().statusCode() == 200);
    }






}






