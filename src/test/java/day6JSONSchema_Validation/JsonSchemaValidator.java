package day6JSONSchema_Validation;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class JsonSchemaValidator {
    
    @Test
    void schemaValidation() {
        given()
        .when()
        .get("http://localhost:3000/book")
        .then()
        .assertThat().body(matchesJsonSchema("./src/test/resources/booker.json")); // Assuming 'booker.json' is in the classpath
    }
}
