package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Products {
    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int ResponseCode;
    public ResponseBody body;
    public JSONObject requestParams;

    public JsonPath jsnPath;

    @Given("I hit the url of get products api endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {
        // Write code here that turns the phrase above into concrete actions
        httpRequest = RestAssured.given();
        response = httpRequest.get("products");
    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        ResponseCode = response.getStatusCode();
        assertEquals(ResponseCode, 200);
    }

    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is(String rate) {
        // Write code here that turns the phrase above into concrete actions
        body = response.getBody();

        String responseBody = body.asString();

        JsonPath jsnpath = response.jsonPath();

        String s = jsnpath.getJsonObject("rating[0].rate").toString();
        String expectedCategory = jsnpath.getJsonObject("category[0]").toString();
        String idTen = jsnpath.getJsonObject("id[9]").toString();

        assertEquals(rate, s);
        assertEquals("men's clothing", expectedCategory);

        assertEquals("10", idTen);
    }

    @Given("I hit the url of post products api endpoint")
    public void iHitTheUrlOfPostProductsApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();
        requestParams = new JSONObject();
    }

    @And("I pass the request body of the product title {}")
    public void iPassTheRequestBodyOfTheProductTitle(String title) {
        requestParams.put("title", title);
        requestParams.put("price", 1000.05);
        requestParams.put("description", "Your usual BS overpriced");
        requestParams.put("category", "others");
        requestParams.put("image", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");

        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post("products");
        ResponseBody body = response.getBody();
        jsnPath = response.jsonPath();

        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAsId(String arg0) {
        String s = jsnPath.getJsonObject("id").toString();
        assertEquals("21", s);
    }

    @Given("I hit the url of put products api endpoint")
    public void iHitTheUrlOfPutProductsApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();
    }

    @When("I pass the url of products in the request with {}")
    public void iPassTheUrlOfProductsInTheRequestWithProductNumber(String productNumber) {
        httpRequest = RestAssured.given();
        requestParams.put("title", "test product");
        requestParams.put("price", 1001);
        requestParams.put("description", "Your usual BS overpriced");
        requestParams.put("category", "others");
        requestParams.put("image", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.put("products/" + productNumber);
        ResponseBody body = response.getBody();
        jsnPath = response.jsonPath();
        jsnPath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Given("I hit the url of delete products api endpoint")
    public void iHitTheUrlOfDeleteProductsApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();
    }

    @When("I pass the url of delete products in the request with {}")
    public void iPassTheUrlOfDeleteProductsInTheRequestWithProductNumber(String productNumber) {
        httpRequest = RestAssured.given();
        requestParams.put("title", "test product");
        requestParams.put("price", 1001);
        requestParams.put("description", "Your usual BS overpriced");
        requestParams.put("category", "others");
        requestParams.put("image", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.delete("products/" + productNumber);
        ResponseBody body = response.getBody();
        jsnPath = response.jsonPath();
        jsnPath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }
}


