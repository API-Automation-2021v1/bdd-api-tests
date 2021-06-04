package com.jalasoft.bdd.hooks.trello;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.JsonPathUtils;
import com.jalasoft.bdd.utils.ReqSpecFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;

public class OrganizationHooks {
    private Context context;

    /**
     * Initializes an instance of BoardHooks class.
     *
     * @param context context instance.
     */
    public OrganizationHooks(final Context context) {
        this.context = context;
    }

    /**
     * Creates an organization.
     */
    @Before(value = "@createTrelloOrganizationPreCondition", order = 1)
    public void createOrganization() {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("trello"));
        String body = "{\"displayName\": \"API Automation Organization\"}";
        Response response = RequestManager.sendPostRequest("organizations", body);
        String id = JsonPathUtils.getValue(response, "id");
        context.storeData("id", id);
    }

    /**
     * Creates a Board on an organization.
     */
    @Before(value = "@createTrelloBoardOnOrganizationPreCondition", order = 2)
    public void createBoardOnOrganization() {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("trello"));
        String body = "{\"name\": \"API Automation Board on Org\",\n"
                + "\"idOrganization\": \"" + context.getData("id") + "\"}";
        Response response = RequestManager.sendPostRequest("boards", body);
        String id = JsonPathUtils.getValue(response, "id");
        context.storeData("idBoard", id);
    }

    /**
     * Deletes a Board.
     */
    @After(value = "@deleteTrelloBoardPostCondition")
    public void deleteBoardOrganization() {
        String id = context.getData("idBoard") == null
                ? context.getResponseData("idBoard").toString()
                : context.getData("idBoard");
        if (id != null && !id.isEmpty()) {
            RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("trello"));
            String endpoint = "boards/".concat(id);
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
    /**
     * Deletes an organization.
     */
    @After(value = "@deleteTrelloOrganizationPostCondition")
    public void deleteOrganization() {
        String id = context.getData("id") == null
                ? context.getResponseData("id").toString()
                : context.getData("id");
        if (id != null && !id.isEmpty()) {
            RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("trello"));
            String endpoint = "organizations/".concat(id);
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
