package com.qa.petstore.httpRequests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HTTPRequests {

	
	public static Response postRequest(String payload, String url) {
		Response response = RestAssured
				.given()
					.header("Content-Type", "application/json")
					.header("accept", "application/json")
					.contentType(ContentType.JSON)
					.body(payload)
					.post(url);
		return response;
	}

	public static Response getRequest(String username, String url) {
		Response response = RestAssured
				.given()
				.pathParam("username", username)
				.get(url);
		return response;
	}

	public static Response putRequest(String username, String payload, String url) {
		Response response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("accept", "application/json")
				.contentType(ContentType.JSON)
				.body(payload)
				.put(url);
		return response;
	}

	public static Response deleteRequest(String username, String url) {
		Response response = RestAssured
				.given()
				.pathParam("username", username)
				.delete(url);
		return response;
	}
}
