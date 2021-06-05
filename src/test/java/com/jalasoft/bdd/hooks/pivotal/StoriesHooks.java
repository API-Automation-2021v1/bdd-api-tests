package com.jalasoft.bdd.hooks.pivotal;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.JsonPathUtils;
import com.jalasoft.bdd.utils.ReqSpecFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;

public class StoriesHooks {

    private Context context;

    /**
     * Initializes an instance of StoriesHooks class.
     *
     * @param context context instance.
     */
    public StoriesHooks(final Context context) {
        this.context = context;
    }

    /**
     * Creates a Story.
     */
    @Before(value = "@createPivotalStoriesPreCondition")
    public void createStory() {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("pivotal tracker"));
        String body = "{\"name\": \"API Automation Pivotal Stories\"}";
        Response response = RequestManager.sendPostRequest("project/{project_id}/stories", body);
        String id = JsonPathUtils.getValue(response, "id");
        context.storeData("id", id);

    }

    /**
     * Deletes a Story.
     */
    @After(value = "@deletePivotalStoryPostCondition")
    public void deleteStory() {
        String id = context.getData("id") == null
                ? context.getResponseData("id").toString()
                : context.getData("id");
        if (id != null && !id.isEmpty()) {
            RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("pivotal tracker"));
            String endpoint = "project/{project_id}/stories/".concat(id);
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
