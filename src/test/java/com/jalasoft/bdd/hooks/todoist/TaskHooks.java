package com.jalasoft.bdd.hooks.todoist;

import com.jalasoft.bdd.client.RequestManager;
import com.jalasoft.bdd.context.Context;
import com.jalasoft.bdd.utils.JsonPathUtils;
import com.jalasoft.bdd.utils.ReqSpecFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
/**
 * Defines preconditions and post conditions for Todoist Task API interactions.
 */
public class TaskHooks {
    private Context context;

    /**
     * Initializes an instance of tasks Hooks class.
     *
     * @param context context instance.
     */
    public TaskHooks(final Context context) {
        this.context = context;
    }

    /**
     * Creates a tasks.
     */
    @Before(value = "@createTodoistTaskPreCondition")
    public void createTask() {
        RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("todoist"));
        String body = "{\"name\": \"API Automation Task\"}";
        Response response = RequestManager.sendPostRequest("tasks", body);
        String id = JsonPathUtils.getValue(response, "id");
        context.storeData("id", id);
        context.storeResponseData(response);
    }

    /**
     * Delete a task.
     */
    @After(value = "@deleteTodoistTaskPostCondition")
    public void deleteTask() {
        String id = context.getData("id") == null
                ? context.getResponseData("id").toString()
                : context.getData("id");
        if (id != null && !id.isEmpty()) {
            RequestManager.setReqSpec(ReqSpecFactory.buildReqSpec("todoist"));
            String endpoint = "tasks/".concat(id);
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
