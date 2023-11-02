package day4ParsingResponseData;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {
	
	//Parsing:- means traversing through the response and selecting particular data
	
	@Test        //approach one validating the data without using testNG assertions
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
	
	@Test  // validating the each data by using testNG Assertions
	void verifyDataByTestNg() {
		Response response = given()
		.when()
		.get("https://reqres.in/api/users?page=2");
		
		Assert.assertEquals(response.getStatusCode(),200);
		// parcing through json body from each element
		Object authorObject = response.jsonPath().get("data[0].email");
        // the variable "authorObject" is stores the response in object form because this we cannot vallidate the response data using testNG methods thats why we are converting that to string format 
		
		// Check if the authorObject is not null before converting to string(because some time it shows null thats why we are adding the condition here)
		
		if (authorObject != null) {
		    String mailId = authorObject.toString();
		    Assert.assertEquals(mailId, "michael.lawson@reqres.in");
		} else {
		    Assert.fail("Author is null or not found in the response.");
		}


	}

		
		
		
		
	}


