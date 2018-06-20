package GoogleMapApi;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.annotations.*;

import io.restassured.RestAssured;

public class RestApi {

	@Test
	public void Test() {

		RestAssured.baseURI = "https://maps.googleapis.com";

		given().param("location", "-33.8670522,151.1957362").param("radius", "500").param("key",
				"AIzaSyBRaA-z-2cc7hBbf6VIkkXcEynyjCB5Fno");
		when().get("/maps/api/place/nearbysearch/json").then().assertThat()
		.body("results[0].geometry.location.lng",equalTo("Sunil"));
		System.out.println("pass");

	}
}