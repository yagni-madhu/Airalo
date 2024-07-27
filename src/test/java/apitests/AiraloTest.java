package apitests;
import apiendpoints.APIResource;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
public class AiraloTest {
    APIResource resource =new APIResource();
    @Test(priority = 1)
    public void testPostOrder(){
        Response res= resource.postOrder();

        String responseString = res.getBody().asString();
        System.out.println("Order Response: " + responseString);
        JsonPath jsonPath = new JsonPath(responseString);

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

        for (int i = 0; i < jo.getJSONObject("data").getJSONArray("sims").length(); i++) {
            String simIds = jo.getJSONObject("data").getJSONArray("sims").getJSONObject(i).get("id").toString();
            String simiccid = jo.getJSONObject("data").getJSONArray("sims").getJSONObject(i).get("iccid").toString();
            String simapn_type = jo.getJSONObject("data").getJSONArray("sims").getJSONObject(i).get("apn_type").toString();
            String simis_roaming = jo.getJSONObject("data").getJSONArray("sims").getJSONObject(i).get("is_roaming").toString();

            if (simIds.equals("135716") || simiccid.equals("893000000000067131") || simapn_type.equals("manual")
                    || simis_roaming.equals("true")) {
                status = true;
            }
            Assert.assertEquals(status, true);

        }
    }
    @Test(priority = 2)
    public void testGetEsims(){
        Response res= resource.getListOfEsims();

        Assert.assertEquals(res.statusCode(),200);
        Assert.assertEquals(res.contentType(),"application/json");
        res.then().body("data[6].simable.quantity",equalTo(6))
                .body("data[6].simable.package_id", equalTo("merhaba-7days-1gb"));

    }
}
