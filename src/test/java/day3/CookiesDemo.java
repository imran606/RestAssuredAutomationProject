package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import java.util.Set;

public class CookiesDemo {
	
	//@Test
	
	void capturCookies() {
		
		given()
		
		.when()
		.get("https://www.google.com")
		
		.then()
		// the following code compare the cookise key with their value (cookies value is keeps on changing for each and every time)
		.cookie("AEC","Ackid1S__sx7TKbZw3MkEQHHuWBh40cSNbM7v_6XzwxDNdcOD6b8BvFXzw")
		.log().all();
		
		/* 
		Set-Cookie: 1P_JAR=2023-10-30-14; expires=Wed, 29-Nov-2023 14:00:09 GMT; path=/; domain=.google.com; Secure
		Set-Cookie: AEC=Ackid1S__sx7TKbZw3MkEQHHuWBh40cSNbM7v_6XzwxDNdcOD6b8BvFXzw; expires=Sat, 27-Apr-2024 14:00:09 GMT; path=/; domain=.google.com; Secure; HttpOnly; SameSite=lax
		Set-Cookie: NID=511=OaL3Mv7H6xClUvgvBKDeUhT7H8MaLU4796ATEyiXULotDsOUTmLmPQKFQP9labGD25LRoHTcbUDhR9ZCxySM255tWTG09vzlORrO-Z_oheEvax2pjjKsBjndTOCqK2m2DNOhM5uAltKOqL5BCU-uwKQ2YJwx7yopgTcgfzDYG00; expires=Tue, 30-Apr-2024 14:00:09 GMT; path=/; domain=.google.com; HttpOnly
	    */	
		
	}

	//@Test
	void getCookiesValue() {
		
		
		Response res= given().when()
		.get("https://www.google.com"); //if want entire response then we should not specify then()
		
		// get single cookie info or value 
		String cookie_value=res.getCookie("AEC");
		System.out.println("the value of cookie is  "+ cookie_value);
		
	}
	
	@Test
	void getAllCookiesInfo() {
		
		Response res=given()
				.when().get("https://www.google.com");
		
		Map<String, String> all_cookies = res.getCookies();
		
		//keySet method is used to extract the all keys in a map
		//System.out.println(all_cookies.keySet());
		Set<String> keySet = all_cookies.keySet();
		//to print the values from the maps  
		
		for(String k:keySet) {
			
			String single_cookie = res.getCookie(k);
			
			System.out.println(k+"           "+single_cookie);
			
			                                                                
			
		}
		
		
		
	}

}
