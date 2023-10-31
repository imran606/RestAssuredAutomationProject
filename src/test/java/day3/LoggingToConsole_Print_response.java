package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingToConsole_Print_response {
	
	@Test
	void usesOfLogAndItsMethods() {
		
		given()
		.when()
		.get("https://www.google.com")
		.then()
		//.log().all()
		//.log().cookies()
		//.log().body()
		.log().headers();
		
	}

}
