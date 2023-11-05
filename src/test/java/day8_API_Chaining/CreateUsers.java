package day8_API_Chaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class CreateUsers {
	
	@Test
	void createUser(ITestContext contex) {
		
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		 String bearerToken="b84ec26e1377c683a265f752d46cc4c3124a8374edf69658e1e98e4f468b5208";
		
		 int id=given()
		.headers("Authorization","Bearer "+bearerToken)
		.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("https://gorest.co.in/public/v2/users")
		.jsonPath().getInt("id");
		 
		 //contex.setAttribute("user_id", id); //after getting the response we are creating the global variable 
		                                     // it will applicable for only test level not for suite lvel
		 
		 //we can set the variable as suite level to access any where
		 contex.getSuite().setAttribute("user_id",id );
		 System.out.println(id);
		
	}

}
