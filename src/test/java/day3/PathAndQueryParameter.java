package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameter {
	
	@Test
	void testpathAndQueryParameter() {
		
		//https://reqres.in/api/users?page=2&id=5 (hard code url)
		
		given()
		.pathParam("mypath","api")
		.pathParam("mypath1","users") //its just like a variable that we can refer in the request 
		.queryParam("page", "2") //query parameter
		.queryParam("id","5")   // query parameter
		
		.when()
		.get("https://reqres.in/{mypath}/{mypath1}")
		
		.then()
		.statusCode(200)
		.log().all();
		
	}

}
