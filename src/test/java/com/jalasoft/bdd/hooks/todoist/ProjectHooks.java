package com.jalasoft.bdd.hooks.todoist;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.JsonPathUtils;
import com.jalasoft.bdd.utils.ReqSpecFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;

/**
 * Defines preconditions and post conditions for Todoist Projects API interactions.
 */
public class ProjectHooks {

    private Context context;

    /**
     * Initializes an instance of ProjectHooks class.
     *
     * @param context context instance.
     */
    public ProjectHooks(final Context context) {
        this.context = context;
    }

    /**
     * Creates a project.
     */
    @Before(value = "@createTodoistProjectPreCondition")
    public void createProject() {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("todoist"));
        String body = "{\"name\": \"API Automation Project\"}";
        Response response = RequestManager.sendPostRequest("projects", body);
        String id = JsonPathUtils.getValue(response, "id");
        context.storeData("id", id);
        context.storeResponseData(response);
    }

    /**
     * Deletes a project.
     */
    @After(value = "@deleteTodoistProjectPostCondition")
    public void deleteProject() {
        String id = context.getData("id") == null
                ? context.getResponseData("id").toString()
                : context.getData("id");
        if (id != null && !id.isEmpty()) {
            RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("todoist"));
            String endpoint = "projects/".concat(id);
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
