package GoogleMapApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics {

	@Test
	public static void getPlaceAPI() {
		// TODO Auto-generated method stub

		// BaseURL or Host
		RestAssured.baseURI = "https://maps.googleapis.com";

		Response r = given().param("location", "-33.8670522,151.1957362").param("radius", "5600").log().all()
				.param("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").when().get("/maps/api/place/nearbysearch/json")
				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.body("results[1].scope", equalTo("GOOGLE")).log().all()
				.body("results[3].place_id", equalTo("ChIJkzN9YUeuEmsRXS5xib231aQ"))
				.header("server", "scaffolding on HTTPServer2").extract().response();
		JsonPath js = new JsonPath(r.asString());
		// System.out.println(r.asString());
		int leng = js.getInt("results.size()");
		System.out.println(leng);
		for (int i = 0; i < leng; i++) {
			String s = js.get("results[" + i + "].name");
			System.out.println(s);
		}

	}
}
