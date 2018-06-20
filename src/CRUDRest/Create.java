package CRUDRest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Create {

	Properties p = new Properties();

	@BeforeClass
	public void getdata() throws IOException {
		FileInputStream fs = new FileInputStream(
				"I:\\REST API Testing\\WORKSPACE\\Rest_GoogleApi\\src\\files\\env.properties");
		p.load(fs);
		System.out.println(p.getProperty("HOST"));
		RestAssured.baseURI = p.getProperty("HOST");
	}

	@Test
	public void create() {

		RestAssured.baseURI = "https://maps.googleapis.com";

		Response res = given().queryParam("key", p.getProperty("key")).body(Payload.getpostbody()).when()
				.post("/maps/api/place/add/json").then().assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();
		System.out.println("Succesfully Created place");
		System.out.println(res.asString());
		JsonPath js = new JsonPath(res.asString());
		String place = js.get("place_id");
		System.out.println("Succesfully extracted the json");
		// delete

		String b2 = "{\r\n" + "  \"place_id\": \"" + place + "\"\r\n" + "}\r\n" + "";

		given().queryParam("key", p.getProperty("key")).body(b2).when().post("/maps/api/place/delete/json").then()
				.assertThat().statusCode(200).contentType(ContentType.JSON).body("status", equalTo("OK"));
		System.out.println("succesfull Deleted the place");
	}
}
