package day8_API_Chaining;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class DeleteUser {
	
	@Test
	void deleteUser(ITestContext contex) {
		
		int id=(Integer) contex.getSuite().getAttribute("user_id"); //this should come from create user 
		String bearerToken="b84ec26e1377c683a265f752d46cc4c3124a8374edf69658e1e98e4f468b5208";
       
		given()
		.headers("Authorization","Bearer "+bearerToken)
		.pathParam("id", id)
		.when()
		.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
		.statusCode(204)
		.log().all();
		
	}

}
