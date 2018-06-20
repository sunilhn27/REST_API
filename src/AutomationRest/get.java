package AutomationRest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class get {

	@Test
	public void getrequest() {

		RestAssured.baseURI = "https://reqres.in/";

		Response r = given().when().post("/api/users/2").then().assertThat().statusCode(201).extract().response();

		System.out.println(r.asString());

	}

}
