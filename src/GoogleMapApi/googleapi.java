package GoogleMapApi;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class googleapi {

	@Test
	public static void rest() {
		RestAssured.baseURI = "https://maps.googleapis.com";

		given().param("location", "-33.8670522,151.1957362").param("radius", "500").param("type", "hospital")
				.param("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y");
		when().get("/maps/api/place/nearbysearch/json").then().assertThat().contentType(ContentType.JSON)
				.statusCode(200).and().body("results[0].name", equalTo("Pyrmont Doctors"));
		System.out.println("pass");

	}
}
