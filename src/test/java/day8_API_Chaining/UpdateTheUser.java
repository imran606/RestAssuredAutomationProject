package day8_API_Chaining;

import org.testng.annotations.Test;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class UpdateTheUser {
	
	@Test
	void UpdateTheUser1(ITestContext contex) {
		
	int id=(Integer) contex.getSuite().getAttribute("user_id");
	
	Faker faker=new Faker();
	JSONObject data=new JSONObject();
	data.put("name", faker.name().fullName());
	data.put("gender", "Male");
	data.put("email", faker.internet().emailAddress());
	data.put("status", "active");
	
	String bearerToken="b84ec26e1377c683a265f752d46cc4c3124a8374edf69658e1e98e4f468b5208";
	given()
	.headers("Authorization","Bearer "+bearerToken)
	.contentType("application/json")
	.pathParam("id", id)
	.body(data.toString())
	
	.when()
	.put("https://gorest.co.in/public/v2/users/{id}")
	
	.then()
	.statusCode(200)
	.log().all();
	
	}
}
