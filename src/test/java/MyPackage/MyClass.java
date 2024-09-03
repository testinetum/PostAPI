package MyPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MyClass {
    
    private String requestBody;
    private Response response;
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Given("the pet data is read from {string}")
    public void the_pet_data_is_read_from(String filePath) throws Exception {
        requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/" + filePath)));
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String endpoint) {
        RequestSpecification request = RestAssured.given()
            .baseUri(BASE_URL)
            .contentType("application/json")
            .body(requestBody);

        response = request.post(endpoint);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertThat(response.statusCode(), equalTo(statusCode));
    }

    @Then("the response body should contain {string} with value {int}")
    public void the_response_body_should_contain_with_value(String key, Integer value) {
        assertThat(response.jsonPath().getInt(key), equalTo(value));
    }
}

