import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredRequests {

    private static String requestBody = "{\n" +
            "  \"title\": \"rafael\",\n" +
            "  \"body\": \"suzano\",\n" +
            "  \"userId\": \"1\" \n}";


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getRequest_comments() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/comments")
                .then()
                .extract().response();
        System.out.print(response.prettyPrint());
        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    public void getRequest_posts() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts")
                .then()
                .extract().response();
        System.out.print(response.prettyPrint());
        Assertions.assertEquals(200, response.statusCode());

    }
    @Test
    public void postRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());
     //   Assertions.assertEquals("foo", response.jsonPath().getString("title"));
       // Assertions.assertEquals("bar", response.jsonPath().getString("body"));
        //Assertions.assertEquals("1", response.jsonPath().getString("userId"));
        //Assertions.assertEquals("101", response.jsonPath().getString("id"));
        System.out.print(response.prettyPrint());

    }

}