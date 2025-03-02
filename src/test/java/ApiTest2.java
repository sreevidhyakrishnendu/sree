import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest2 {
    @Test
    public void testPutEndpoint() {
        RestAssured.baseURI = ConfigManager.getProperty("baseURI");

        String token = TokenManager.getToken();
        System.out.println("Token for PUT request: " + token);

        // Initial title
        String title = "latestTask123";
        String description = "task Description123";

        // Create a JSON body for the PUT request
        String requestBody = "{\"title\": \"" + title + "\", \"description\": \""+ description +"\" }";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(ConfigManager.getProperty("createTaskEndpoint") ); // Replace with your PUT endpoint

        System.out.println("Response: " + response.asString());

        // Validate the response
        response.then()
                .statusCode(201)
                .body("title", equalTo(title))
                .body("description", equalTo(description));
    }




}
