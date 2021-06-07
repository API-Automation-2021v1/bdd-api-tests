package com.jalasoft.bdd.stepdefs;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.AssertSchemaUtils;
import com.jalasoft.bdd.utils.JsonPathUtils;
import com.jalasoft.bdd.utils.Mapper;
import com.jalasoft.bdd.utils.ReqSpecFactory;
import com.jalasoft.bdd.config.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Defines request step definitions for API requests.
 */
public class RequestSteps {

    private Response response;
    private Response responseFirstList;
    private Response responseSecondList;
    private Response responseThirdList;
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
     * Sends create request.
     *@param body post request.
     * @param endpoint api endpoint.
     */
    @When("the user sends a POST request to {string} with  an empty data in name")
    public void sendsPostRequestWithEmptyNameBoard(final String endpoint, final String body) {
        String endpointMapped = Mapper.mapValue(endpoint, context.getData());
        String bodyMapped = Mapper.mapValue(body, context.getData());
        response = RequestManager.sendPostRequest(endpointMapped, bodyMapped);
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
     * Verifies response body at least a value.
     *
     * @param expectedData expected data.
     */
    @Then("verifies that the response body should contain at least the following value")
    public void verifiesThatResponseBodyShouldContainAtLeastTheFollowingValue(final Map<String, String> expectedData) {
         for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            String allActualValue = JsonPathUtils.getValue(response, entry.getKey());
            String expectedValue = entry.getValue();
            Assert.assertTrue(allActualValue.contains(expectedValue));
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

    /**
     * Sends post request to create a card in the default lists of a board.
     */
    @When("the user creates a card in the default lists")
    public void createCardInDefaultLists() {
        final int startFirstIdList = 1;
        final int endFirstIdList = 25;
        final int startSecondIdList = 27;
        final int endSecondIdList = 51;
        final int startThirdIdList = 53;
        final int endThirdIdList = 77;
        String idBoard = context.getData("id");
        String endPointList = Environment.getInstance()
                .getBaseURL("trello")
                .concat("/boards/")
                .concat(idBoard)
                .concat("/lists");
        response = RequestManager.sendGetRequest(endPointList);
        String idLists = JsonPathUtils.getValue(response, "id");
        String firstIdListDefault = idLists.substring(startFirstIdList, endFirstIdList);
        String secondIdListDefault = idLists.substring(startSecondIdList, endSecondIdList);
        String thirdIdListDefault = idLists.substring(startThirdIdList, endThirdIdList);
        String endPointCreateCard = Environment.getInstance().getBaseURL("trello").concat("/cards");
        String bodyFirstList = "{\"name\":\"First Card by Automation\",\"idList\":\"".concat(firstIdListDefault)
                .concat("\"}");
        String bodySecondList = "{\"name\":\"Second Card by Automation\",\"idList\":\"".concat(secondIdListDefault)
                .concat("\"}");
        String bodyThirdList = "{\"name\":\"Third Card by Automation\",\"idList\":\"".concat(thirdIdListDefault)
                .concat("\"}");
        responseFirstList = RequestManager.sendPostRequest(endPointCreateCard, bodyFirstList);
        responseSecondList = RequestManager.sendPostRequest(endPointCreateCard, bodySecondList);
        responseThirdList = RequestManager.sendPostRequest(endPointCreateCard, bodyThirdList);
    }

    /**
     * Verifies response status code.
     *
     * @param expectedStatusCode expected response status code.
     */
    @Then("verifies that the responses should have the {int} status code")
    public void verifyStatusCodeResponses(final int expectedStatusCode) {
        int actualStatusCodeFirstList = responseFirstList.statusCode();
        int actualStatusCodeSecondList = responseSecondList.statusCode();
        int actualStatusCodeThirdList = responseThirdList.statusCode();
        Assert.assertEquals(actualStatusCodeFirstList, expectedStatusCode);
        Assert.assertEquals(actualStatusCodeSecondList, expectedStatusCode);
        Assert.assertEquals(actualStatusCodeThirdList, expectedStatusCode);
    }

    /**
     * Verifies response body json schema.
     *
     * @param schemaName json schema name.
     */
    @Then("verifies that the responses body should match with {string} JSON schema")
    public void verifyJSONSchemaResponses(final String schemaName) {
        AssertSchemaUtils.validateSchema(responseFirstList, schemaName);
        AssertSchemaUtils.validateSchema(responseSecondList, schemaName);
        AssertSchemaUtils.validateSchema(responseThirdList, schemaName);
    }

    /**
     * Deletes the card in the default lists.
     */
    @When("the user deletes the cards in the default list")
    public void deleteCardInDefaultLists() {
        String idFirstCard = context.getData("idFirstCard");
        String idSecondCard = context.getData("idSecondCard");
        String idThirdCard = context.getData("idThirdCard");
        String endPointDeleteCard = Environment.getInstance().getBaseURL("trello").concat("/cards/");
        responseFirstList = RequestManager.sendDeleteRequest(endPointDeleteCard.concat(idFirstCard));
        responseSecondList = RequestManager.sendDeleteRequest(endPointDeleteCard.concat(idSecondCard));
        responseThirdList = RequestManager.sendDeleteRequest(endPointDeleteCard.concat(idThirdCard));
    }

    /**
     * Tries to delete the cards with a invalid idCard.
     */
    @When("the user try deletes the cards in the default list with invalid idCard")
    public void deleteTryCardWithInvalidIdCard() {
        Random r = new Random();
        final int numberForGenerateRandomCharacter = 26;
        char firstCharacterRandomChar = (char) (r.nextInt(numberForGenerateRandomCharacter) + 'a');
        char secondCharacterRandomChar = (char) (r.nextInt(numberForGenerateRandomCharacter) + 'a');
        char thirdCharacterRandomChar = (char) (r.nextInt(numberForGenerateRandomCharacter) + 'a');
        String firstCharacterRandom = String.valueOf(firstCharacterRandomChar);
        String secondCharacterRandom = String.valueOf(secondCharacterRandomChar);
        String thirdCharacterRandom = String.valueOf(thirdCharacterRandomChar);
        String idFirstCard = context.getData("idFirstCard");
        String idSecondCard = context.getData("idSecondCard");
        String idThirdCard = context.getData("idThirdCard");
        String endPointDeleteCard = Environment.getInstance().getBaseURL("trello").concat("/cards/");
        responseFirstList = RequestManager.sendDeleteRequest(endPointDeleteCard
                .concat(idFirstCard)
                .concat(firstCharacterRandom));
        responseSecondList = RequestManager.sendDeleteRequest(endPointDeleteCard
                .concat(idSecondCard).concat(secondCharacterRandom));
        responseThirdList = RequestManager.sendDeleteRequest(endPointDeleteCard.concat(idThirdCard)
                .concat(thirdCharacterRandom));
    }

    /**
     * Update the card in the default lists.
     */
    @When("the user updates the cards in the default list")
    public void updateCardInDefaultLists() {
        String idFirstCard = context.getData("idFirstCard");
        String idSecondCard = context.getData("idSecondCard");
        String idThirdCard = context.getData("idThirdCard");
        String endPointUpdateCard = Environment.getInstance().getBaseURL("trello").concat("/cards/");
        String bodyFirstCardUpdate = "{\"name\":\"Updating First Card Name\"}";
        String bodySecondCardUpdate = "{\"name\":\"Updating Second Card Name\"}";
        String bodyThirdCardUpdate = "{\"name\":\"Updating Third Card Name\"}";
        responseFirstList = RequestManager.sendPutRequest(endPointUpdateCard
                .concat(idFirstCard), bodyFirstCardUpdate);
        responseSecondList = RequestManager.sendPutRequest(endPointUpdateCard
                .concat(idSecondCard), bodySecondCardUpdate);
        responseThirdList = RequestManager.sendPutRequest(endPointUpdateCard
                .concat(idThirdCard), bodyThirdCardUpdate);
    }

    /**
     * Gets the information of card in the default lists.
     */
    @When("the user gets the information of cards in the default lists")
    public void getCardsInDefaultLists() {
        String idFirstCard = context.getData("idFirstCard");
        String idSecondCard = context.getData("idSecondCard");
        String idThirdCard = context.getData("idThirdCard");
        String endPointGetCard = Environment.getInstance().getBaseURL("trello").concat("/cards/");
        responseFirstList = RequestManager.sendGetRequest(endPointGetCard.concat(idFirstCard));
        responseSecondList = RequestManager.sendGetRequest(endPointGetCard.concat(idSecondCard));
        responseThirdList = RequestManager.sendGetRequest(endPointGetCard.concat(idThirdCard));
    }

    /**
     * Creates a card with a empty name for a card.
     */
    @When("the user creates a card in the default lists with empty name for a card")
    public void postCardInvalidBody() {
        final int startFirstIdList = 1;
        final int endFirstIdList = 25;
        final int startSecondIdList = 27;
        final int endSecondIdList = 51;
        final int startThirdIdList = 53;
        final int endThirdIdList = 77;
        String idBoard = context.getData("id");
        String endPointList = Environment.getInstance()
                .getBaseURL("trello")
                .concat("/boards/")
                .concat(idBoard)
                .concat("/lists");
        response = RequestManager.sendGetRequest(endPointList);
        String idLists = JsonPathUtils.getValue(response, "id");
        String firstIdListDefault = idLists.substring(startFirstIdList, endFirstIdList);
        String secondIdListDefault = idLists.substring(startSecondIdList, endSecondIdList);
        String thirdIdListDefault = idLists.substring(startThirdIdList, endThirdIdList);
        String endPointCreateCard = Environment.getInstance().getBaseURL("trello").concat("/cards");
        String bodyFirstList = "{\"name\":\"    \",\"idList\":\"".concat(firstIdListDefault)
                .concat("\"}");
        String bodySecondList = "{\"name\":\"    \",\"idList\":\"".concat(secondIdListDefault)
                .concat("\"}");
        String bodyThirdList = "{\"name\":\"    \",\"idList\":\"".concat(thirdIdListDefault)
                .concat("\"}");
        responseFirstList = RequestManager.sendPostRequest(endPointCreateCard, bodyFirstList);
        responseSecondList = RequestManager.sendPostRequest(endPointCreateCard, bodySecondList);
        responseThirdList = RequestManager.sendPostRequest(endPointCreateCard, bodyThirdList);
    }

    /**
     * Creates a card with a empty idList for a card.
     */
    @When("the user creates a card in the default lists with empty idList")
    public void postCardEmptyIdList() {
        String idBoard = context.getData("id");
        String endPointList = Environment.getInstance()
                .getBaseURL("trello")
                .concat("/boards/")
                .concat(idBoard)
                .concat("/lists");
        response = RequestManager.sendGetRequest(endPointList);
        String endPointCreateCard = Environment.getInstance().getBaseURL("trello").concat("/cards");
        String bodyFirstList = "{\"name\":\"First Card by Automation\",\"idList\":\""
                .concat("\"}");
        String bodySecondList = "{\"name\":\"Second Card by Automation\",\"idList\":\""
                .concat("\"}");
        String bodyThirdList = "{\"name\":\"Third Card by Automation\",\"idList\":\""
                .concat("\"}");
        responseFirstList = RequestManager.sendPostRequest(endPointCreateCard, bodyFirstList);
        responseSecondList = RequestManager.sendPostRequest(endPointCreateCard, bodySecondList);
        responseThirdList = RequestManager.sendPostRequest(endPointCreateCard, bodyThirdList);
    }

    /**
     * Updates a card without idCard for a card.
     */
    @When("the user updates the cards in the default lists without idCard")
    public void putCardEmptyIdList() {
        String endPointUpdateCard = Environment.getInstance().getBaseURL("trello").concat("/cards/");
        String bodyFirstCardUpdate = "{\"name\":\"Updating First Card Name\"}";
        String bodySecondCardUpdate = "{\"name\":\"Updating Second Card Name\"}";
        String bodyThirdCardUpdate = "{\"name\":\"Updating Third Card Name\"}";
        responseFirstList = RequestManager.sendPutRequest(endPointUpdateCard, bodyFirstCardUpdate);
        responseSecondList = RequestManager.sendPutRequest(endPointUpdateCard, bodySecondCardUpdate);
        responseThirdList = RequestManager.sendPutRequest(endPointUpdateCard, bodyThirdCardUpdate);
    }

    /**
     * Gets a card information without idCard.
     */
    @When("the user try gets the information of cards in the default lists without idCard")
    public void getTryCardInformation() {
        String endPointGetCard = Environment.getInstance().getBaseURL("trello").concat("/cards/");
        responseFirstList = RequestManager.sendGetRequest(endPointGetCard);
        responseSecondList = RequestManager.sendGetRequest(endPointGetCard);
        responseThirdList = RequestManager.sendGetRequest(endPointGetCard);
    }
}
