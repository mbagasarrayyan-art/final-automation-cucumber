package com.bagas.finalproject.api.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class DummyApiClient {

    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    private static final String DEFAULT_APP_ID = "63a804408eb0cb069b57e43a";

    private final RequestSpecification req;

    public DummyApiClient() {
        String appId = System.getenv("APP_ID");
        if (appId == null || appId.isBlank()) {
            appId = DEFAULT_APP_ID;
        }

        this.req = RestAssured.given()
                .baseUri(BASE_URL)
                .header("app-id", appId)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    public Response getUserList() {
        return req.when().get("/user").then().extract().response();
    }

    public Response getTags() {
        return req.when().get("/tag").then().extract().response();
    }

    public Response getUserById(String id) {
        return req.when().get("/user/" + id).then().extract().response();
    }

    public Response createUser(Map<String, Object> body) {
        return req.body(body).when().post("/user/create").then().extract().response();
    }

    public Response updateUser(String id, Map<String, Object> body) {
        return req.body(body).when().put("/user/" + id).then().extract().response();
    }

    public Response deleteUser(String id) {
        return req.when().delete("/user/" + id).then().extract().response();
    }
}
