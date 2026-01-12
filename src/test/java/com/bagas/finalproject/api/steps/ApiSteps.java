package com.bagas.finalproject.api.steps;

import com.bagas.finalproject.api.client.DummyApiClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiSteps {

    private final DummyApiClient client = new DummyApiClient();

    private Response lastResponse;
    private String createdUserId;

    @When("I request user list with limit {int}")
    public void i_request_user_list_with_limit(Integer limit) {
        lastResponse = client.getUsers(limit);
    }

    @When("I request tags list")
    public void i_request_tags_list() {
        lastResponse = client.getTags();
    }

    @When("I create a new user")
    public void i_create_a_new_user() {
        String uniqueEmail = "bagas." + System.currentTimeMillis() + "@mail.com";

        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "Bagas");
        body.put("lastName", "Automation");
        body.put("email", uniqueEmail);

        lastResponse = client.createUser(body);

        // simpan id kalau berhasil
        if (lastResponse.statusCode() == 200 || lastResponse.statusCode() == 201) {
            createdUserId = lastResponse.jsonPath().getString("id");
        }
    }

    @When("I update the created user")
    public void i_update_the_created_user() {
        Assertions.assertThat(createdUserId)
                .as("createdUserId harus ada (pastikan create user sukses)")
                .isNotBlank();

        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "BagasUpdated");
        body.put("lastName", "AutomationUpdated");

        lastResponse = client.updateUser(createdUserId, body);
    }

    @When("I delete the created user")
    public void i_delete_the_created_user() {
        Assertions.assertThat(createdUserId)
                .as("createdUserId harus ada (pastikan create user sukses)")
                .isNotBlank();

        lastResponse = client.deleteUser(createdUserId);
    }

    @When("I request user by invalid id {string}")
    public void i_request_user_by_invalid_id(String invalidId) {
        lastResponse = client.getUserById(invalidId);
    }

    @Then("the response status should be {int} or {int}")
    public void the_response_status_should_be_or(Integer a, Integer b) {
        int actual = lastResponse.statusCode();
        Assertions.assertThat(actual)
                .as("status code")
                .isIn(a, b);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer code) {
        Assertions.assertThat(lastResponse.statusCode()).isEqualTo(code);
    }

    @Then("the user list should not be empty")
    public void the_user_list_should_not_be_empty() {
        List<?> data = lastResponse.jsonPath().getList("data");
        Assertions.assertThat(data).isNotNull();
        Assertions.assertThat(data.size()).isGreaterThan(0);
    }

    @Then("the tags list should not be empty")
    public void the_tags_list_should_not_be_empty() {
        List<?> data = lastResponse.jsonPath().getList("data");
        Assertions.assertThat(data).isNotNull();
        Assertions.assertThat(data.size()).isGreaterThan(0);
    }

    @Then("print the last response")
    public void print_the_last_response() {
        System.out.println("STATUS: " + lastResponse.statusCode());
        System.out.println(lastResponse.asPrettyString());
    }
}
