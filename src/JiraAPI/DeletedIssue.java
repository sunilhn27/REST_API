package JiraAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeletedIssue {

	@Test
	public void deletePartiularissue() {
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"sunil\", \"password\": \"Sunil@123\" }").when().post("/rest/auth/1/session")
				.then().assertThat().statusCode(200).extract().response();

		JsonPath js = new JsonPath(res.asString());
		System.out.println(res.asString());
		String sessionid = js.getString("session.value");

		given().header("Content-Type", "application/json").header("Cookie", "JSESSIONID=" + sessionid).when()
				.delete("/rest/api/2/issue/" + 10320).then().assertThat().statusCode(204);
		System.out.println("Succesfully Deleted issue");

	}
}
