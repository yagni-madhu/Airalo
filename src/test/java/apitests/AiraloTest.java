package apitests;
import apiendpoints.APIResource;
import apiendpoints.Base;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static apiendpoints.StaticResources.*;
import static io.restassured.RestAssured.given;

public class AiraloTest {
    private String accessToken;
    private String orderId;
    @BeforeClass
    public void authenticate() {
        Response response = given()
                .formParam("grant_type", "client_credentials")
                .formParam(clientId, client_id_Value)
                .formParam(clientSecret, client_secret_Value)
                .post(Base.generateToken_url);
        String responseString = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseString);
        accessToken = jsonPath.getString("data.access_token");

    }

    public String getAccessToken() {

        return this.accessToken;
    }

    public String getOrderId() {
        return this.orderId;
    }
    APIResource resource =new APIResource();
    @Test(priority = 1)
    public void testPostOrder(){
        Response res= resource.postOrder(getAccessToken());

        String responseString = res.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseString);
        this.orderId = jsonPath.getString("data.id");

        Assert.assertEquals(jsonPath.get("data.currency"), "USD");
        Assert.assertEquals(jsonPath.getString("data.package_id"), "merhaba-7days-1gb");
        Assert.assertEquals(jsonPath.getInt("data.quantity"), 6);
        Assert.assertEquals(jsonPath.getString("data.type"), "sim");
        Assert.assertEquals(jsonPath.getString("data.description"), "creating order");
        Assert.assertEquals(jsonPath.getString("data.esim_type"), "Prepaid");
        Assert.assertEquals(jsonPath.getInt("data.validity"), 7);
        Assert.assertEquals(jsonPath.getString("data.package"), "Merhaba-1 GB - 7 Days");
        Assert.assertEquals(jsonPath.getString("data.data"), "1 GB");
        Assert.assertEquals(jsonPath.getFloat("data.price"), 4.5);


        boolean status = false;
        JSONObject jo = new JSONObject(res.asString());

        int numberOfSims = jo.getJSONObject("data").getJSONArray("sims").length();
        Assert.assertEquals(numberOfSims, 6);

        for (int i = 0; i < numberOfSims; i++) {
            String sim_Id = jo.getJSONObject("data").getJSONArray("sims").getJSONObject(i).get(simId).toString();
            String sim_Iccid = jo.getJSONObject("data").getJSONArray("sims").getJSONObject(i).get(simIccid).toString();
            String sim_ApnType = jo.getJSONObject("data").getJSONArray("sims").getJSONObject(i).get(simApnType).toString();

            Assert.assertNotNull(sim_Id);
            Assert.assertNotNull(sim_Iccid);
            Assert.assertNotNull(sim_ApnType);

        }
    }
    @Test(priority = 2)
    public void testGetEsims(){
        int simQuantity = 0;
        int expectedSimQuantity = 6;
        Response res= resource.getListOfEsims(getAccessToken());

        JSONObject jo = new JSONObject(res.asString());
        for (int i = 0; i < jo.getJSONArray("data").length(); i++) {
            String orderId = jo.getJSONArray("data").getJSONObject(i).getJSONObject("simable").get(simId).toString();
            String packageId = jo.getJSONArray("data").getJSONObject(i).getJSONObject("simable").get(package_id).toString();
            if(orderId.equals(getOrderId()) && packageId.equals("merhaba-7days-1gb")) {
                simQuantity = simQuantity + 1;
            }
        }

        Assert.assertEquals(res.statusCode(),200);
        Assert.assertEquals(simQuantity,expectedSimQuantity);

    }
}
