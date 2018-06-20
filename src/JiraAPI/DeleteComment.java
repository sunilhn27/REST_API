package JiraAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteComment {

	@Test
	public void deleteComment() {
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"sunil\", \"password\": \"Sunil@123\" }").when().post("/rest/auth/1/session")
				.then().assertThat().statusCode(200).extract().response();

		JsonPath js1 = new JsonPath(res.asString());
		System.out.println(res.asString());
		String id = js1.get("session.value");
		System.out.println("issue id " + id);

		Response r = given().header("Content-Type", "application/json").header("Cookie", "JSESSIONID=" + id).when()
				.delete("/rest/api/2/issue/10400/comment/10101").then().assertThat().statusCode(204).extract().response();

	}
}
