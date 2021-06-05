package com.jalasoft.bdd.stepdefs;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.AssertSchemaUtils;
import com.jalasoft.bdd.utils.JsonPathUtils;
import com.jalasoft.bdd.utils.Mapper;
import com.jalasoft.bdd.utils.ReqSpecFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Defines request step definitions for API requests.
 */
public class RequestSteps {

    private Response response;
    private Context context;

    /**
     * Initializes an instance of RequestSteps.
     *
     * @param context context instance.
     */
    public RequestSteps(final Context context) {
        this.context = context;
    }

    /**
     * Sets valid authentication headers.
     *
     * @param apiName api name.
     */
    @Given("the user sets valid authentication headers for {string} API request")
    public void setsAuthenticationHeaders(final String apiName) {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec(apiName));
    }

    /**
     * Sets valid authentication headers.
     *
     * @param apiName api name.
     * @param headers request headers.
     */
    @Given("the user sets the following headers for {string} API request")
    public void theUserSetsTheFollowingHeadersForAPIRequest(final String apiName, final Map<String, String> headers) {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec(apiName, headers));
    }

    /**
     * Sends get request.
     *
     * @param endpoint api endpoint.
     */
    @When("the user sends a GET request to {string}")
    public void sendsGETRequestWithData(final String endpoint) {
        String endpointMapped = Mapper.mapValue(endpoint, context.getData());
        response = RequestManager.sendGetRequest(endpointMapped);
    }

    /**
     * Sends post request with data.
     *
     * @param endpoint api endpoint.
     * @param body     request body.
     */
    @When("the user sends a POST request to {string} with the following data")
    public void sendsPOSTRequestWithData(final String endpoint, final String body) {
        String endpointMapped = Mapper.mapValue(endpoint, context.getData());
        String bodyMapped = Mapper.mapValue(body, context.getData());
        response = RequestManager.sendPostRequest(endpointMapped, bodyMapped);
    }

    /**
     * Sends put request with data.
     *
     * @param endpoint api endpoint.
     * @param body     request body.
     */
    @When("the user sends a PUT request to {string} with the following data")
    public void sendsPUTRequestWithData(final String endpoint, final String body) {
        String endpointMapped = Mapper.mapValue(endpoint, context.getData());
        String bodyMapped = Mapper.mapValue(body, context.getData());
        response = RequestManager.sendPutRequest(endpointMapped, bodyMapped);
    }

    /**
     * Sends delete request.
     *
     * @param endpoint api endpoint.
     */
    @When("the user sends a DELETE request to {string}")
    public void sendsDELETERequestWithData(final String endpoint) {
        String endpointMapped = Mapper.mapValue(endpoint, context.getData());
        response = RequestManager.sendDeleteRequest(endpointMapped);
    }

    /**
     * Verifies response status code.
     *
     * @param expectedStatusCode expected response status code.
     */
    @Then("verifies that the response should have the {int} status code")
    public void verifyStatusCode(final int expectedStatusCode) {
        context.storeResponseData(response);
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    /**
     * Verifies response body json schema.
     *
     * @param schemaName json schema name.
     */
    @Then("verifies that the response body should match with {string} JSON schema")
    public void verifyJSONSchema(final String schemaName) {
        AssertSchemaUtils.validateSchema(response, schemaName);
    }

    /**
     * Verifies response body content.
     *
     * @param expectedData expected data.
     */
    @Then("verifies that the response body should contain the following values")
    public void verifyBodyContent(final Map<String, String> expectedData) {
        Map<String, String> mappedData = Mapper.mapValues(expectedData, context.getData());
        for (Map.Entry<String, String> entry : mappedData.entrySet()) {
            String actualValue = JsonPathUtils.getValue(response, entry.getKey());
            String expectedValue = entry.getValue();
            Assert.assertEquals(actualValue, expectedValue);
        }
    }

    /**
     * Verifies response body is empty.
     *
     */
    @Then("verifies that the response body should empty")
    public void verifyEmptyBody() {
        List expectedResponse = new ArrayList();
        Assert.assertEquals(JsonPathUtils.getResponseBody(response), expectedResponse);
    }
}
