package com.bagas.finalproject.api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class DummyApiClient {

    private final String appId;

    public DummyApiClient(String baseUrl, String appId) {
        RestAssured.baseURI = baseUrl;
        this.appId = appId;
    }

    private io.restassured.specification.RequestSpecification req() {
        return given()
                .header("app-id", appId)
                .contentType("application/json");
    }

    public Response createUser(Map<String, Object> body) {
        return req().body(body).post("/user/create");
    }

    public Response getUserById(String id) {
        return req().get("/user/" + id);
    }

    public Response updateUser(String id, Map<String, Object> body) {
        return req().body(body).put("/user/" + id);
    }

    public Response deleteUser(String id) {
        return req().delete("/user/" + id);
    }

    public Response getTags() {
        return req().get("/tag");
    }
}
