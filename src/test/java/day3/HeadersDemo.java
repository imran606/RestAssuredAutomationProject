package day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class HeadersDemo {
	
	//the following method is to verify the headers 
	//@Test
	void verifyTheHeaders() {
		
		given()
		
		.when()
		.get("https://www.google.com")
		
		.then()
		.header("Content-Type","text/html; charset=ISO-8859-1")
		.and() //this method is not mandatory but its our choice 
		.header("Content-Encoding","gzip")
		.and()
		.header("server","gws");
		
	}
	
	//@Test
	void captureTheHeaders() {
		
		//to capture single header
		
		Response response = given()
		                     .when()
		                       .get("https://www.google.com");
		String header1 = response.getHeader("server");
		System.out.println(header1);
		
	}
	//it will captur all the headers but it is not usefull
	//@Test
	void captureTheallHeaders() {
		
		Response response = given()
                .when()
                  .get("https://www.google.com");
		
		Headers all_headers = response.getHeaders();
		
		for(Header h:all_headers) {
			System.out.println(h.getName()+"   "+h.getValue());
		}
		
			}
	@Test
	void captureAllHeaders1() {
		given()
		.when()
		.get("https://google.com")
		.then()
		.log().headers();
		
		
	}

	

}
