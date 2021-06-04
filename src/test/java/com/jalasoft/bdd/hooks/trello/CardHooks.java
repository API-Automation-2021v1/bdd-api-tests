package com.jalasoft.bdd.hooks.trello;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.config.Environment;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.JsonPathUtils;
import io.cucumber.java.Before;
import io.restassured.response.Response;

/**
 * Defines preconditions and post conditions for Trello Cards API interactions.
 */
public class CardHooks {

    private Context context;
    private Response response;

    /**
     * Initializes an instance of CardsHooks class.
     *
     * @param context context instance.
     */
    public CardHooks(final Context context) {
        this.context = context;
    }

    /**
     * Creates a card.
     */
    @Before(value = "@createTrelloCardPreCondition")
    public void createBoard() {
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
        String bodyFirstList = "{\"name\":\"First Card by Automation Pre Condition\",\"idList\":\""
                .concat(firstIdListDefault)
                .concat("\"}");
        String bodySecondList = "{\"name\":\"First Card by Automation Pre Condition\",\"idList\":\""
                .concat(secondIdListDefault)
                .concat("\"}");
        String bodyThirdList = "{\"name\":\"First Card by Automation Pre Condition\",\"idList\":\""
                .concat(thirdIdListDefault)
                .concat("\"}");
        response = RequestManager.sendPostRequest(endPointCreateCard, bodyFirstList);
        String idFirstCard = JsonPathUtils.getValue(response, "id");
        response = RequestManager.sendPostRequest(endPointCreateCard, bodySecondList);
        String idSecondCard = JsonPathUtils.getValue(response, "id");
        response = RequestManager.sendPostRequest(endPointCreateCard, bodyThirdList);
        String idThirdCard = JsonPathUtils.getValue(response, "id");
        context.storeData("idFirstCard", idFirstCard);
        context.storeData("idSecondCard", idSecondCard);
        context.storeData("idThirdCard", idThirdCard);
    }
}
