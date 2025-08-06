package Tests;

import Utils.ApiClient;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CurrentWeatherTest {


	    // Test Criteria 1: API Key Authentication
	    @Test
	    public void testValidApiKey() {
	        given()
	            .spec(ApiClient.getRequestSpec())
	            .queryParam("q", "London") // Use city name for this endpoint
	        .when()
	            .get("/weather") // Use the /weather endpoint
	        .then()
	        .log().body()
	            .statusCode(200);
	    }

	    @Test
	    public void testInvalidApiKey() {
	        given()
	            .baseUri("https://api.openweathermap.org/data/2.5") // Need to set Base URI here since spec has valid key
	            .queryParam("appid", "invalid_key")
	            .queryParam("q", "London")
	        .when()
	            .get("/weather")
	        .then()
	        .log().body()
	            .statusCode(401)
	            // UPDATE the expected message to match the real one
	            .body("message", containsString("Invalid API key."));
	    }

	    // Test Criteria 4: Parameter Handling and Input Validation
	    @Test
	    public void testMissingParameters() {
	        given()
	            .spec(ApiClient.getRequestSpec())
	            // Missing the 'q' parameter
	        .when()
	            .get("/weather")
	        .then()
	        .log().body()
	            .statusCode(400) // Now this should work
	            .body("message", equalTo("Nothing to geocode"));
	    }

	    // Test Criteria 3: Response Structure and Data Validation
	    @Test
	    public void testResponseSchema() {
	        // NOTE: You will need to create a NEW schema file for the /weather endpoint response
	        // and name it 'weather-schema.json' in src/test/resources
	        given()
	            .spec(ApiClient.getRequestSpec())
	            .queryParam("q", "London")
	        .when()
	            .get("/weather")
	        .then()
	        .log().body()
	            .assertThat()
	            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("weather-schema.json"));
	    }

	   

    // Test Criteria 5: Query Parameter Combination
    @Test
    public void testParameterCombination() {
        given()
            .spec(ApiClient.getRequestSpec())
            .queryParam("q", "London") // Use city parameter 'q'
            .queryParam("units", "metric")
        .when()
            .get("/weather") // Use the /weather endpoint
        .then()
        .log().body()
            .statusCode(200)
            // The temp is inside the 'main' object for this endpoint
            .body("main.temp", lessThan(50.0f));
    }

    // Test Criteria 6: Performance and Response Time
    @Test
    public void testResponseTime() {
        given()
            .spec(ApiClient.getRequestSpec())
            .queryParam("q", "London") // Use city parameter 'q'
        .when()
            .get("/weather") // Use the /weather endpoint
        .then()
        .log().body()
            .statusCode(200) // Add a status code check
            .time(lessThan(2000L)); // Response time less than 2 seconds
    }

    // Test Criteria 7: Data Accuracy and Consistency
    @Test
    public void testDataAccuracy() {
        given()
            .spec(ApiClient.getRequestSpec())
            .queryParam("q", "London") // Use city parameter 'q'
        .when()
            .get("/weather") // Use the /weather endpoint
        .then()
        .log().body()
            .statusCode(200)
            // Update the JSON paths for this endpoint's response structure
            .body("main.temp", notNullValue())
            .body("main.humidity", notNullValue())
        .body("wind.speed", notNullValue());
    }
    }
    