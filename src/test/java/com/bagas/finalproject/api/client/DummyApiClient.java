package com.bagas.finalproject.api.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class DummyApiClient {

    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    // App-id yang dikasih mentor (WAJIB)
    private static final String APP_ID = "63a804408eb0cb069b57e43a";

    public DummyApiClient() {
        RestAssured.baseURI = BASE_URL;
    }

    private io.restassured.specification.RequestSpecification baseReq() {
        return RestAssured.given()
                .header("app-id", APP_ID)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    public Response getUsers(int limit) {
        return baseReq()
                .queryParam("limit", limit)
                .when()
                .get("/user");
    }

    public Response getUserById(String id) {
        return baseReq()
                .when()
                .get("/user/" + id);
    }

    public Response createUser(Map<String, Object> body) {
        return baseReq()
                .body(body)
                .when()
                .post("/user/create");
    }

    public Response updateUser(String id, Map<String, Object> body) {
        return baseReq()
                .body(body)
                .when()
                .put("/user/" + id);
    }

    public Response deleteUser(String id) {
        return baseReq()
                .when()
                .delete("/user/" + id);
    }

    public Response getTags() {
        return baseReq()
                .when()
                .get("/tag");
    }
}
