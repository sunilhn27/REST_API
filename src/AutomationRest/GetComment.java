package AutomationRest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetComment {

	@Test
	public void getcomment() {

		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"sunil\", \"password\": \"Sunil@123\" }").when().get("/rest/api/2/issue/10400/comment/10100")
				.then().assertThat().statusCode(200).extract().response();
		
		JsonPath js = new JsonPath(res.asString());
		System.out.println(res.asString());
		
		

		
		
		
	}
}
