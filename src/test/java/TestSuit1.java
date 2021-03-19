
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestSuit1 {

    @Test(priority = 0, groups = "sanity", alwaysRun = true)
    void setupRequest(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2/store/order";
        //RestAssured.useRelaxedHTTPSValidation(); //SSL
        //RestAssured.proxy("localhost");
        //RestAssured.proxy(8888);
    }

    @Test(priority = 1, groups = "sanity", enabled = true)
    @Parameters({"orden1"})
    void RequestPost(String orden1){
        // Json Object
        JSONObject requestParams = new JSONObject();
            requestParams.put("id", Integer.parseInt(orden1.substring(1)));
            requestParams.put("petId", Integer.parseInt(orden1.substring(1)));
            requestParams.put("quantity", "3");
            requestParams.put("shipDate", "2021-03-16");
            requestParams.put("status",  "placed");
            requestParams.put("complete",  false);

        given().header("Content-Type", "application/json")
                .body(requestParams.toString())
                .post()
                .then().statusCode(200);

    }

    @Test(priority = 2, groups = "sanity", enabled = true)
    @Parameters({"orden1"})
    void RequestGetSuccess(String orden1){
        if(!orden1.isEmpty()){
            given().get(orden1)
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(Integer.parseInt(orden1.substring(1))))
                    .body("petId", equalTo(Integer.parseInt(orden1.substring(1))))
                    .body("complete", notNullValue());
        }
    }

    @Test(priority = 3, groups = "sanity")
    @Parameters({"orden2"})
    void RequestGetFail(String orden2){
        given().get(orden2)
                .then()
                .statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", notNullValue());
    }

    @Test(priority = 4, groups = "sanity")
    @Parameters({"orden1"})
    void RequestDeleteSuccess(String orden1){
        given().delete(orden1)
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(orden1.substring(1)));
    }

    @Test(priority = 5, groups = "sanity")
    @Parameters({"orden2"})
    void RequestDeleteFail(String orden2){
        given().delete(orden2)
                .then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("Order Not Found"));
    }
    /*
    void MapinRequest(){
        String nombre = given().get(orden).jsonPath().getObject("id", String.class);
        String nombre = given().get(orden).jsonPath().getObject("petId", String.class);
        String nombre = given().get(orden).jsonPath().getObject("quantity", String.class);
        String nombre = given().get(orden).jsonPath().getObject("shipDate", String.class);
        String nombre = given().get(orden).jsonPath().getObject("status", String.class);
        String nombre = given().get(orden).jsonPath().getObject("complete", String.class);
    }
    */
}
