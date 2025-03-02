import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager {
    private static String token;

    public static String getToken() {
        if (token == null) {
            generateToken();
        }
        return token;
    }

    private static void generateToken() {
        RestAssured.baseURI = ConfigManager.getProperty("baseURI");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{ \"long_token\": true, \"password\": \"T3st12345\", \"totp_passcode\": \"string\", \"username\": \"sree\" }")
                .post(ConfigManager.getProperty("loginEndpoint"));

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to get token: " + response.statusLine());
        }

        token = response.jsonPath().getString("token");
        System.out.println("Generated Token: " + token);
    }
}
