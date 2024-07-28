package apiendpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static apiendpoints.Properties.getUrl;
import static io.restassured.RestAssured.given;
public class APIResource {

    public Response postOrder(String accessToken) {

        Response response = given()
                .accept("application/json")
                .header("Authorization", "Bearer " + accessToken)
                .formParam("quantity", "6")
                .formParam("package_id", "merhaba-7days-1gb")
                .formParam("type", "sim")
                .formParam("description", "creating order")
                .post(Base.post_url);
        return response;
    }

    public Response getListOfEsims(String accessToken) {
        Response response = given()
                .accept("application/json")
                .header("Authorization", "Bearer " + accessToken)
                .queryParam("include", "order")
                .get(Base.get_url)
                .then().log().all().extract().response();
        return response;
    }

}
