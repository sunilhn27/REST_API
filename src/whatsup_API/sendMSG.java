package whatsup_API;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class sendMSG {

	@Test
	public void sendTORaju() {

		RestAssured.baseURI = "https://eu6.chat-api.com";
		
		given().header("token","h9lhuk941s8br02j").body("{\r\n" + 
				"    \"phone\": \"918147773448\",\r\n" + 
				"    \"body\": \"Hello, world! 🍏\"\r\n" + 
				"}").when()
				.post("/instance4650/message/").then().assertThat().statusCode(200);

	}
}
