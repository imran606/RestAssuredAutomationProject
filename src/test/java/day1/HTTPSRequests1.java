package day1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPSRequests1 {
	
   /*
    * given()
    * this is the method used to write pre-requisists like content type, cookies,header,authentication types and etc..
    * and request body(playload)
    * 
    * when()
    * this method is used to write the HTTP Requests like Get,Post,Put,Patch and Delete
    * 
    * then()
    * this method includes all the validation parts like status code, response time and body,headers and cookies etc..
    * 
    * all methods are static methods to import these methods we have to explicitly mention the package name 
    * import static io.restassured.RestAssured.*;
      import static io.restassured.matcher.RestAssuredMatchers.*;
      import static org.hamcrest.Matchers.*;
    */
	
	int id;
	
	@Test(priority=0)
	void listOfUsers() {
		
	     given()
	    
	    .when()
	     .get("https://reqres.in/api/users?page=2")
	    
	    .then()
	    .statusCode(200)
	    .body("page", equalTo(2))
	    .log().all(); // this will print the response in the console 
	     
	}
	
	@Test(priority=1)
	void creatUser() {
		HashMap data=new HashMap();
		data.put("name", "Imran");
		data.put("job", "SDET");
		
		id=given()
		.contentType("application/json")
		.body(data)
		
		.when()   
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		//.then()
		//.statusCode(201)
		//.log().all();
		
	}
	
	@Test(priority=2)
	void updateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "maqsood");
		data.put("job", "networkanalyst");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200)
		.log().all();
		
		
	}
	
	@Test(priority=3)
	void deleteUser() {
		
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204);
	}
	
	
    
	
	

}
