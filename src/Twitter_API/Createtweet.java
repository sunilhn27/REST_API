package Twitter_API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Createtweet {

	String coustmerkey = "JkoqClF3I3UNG4z0DWZyaAKUQ";
	String coustmerSecret = "Hi3AA7GXv8ZuRRXX9ZiP63LMdIucttcmasp82kvZiw8lH9e7pH";
	String token = "2008579383215337472-CBhmuzVP3f0tlUdAOlQpmML3L6PUNA";
	String tokenSecret = "O5z4bxHSYCanAaoUYvKZb8IjZAYY4wm4KMUhnKx7UjP6J";


	@Test
	public void createtweet() {

		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses/";
		Response r = given().queryParam("status", "This is posted using Eclipse	").auth()
				.oauth(coustmerkey, coustmerSecret, token, tokenSecret).when().post("/update.json").then().assertThat()
				.statusCode(200).extract().response();

		String res = r.asString();
		System.out.println(r.asString());
		JsonPath comment = new JsonPath(res);
		String id = comment.getString("id");
		String text = comment.getString("text");
		System.out.println(text);
		System.out.println(id);

		

		//I-J
		//G-H	
		//1-2	
		//N-O
	}
}
