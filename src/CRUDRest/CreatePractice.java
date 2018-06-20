package CRUDRest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreatePractice {

	@Test
	public void create() {

		RestAssured.baseURI = "https://maps.googleapis.com";
		Response r = given().queryParam("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y")
				.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -33.8669710,\r\n"
						+ "    \"lng\": 151.1958750\r\n" + "  },\r\n" + "  \"accuracy\": 50,\r\n"
						+ "  \"name\": \"Google Shoes!\",\r\n" + "  \"phone_number\": \"(02) 9374 4000\",\r\n"
						+ "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Iran\",\r\n"
						+ "  \"types\": [\"shoe_store\"],\r\n" + "  \"website\": \"http://www.google.com.au/\",\r\n"
						+ "  \"language\": \"en-AU\"\r\n" + "}")
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).extract().response();

		JsonPath js = new JsonPath(r.asString());
		System.out.println(r.asString());
		String pla = js.get("place_id");

		// delete

		String b2 = "{\r\n" + "  \"place_id\": \"" + pla + "\"\r\n" + "}\r\n" + "";

		given().queryParam("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").body(b2)
				.post("/maps/api/place/delete/json").then().assertThat().and().statusCode(200);
	}

}
