package GoogleMapApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class POST {

	@Test
	public static void postrequest() {

		RestAssured.baseURI = "https://maps.googleapis.com";
		// create Place
		Response rs = given().
				queryParam("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y")
				.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -33.8669710,\r\n"
						+ "    \"lng\": 151.1958750\r\n" + "  },\r\n" + "  \"accuracy\": 50,\r\n"
						+ "  \"name\": \"Google Shoes!\",\r\n" + "  \"phone_number\": \"(02) 9374 4000\",\r\n"
						+ "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Iran\",\r\n"
						+ "  \"types\": [\"shoe_store\"],\r\n" + "  \"website\": \"http://www.google.com.au/\",\r\n"
						+ "  \"language\": \"en-AU\"\r\n" + "}")
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).body("status", equalTo("OK")).body("scope", equalTo("APP")).extract()
				.response();
		// Extract Place

		String r = rs.asString();
		System.out.println(r);
		JsonPath js = new JsonPath(r);
		String placeid = js.get("place_id");
		System.out.println(placeid);

		// delete place
		String bo = "{\r\n" + "  \"place_id\": \"" + placeid + "\"\r\n" + "}\r\n" + " ";

		given().queryParam("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").body(bo).when()
				.post("/maps/api/place/delete/json").then().assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("status", equalTo("OK"));
		System.out.println("");
	}
}
