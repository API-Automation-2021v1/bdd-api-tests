package com.jalasoft.bdd.hooks.pivotal;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.JsonPathUtils;
import com.jalasoft.bdd.utils.ReqSpecFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;

/**
 * Defines preconditions and post conditions for Pivotal Tracker workspace API interactions.
 */
public class WorkspaceHooks {
    private Context context;

    /**
     * Initializes an instance of WorkspacesHooks class.
     *
     * @param context context instance.
     */
    public WorkspaceHooks(final Context context) {
        this.context = context;
    }

    /**
     * Creates a workspaces.
     */
    @Before(value = "@createPivotalWorkspacePreCondition")
    public void createWorkspace() {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("pivotal tracker"));
        String body = "{\"name\": \"API Automation workspace\"}";
        Response response = RequestManager.sendPostRequest("my/workspaces", body);
        String id = JsonPathUtils.getValue(response, "id");
        context.storeData("workspaceId", id);
    }

    /**
     * Deletes a workspaces.
     */
    @After(value = "@deletePivotalWorkspacesPostCondition")
    public void deleteProject() {
        String id = context.getData("workspaceId") == null
                ? context.getResponseData("workspaceId").toString()
                : context.getData("workspaceId");
        if (id != null && !id.isEmpty()) {
            RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("pivotal tracker"));
            String endpoint = "my/workspaces/".concat(id);
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
