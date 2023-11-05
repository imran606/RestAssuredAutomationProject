package day8_API_Chaining;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class GetAUser {
	
	@Test
	void getUser(ITestContext contex) {
		
		int id=(Integer) contex.getSuite().getAttribute("user_id"); //we are getting this from create user
		String bearerToken="b84ec26e1377c683a265f752d46cc4c3124a8374edf69658e1e98e4f468b5208";
		
		given()
		.headers("Authorization","Bearer "+bearerToken)
		.pathParam("id", id)
		.when()
		.get("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
		.statusCode(200)
		.log().all();
		
	}

}
