package com.bagas.finalproject.api.steps;

import com.bagas.finalproject.api.client.DummyApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ApiSteps {

    private final DummyApiClient api = new DummyApiClient();

    private Response lastResponse;
    private String createdUserId;


    @When("user request tag list")
    public void userRequestTagList() {
        lastResponse = api.getTags();
    }

    @Then("tag list response status should be {int}")
    public void tagListResponseStatusShouldBe(Integer status) {
        Assertions.assertEquals(status.intValue(), lastResponse.statusCode(), lastResponse.asString());
    }


    @When("user request user list")
    public void userRequestUserList() {
        lastResponse = api.getUserList();
    }

    @Then("user list response status should be {int}")
    public void userListResponseStatusShouldBe(Integer status) {
        Assertions.assertEquals(status.intValue(), lastResponse.statusCode(), lastResponse.asString());
    }


    @Given("prepare valid user payload")
    public void prepareValidUserPayload() {

    }

    @When("user create new user")
    public void userCreateNewUser() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "Bagas");
        body.put("lastName", "Automation");
        body.put("email", "bagas.automation." + System.currentTimeMillis() + "@mail.com");

        lastResponse = api.createUser(body);


        if (lastResponse.statusCode() == 200 || lastResponse.statusCode() == 201) {
            createdUserId = lastResponse.jsonPath().getString("id");
        }
    }

    @Then("create user response status should be {int} or {int}")
    public void createUserResponseStatusShouldBeOr(Integer a, Integer b) {
        int code = lastResponse.statusCode();
        Assertions.assertTrue(code == a || code == b, "Unexpected status: " + code + "\n" + lastResponse.asString());
        Assertions.assertNotNull(createdUserId, "createdUserId null\n" + lastResponse.asString());
    }


    @When("user update created user")
    public void userUpdateCreatedUser() {
        Assertions.assertNotNull(createdUserId, "createdUserId null - create step gagal atau tidak jalan");

        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "BagasUpdated");

        lastResponse = api.updateUser(createdUserId, body);
    }

    @Then("update user response status should be {int}")
    public void updateUserResponseStatusShouldBe(Integer status) {

        int code = lastResponse.statusCode();
        Assertions.assertEquals(status.intValue(), code, lastResponse.asString());


        if (lastResponse.getBody() != null && lastResponse.asString().contains("\"id\"")) {
            String id = lastResponse.jsonPath().getString("id");
            if (id != null) {
                Assertions.assertEquals(createdUserId, id, lastResponse.asString());
            }
        }
    }


    @When("user delete created user")
    public void userDeleteCreatedUser() {
        Assertions.assertNotNull(createdUserId, "createdUserId null - create step gagal atau tidak jalan");
        lastResponse = api.deleteUser(createdUserId);
    }

    @Then("delete user response status should be {int} or {int}")
    public void deleteUserResponseStatusShouldBeOr(Integer a, Integer b) {
        int code = lastResponse.statusCode();
        Assertions.assertTrue(code == a || code == b, "Unexpected status: " + code + "\n" + lastResponse.asString());
    }


    @Given("user has invalid user id")
    public void userHasInvalidUserId() {

    }

    @When("user request user by invalid id")
    public void userRequestUserByInvalidId() {
        lastResponse = api.getUserById("invalid-id-0000");
    }

    @Then("get user invalid id response status should be {int} or {int}")
    public void getUserInvalidIdResponseStatusShouldBeOr(Integer a, Integer b) {
        int code = lastResponse.statusCode();
        Assertions.assertTrue(code == a || code == b, "Unexpected status: " + code + "\n" + lastResponse.asString());
    }
}
