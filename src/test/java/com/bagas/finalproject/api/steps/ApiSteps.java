package com.bagas.finalproject.api.steps;

import com.bagas.finalproject.api.client.DummyApiClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSteps {

    private DummyApiClient client;
    private Response response;
    private static String savedUserId; // biar bisa dipakai antar scenario

    private final String BASE_URL = "https://dummyapi.io/data/v1";
    private final String APP_ID = "63a804408eb0cb069b57e43a";

    @Given("I set DummyAPI base url")
    public void setBaseUrl() {
        client = new DummyApiClient(BASE_URL, APP_ID);
    }

    @When("I create a new user with firstName {string} lastName {string} email {string}")
    public void createUser(String firstName, String lastName, String email) {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);

        response = client.createUser(body);
        if (response.statusCode() == 200) {
            savedUserId = response.jsonPath().getString("id");
        }
    }

    @Given("I have an existing user id from previous create")
    public void haveUserId() {
        assertThat(savedUserId).as("User id must be created first").isNotBlank();
    }

    @When("I get user by that id")
    public void getUserById() {
        response = client.getUserById(savedUserId);
    }

    @When("I update user firstName to {string}")
    public void updateUser(String newFirstName) {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", newFirstName);
        response = client.updateUser(savedUserId, body);
    }

    @When("I delete user by that id")
    public void deleteUser() {
        response = client.deleteUser(savedUserId);
    }

    @When("I get list of tags")
    public void getTags() {
        response = client.getTags();
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int status) {
        assertThat(response.statusCode()).isEqualTo(status);
    }

    @Then("the response should contain {string}")
    public void verifyContainsField(String field) {
        String value = response.jsonPath().getString(field);
        assertThat(value).isNotNull();
    }
}
