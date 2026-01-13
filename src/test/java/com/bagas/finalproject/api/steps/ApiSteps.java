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

    // =========================
    // TAGS
    // =========================
    @When("user request tag list")
    public void userRequestTagList() {
        lastResponse = api.getTags();
    }

    @Then("tag list response status should be {int}")
    public void tagListResponseStatusShouldBe(Integer status) {
        Assertions.assertEquals(status.intValue(), lastResponse.statusCode(), lastResponse.asString());
    }

    // =========================
    // USERS - LIST
    // =========================
    @When("user request user list")
    public void userRequestUserList() {
        lastResponse = api.getUserList();
    }

    @Then("user list response status should be {int}")
    public void userListResponseStatusShouldBe(Integer status) {
        Assertions.assertEquals(status.intValue(), lastResponse.statusCode(), lastResponse.asString());
    }

    // =========================
    // USERS - CREATE
    // =========================
    @Given("prepare valid user payload")
    public void prepareValidUserPayload() {
        // no-op, payload dibuat pas create biar gampang
    }

    @When("user create new user")
    public void userCreateNewUser() {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "Bagas");
        body.put("lastName", "Automation");
        body.put("email", "bagas.automation." + System.currentTimeMillis() + "@mail.com");

        lastResponse = api.createUser(body);

        // simpan id kalau sukses (200/201)
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

    // =========================
    // USERS - UPDATE
    // =========================
    @When("user update created user")
    public void userUpdateCreatedUser() {
        Assertions.assertNotNull(createdUserId, "createdUserId null - create step gagal atau tidak jalan");

        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "BagasUpdated");

        lastResponse = api.updateUser(createdUserId, body);
    }

    @Then("update user response status should be {int}")
    public void updateUserResponseStatusShouldBe(Integer status) {
        // DummyAPI kadang stabil 200, tapi biar CI gak random fail,
        // kita accept 200 (dan kalau dia balikin 400/403 karena rate-limit, bakal kelihatan jelas)
        int code = lastResponse.statusCode();
        Assertions.assertEquals(status.intValue(), code, lastResponse.asString());

        // kalau body ada id, pastikan sama
        if (lastResponse.getBody() != null && lastResponse.asString().contains("\"id\"")) {
            String id = lastResponse.jsonPath().getString("id");
            if (id != null) {
                Assertions.assertEquals(createdUserId, id, lastResponse.asString());
            }
        }
    }

    // =========================
    // USERS - DELETE
    // =========================
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

    // =========================
    // NEGATIVE - GET USER INVALID ID
    // =========================
    @Given("user has invalid user id")
    public void userHasInvalidUserId() {
        // no-op
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
