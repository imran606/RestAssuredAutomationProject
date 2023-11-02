package day4ParsingResponseData;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {
	
	//Parsing:- means traversing through the response and selecting particular data
	
	//@Test        //approach one validating the data without using testNG assertions
	void validateTitleFromTheResponse() {
	    given().
	    when().
	    get("https://reqres.in/api/users?page=2").
	    then().
	    statusCode(200).
	    contentType("application/json; charset=utf-8").
	    body("data[0].email", equalTo("michael.lawson@reqres.in"));
	   // body("data[0].email", equalTo("afreen"));
	}
	
	//@Test  // validating the each data by using testNG Assertions
	void verifyDataByTestNg() {
		Response response = given()
		.when()
		.get("https://reqres.in/api/users?page=2");
		
		Assert.assertEquals(response.getStatusCode(),200);
		// parcing through json body from each element
		String id = response.jsonPath().get("data[0].id").toString();
		String email= response.jsonPath().get("data[0].email").toString();
		String fname= response.jsonPath().get("data[0].first_name").toString();
		String lname= response.jsonPath().get("data[0].last_name").toString();
		String avatar= response.jsonPath().get("data[0].avatar").toString();
		 Assert.assertEquals(id, "7");	
	     Assert.assertEquals(email, "michael.lawson@reqres.in");
	     Assert.assertEquals(fname, "Michael");
	     Assert.assertEquals(lname, "Lawson");
	     Assert.assertEquals(avatar, "https://reqres.in/img/faces/7-image.jpg");

		
        // the variable "authorObject" is stores the response in object form because this we cannot vallidate the response data using testNG methods thats why we are converting that to string format 
		
		// Check if the authorObject is not null before converting to string(because some time it shows null thats why we are adding the condition here)
		
		/*if (authorObject != null) {
		    String mailId = authorObject.toString();
		    Assert.assertEquals(mailId, "michael.lawson@reqres.in");
		} else {
		    Assert.fail("Author is null or not found in the response.");
		}*/
	
	}
    @Test
    void validatePerticularKeyfromAllResponseBody() {
    	
    	/*Response res = given()
    	.when()
    	.get("https://reqres.in/api/users?page=2");*/
    	
    	//another way to send the request
    	Response res = RestAssured.get("https://reqres.in/api/users?page=2");
    	
    	//you can use asString() to extract the content of the response as a String
    	JSONObject oj=new JSONObject(res.asString());//converting response to json object
    	
    	boolean status=false;
    	for(int i=0;i<oj.getJSONArray("data").length();i++) {
    		
    		String lastname = oj.getJSONArray("data").getJSONObject(i).get("last_name").toString();
    		//System.out.println(lastname);
    		
    		//if we want to fetch the specific lastname then we have to add some conditions
    		if(lastname.equals("Funke")) {
    			status=true;
    			break; //if the condition is true it will break (stops)the for loop
    		}
    		
    	}
    	Assert.assertEquals(status, true);  //it will pass our test case it is true
    }
		
		
		
	}


