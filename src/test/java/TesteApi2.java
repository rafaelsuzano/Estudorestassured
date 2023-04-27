import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;


import java.util.List;

import static io.restassured.RestAssured.given;

public class TesteApi2 {



    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }



    @Test
    public void makeSureThatGoogleIsUp2() {

        String baseUrl = "https://jsonplaceholder.typicode.com/todos/";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get(baseUrl);
        String jsonString = response.asString();

//        System.out.printf(jsonString);

        Response response1 = doGetRequest(baseUrl);

        List<String> jsonResponse = response1.jsonPath().getList("$");

        System.out.println(jsonResponse.size());

        String userId = response.jsonPath().getString("id");
        System.out.println(userId);
        String id2 = response.jsonPath().getString("userId[38]");
        System.out.println(id2);
        String title = response1.jsonPath().getString("title");
        System.out.println(title);
        Assert.assertEquals(response.getStatusCode(), 200);


        List<String> jsonResponse2 = response1.jsonPath().getList("title");
        System.out.println(jsonResponse2.get(0));

        // https://devqa.io/parse-json-response-rest-assured/

    }


}