package Twitter_API;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetTwt {

	String coustmerkey = "JkoqClF3I3UNG4z0DWZyaAKUQ";
	String coustmerSecret = "Hi3AA7GXv8ZuRRXX9ZiP63LMdIucttcmasp82kvZiw8lH9e7pH";
	String token = "2008579383215337472-CBhmuzVP3f0tlUdAOlQpmML3L6PUNA";
	String tokenSecret = "O5z4bxHSYCanAaoUYvKZb8IjZAYY4wm4KMUhnKx7UjP6J";


	@Test
	public void gettweet() {

		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses/";
		Response r = given().queryParam("count", "1").auth().oauth(coustmerkey, coustmerSecret, token, tokenSecret)
				.when().get("user_timeline.json").then().assertThat().statusCode(200).extract().response();

		String res = r.asString();
		System.out.println(r.asString());
		JsonPath comment = new JsonPath(res);
		String s = comment.getString("text");
		System.out.println(s);

		//I-J
		//G-H	
		//1-2	
		//N-O
	
	}
	
	}
