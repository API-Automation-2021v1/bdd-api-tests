package com.jalasoft.bdd.hooks.pivotal;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.JsonPathUtils;
import com.jalasoft.bdd.utils.ReqSpecFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;

/**
 * Defines preconditions and post conditions for Pivotal Tracker Projects API interactions.
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
    @Before(value = "@createPivotalProjectPreCondition")
    public void createProject() {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("pivotal tracker"));
        String body = "{\"name\": \"API Automation\"}";
        Response response = RequestManager.sendPostRequest("projects", body);
        String id = JsonPathUtils.getValue(response, "id");
        context.storeData("id", id);
    }

    /**
     * Create a label in the project.
     */
    @Before(value = "@createPivotalProjectWithLabelPreCondition")
    public void createProjectWithLabel() {
        createProject();
        String projectId = context.getData("id");
        String endpoint = "projects/".concat(projectId).concat("/labels");
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("pivotal tracker"));
        String body = "{\"name\": \"api label\"}";
        Response response = RequestManager.sendPostRequest(endpoint, body);
        String id = JsonPathUtils.getValue(response, "id");
        String labelName = JsonPathUtils.getValue(response, "name");
        context.storeData("label_id", id);
        context.storeData("label_name", labelName);
    }

    /**
     * Deletes a project.
     */
    @After(value = "@deletePivotalProjectPostCondition")
    public void deleteProject() {
        String id = context.getData("id") == null
                ? context.getResponseData("id").toString()
                : context.getData("id");
        if (id != null && !id.isEmpty()) {
            RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("pivotal tracker"));
            String endpoint = "projects/".concat(id);
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
