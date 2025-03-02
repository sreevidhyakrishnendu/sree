import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class ApiTest {

    @Test
    public void testGetEndpoint1() {
        RestAssured.baseURI = ConfigManager.getProperty("baseURI");

        String token = TokenManager.getToken();
        System.out.println("Token from endpoint 1: " + token);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(ConfigManager.getProperty("tasksEndpoint") );

        System.out.println("Response: " + response.asString());

        // Extract a specific value from the response
        String specificValue = response.jsonPath().getString("title");
        System.out.println("Specific Value: " + specificValue);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("hiikjhukgjkg"))
                .body("description", equalTo(""))
                .body("done", equalTo(true))
                .body("done_at", equalTo("2025-02-20T20:37:34Z"))
                .body("due_date", equalTo("0001-01-01T00:00:00Z"))
                .body("reminders", nullValue())
                .body("project_id", equalTo(1))
                .body("repeat_after", equalTo(0))
                .body("repeat_mode", equalTo(0))
                .body("priority", equalTo(0))
                .body("start_date", equalTo("0001-01-01T00:00:00Z"))
                .body("end_date", equalTo("0001-01-01T00:00:00Z"))
                .body("assignees", nullValue())
                .body("labels", nullValue())
                .body("hex_color", equalTo(""))
                .body("percent_done", equalTo(0))
                .body("identifier", equalTo("#1"))
                .body("index", equalTo(1))
                .body("related_tasks", equalTo(new HashMap<>()))
                .body("attachments", nullValue())
                .body("cover_image_attachment_id", equalTo(0))
                .body("is_favorite", equalTo(false))
                .body("created", equalTo("2025-02-20T20:37:31Z"))
                .body("updated", equalTo("2025-02-20T20:37:34Z"))
                .body("bucket_id", equalTo(0))
                .body("position", equalTo(0))
                .body("reactions", nullValue())
                .body("created_by.id", equalTo(1))
                .body("created_by.name", equalTo(""))
                .body("created_by.username", equalTo("sree"))
                .body("created_by.created", equalTo("2025-02-20T20:36:48Z"))
                .body("created_by.updated", equalTo("2025-02-20T20:36:48Z"));
    }

}
