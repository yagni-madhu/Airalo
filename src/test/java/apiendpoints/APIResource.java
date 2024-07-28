package apiendpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static apiendpoints.Properties.getUrl;
import static io.restassured.RestAssured.given;
public class APIResource {
    private static String accessToken;
    @BeforeClass
    public void authenticate() {
        Response response = given()
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", "974d515d41f86868eccd2d22aae8d10e")
                .formParam("client_secret", "tILYEqQRq5PnZ5nccQZ1IiVugUWhZN2UveJZ9rVa")
                .post(Base.generateToken_url);
        String responseString = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseString);
        accessToken = jsonPath.getString("data.access_token");

    }
    public Response postOrder() {
     // String post_url= getUrl().getString("post_url");// Retrieves data from properties class

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

    public Response getListOfEsims() {
        Response response = given()
                .accept("application/json")
                .header("Authorization", "Bearer " + accessToken)
                .queryParam("include", "order")
                .get(Base.get_url)
                .then().log().all().extract().response();
        return response;
    }

}
