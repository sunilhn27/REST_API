package JiraAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIssue {

	// obj -> json(Marshelling)
	@Test
	public void JiraCreateIssue() {

		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"sunil\", \"password\": \"Sunil@123\" }").when().post("/rest/auth/1/session")
				.then().assertThat().statusCode(200).extract().response();

		JsonPath js = new JsonPath(res.asString());
		System.out.println(res.asString());
		String sessionid = js.getString("session.value");
		System.out.println("Session Value is " + sessionid);

		System.out.println("success getting session id");
		// creating issue
		Response Resp = given().header("Content-Type", "application/json").header("Cookie", "JSESSIONID=" + sessionid)
				.body(CreateIssue.Create("Data not updating to database", "Need to fix ASAP", "Bug")).when()
				.post("/rest/api/2/issue").then().assertThat().statusCode(201).extract().response();
		// Getting the id
		JsonPath js1 = new JsonPath(Resp.asString());
		System.out.println(Resp.asString());
		String id = js1.get("id");
		System.out.println("issue id " + id);

	}

	// method to pass the body
	public static String Create(String summary, String description, String issuetype) {

		String b = "{\r\n" + "	\"fields\": {\r\n" + "        \"project\":\r\n" + "        {\r\n"
				+ "            \"key\": \"RES\"\r\n" + "        },\r\n" + "        	\r\n" + "        \"summary\": \""
				+ summary + "\",\r\n" + "        \"description\": \"" + description + "\" ,\r\n"
				+ "        \"issuetype\": {\r\n" + "         \"name\": \"" + issuetype + "\",\r\n"
				+ "         \"assignee\": {\r\n" + "            \"name\": \"Sunil H N\"\r\n" + "        }\r\n" + "}\r\n"
				+ "}\r\n" + "}";
		return b;
	}
}	