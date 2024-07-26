package api_endpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class API_Resource {
    private static String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI1MDkiLCJqdGkiOiI3ZjQ3MmYzNDViZWRmMGQwM2JkODAzNzgyYTczZTRkN2JkMTI1OTA4Yzk1NDY0NWJkM2VjOTVlNmFlYWU0YzJmZGVkM2VkMzY3NDIzMWM5OSIsImlhdCI6MTcyMTkwMzY4NSwibmJmIjoxNzIxOTAzNjg1LCJleHAiOjE3NTM0Mzk2ODUsInN1YiI6IiIsInNjb3BlcyI6W119.LPFAhgZtfclDUktla6pVktL6y7fR-EWHRESUkzt8z3xm2hAhIemzbHf1VuwmNdfS3Bqh5Yr_wuTVwnkt_qvUsLsB2eKOCVfMnN1PNyQEt9At2w5DXUYK74M51X92kfucOnjY85Qz9VCq8klnM9TGeGoazmLu3oDSVsQ3pCHTqcP4KWx31JeZcO2KHdt-cAJathohlglQubTKE3yh-54ct_MulFbq1fN038VvaUcCqJJ1YxeZKwE3jHJimB2v1owhmDyc9AXkvLxQfzLpm2LokH-1a6_ZNquoRntoaZTNA9QoJ8tuT3pWT25Onny2VhIdphxa7q1rt6GB1hMhBR4MowBoJhkW8iaBbbnTFKq6FY8u4enkj88sHcm6z-Nw_dFRrunrjpuX68Cr7_v2_TifrauHD3Hw9uyHIilSkEurPPb37pAsFD2NKnmo8WKAvfIzy7Ih-0ZRR9UaXDv4jkhSiEqVsP7xLpspEvRAduV3I-nycGGKNlecL1dHbMOYaWIj4ngcBymSVLZwmYlXnmmm9W3cACllPSk4wKS2v4spHJ4jUvarO_gX7iHxzy6SHn70P4I7akndIam6vGoGYuh2VZk6B_dciKnnWd7dKr2GkWZcVzfGk8eStC16KYBUVXCLqGomCpWdJk2j0kdBLdKrHP7eK_Fmdlm4TKsZKB0-2IY";

    public void authenticate() {
        Response response = given()
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", "974d515d41f86868eccd2d22aae8d10e")
                .formParam("client_secret", "tILYEqQRq5PnZ5nccQZ1IiVugUWhZN2UveJZ9rVa")
                .post(Routes.generateToken_url);
        }
    public Response postOrder() {
        Response response = given()
                .accept("application/json")
                .header("Authorization", "Bearer " + accessToken)
                .formParam("quantity", "6")
                .formParam("package_id", "merhaba-7days-1gb")
                .formParam("type", "sim")
                .formParam("description", "creating order")
                .post(Routes.post_url);
        return response;
    }

    public Response getListOfEsims() {
        Response response = given()
                .accept("application/json")
                .header("Authorization", "Bearer " + accessToken)
                .queryParam("include", "order")
                .get(Routes.get_url)
                .then().log().all().extract().response();
        return response;
    }

}
