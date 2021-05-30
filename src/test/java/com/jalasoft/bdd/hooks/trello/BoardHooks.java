package com.jalasoft.bdd.hooks.trello;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.JsonPathUtils;
import com.jalasoft.bdd.utils.ReqSpecFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;

/**
 * Defines preconditions and post conditions for Trello Boards API interactions.
 */
public class BoardHooks {

    private Context context;

    /**
     * Initializes an instance of BoardHooks class.
     *
     * @param context context instance.
     */
    public BoardHooks(final Context context) {
        this.context = context;
    }

    /**
     * Creates a board.
     */
    @Before(value = "@createTrelloBoardPreCondition")
    public void createBoard() {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("trello"));
        String body = "{\"name\": \"API Automation Board\"}";
        Response response = RequestManager.sendPostRequest("boards", body);
        String id = JsonPathUtils.getValue(response, "id");
        context.storeData("id", id);
    }

    /**
     * Deletes a board.
     */
    @After(value = "@deleteTrelloBoardPostCondition")
    public void deleteBoard() {
        String id = context.getResponseData("id").toString();
        if (id != null && !id.isEmpty()) {
            RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("trello"));
            String endpoint = "boards/".concat(id);
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
