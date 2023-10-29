package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

/*
 * Different ways to create Post Request Body 
 * 1) Post Request Body Using HashMap
 * 2) Post Request Body Creation Using orj.json
 * 3) Post Request Body Creation Using POJO Class
 * 4) Post Request Using External JSON File Data
 */

public class DifferentWaysToCreatePostRequestBody {
	
	// creating the post request by using hashmap(Maps Concept)
	//@Test(priority=1)
	void postRequestUsingHashMAp() {
		
		HashMap data=new HashMap();
		data.put("name","Imrankolkar");
		data.put("location","koppal");
		data.put("phone","124578963");
		
		//if we have more than one value for one key then we have to store the values like this 
		String courcesarr[]= {"selenium22","java++"};
		data.put("courses", courcesarr);
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name",equalTo("Imrankolkar"))
		
		.body("location",equalTo("koppal"))
		.body("phone",equalTo("124578963"))
		.body("courses[0]",equalTo("selenium22"))
		.body("courses[1]",equalTo("java++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
	}
	
	// creating the post request by using orj.json
	//@Test(priority=1)
	void postRequestUsingOrgJson() {
		
		JSONObject data=new JSONObject();
		data.put("name","Imrankolkar");
		data.put("location","koppal");
		data.put("phone","124578963");
		
		//if we have more than one value for one key then we have to store the values like this 
		String courcesarr[]= {"selenium22","java++"};
		data.put("courses", courcesarr);
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name",equalTo("Imrankolkar"))
		
		.body("location",equalTo("koppal"))
		.body("phone",equalTo("124578963"))
		.body("courses[0]",equalTo("selenium22"))
		.body("courses[1]",equalTo("java++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
	}
	
	// creating the post request by using POJO class
	//@Test(priority=1)
	void postRequestUsingPojo() {
		
        Pojo_postRequest data=new Pojo_postRequest();
        data.setName("Imrankolkar");
		data.setLocation("koppal");
		data.setPhone("124578963");
		//if we have more than one value for one key then we have to store the values like this 
		String courcesarr[]= {"selenium22","java++"};
		data.setCourses(courcesarr);
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name",equalTo("Imrankolkar"))
		
		.body("location",equalTo("koppal"))
		.body("phone",equalTo("124578963"))
		.body("courses[0]",equalTo("selenium22"))
		.body("courses[1]",equalTo("java++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
	}
	
	// creating the post request using external json file which is created inside the project with .json format
	   @Test(priority=1)
		void postRequestUsingExeternalFile() throws FileNotFoundException {
			
			
			//to get the location of the file we have to use we have to import from java.io
			File f=new File(".//body.json");  //(dot\\(.\\) represent the current project location)
			// to read the data from the file we have to use we have to import this from java.io package 
			FileReader fr=new FileReader(f);
			// by using the JSONTokener we can extract the data in the json format we have to import it fron org.json package 
			JSONTokener jt=new JSONTokener(fr);
			JSONObject data=new JSONObject(jt);
			
			
			//if we have more than one value for one key then we have to store the values like this 
			String courcesarr[]= {"selenium22","java++"};
			data.put("courses", courcesarr);
			
			given()
			.contentType("application/json")
			.body(data.toString())
			
			.when()
			.post("http://localhost:3000/students")
			
			.then()
			.statusCode(201)
			.body("name",equalTo("Imrankolkar"))
			
			.body("location",equalTo("koppal"))
			.body("phone",equalTo("124578963"))
			.body("courses[0]",equalTo("selenium22"))
			.body("courses[1]",equalTo("java++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
			
		}
		
	
	
	@Test(priority=2)
	//deleting student record
	void testDelete() {
		
		when()
		.delete("http://localhost:3000/students/4")
		
		.then()
		.statusCode(200);
	}

}
