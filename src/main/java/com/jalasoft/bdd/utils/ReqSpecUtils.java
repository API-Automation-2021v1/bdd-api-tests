package com.jalasoft.bdd.utils;

import com.jalasoft.bdd.config.Environment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Defines methods to build request specifications.
 */
public final class ReqSpecUtils {

    /**
     * Private constructor for ReqSpecUtils utility class.
     */
    private ReqSpecUtils() {
        // Default constructor.
    }

    /**
     * Builds request specification for Pivotal Tracker API.
     *
     * @return request specification.
     */
    public static RequestSpecification buildPivotalBaseRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getBaseURL("pivotal tracker"))
                .addHeaders(getPivotalDefaultHeaders())
                .build();
    }


    /**
     * Builds request specification for Pivotal Tracker API.
     *
     * @param headers request headers.
     * @return request specification.
     */
    public static RequestSpecification buildPivotalBaseRequest(final Map<String, String> headers) {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getBaseURL("pivotal tracker"))
                .addHeaders(headers)
                .build();
    }

    /**
     * Builds request specification for Trello API.
     *
     * @return request specification.
     */
    public static RequestSpecification buildTrelloBaseRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getBaseURL("trello"))
                .addHeaders(getTrelloDefaultHeaders())
                .addQueryParams(getTrelloDefaultParams())
                .build();
    }

    /**
     * Builds request specification for Trello API.
     *
     * @param headers request headers.
     * @return request specification.
     */
    public static RequestSpecification buildTrelloBaseRequest(final Map<String, String> headers) {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getBaseURL("trello"))
                .addHeaders(getTrelloDefaultHeaders())
                .addQueryParams(headers)
                .build();
    }

    /**
     * Builds request specification for Todoist API.
     *
     * @return request specification.
     */
    public static RequestSpecification buildTodoistBaseRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getBaseURL("todoist"))
                .addHeaders(getTodoistDefaultHeaders())
                .build();
    }

    /**
     * Builds request specification for Todoist API.
     *
     * @param headers request headers.
     * @return request specification.
     */
    public static RequestSpecification buildTodoistBaseRequest(final Map<String, String> headers) {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getBaseURL("todoist"))
                .addHeaders(headers)
                .build();
    }

    /**
     * Builds default headers for Pivotal Tracker API.
     *
     * @return default headers map.
     */
    private static Map<String, String> getPivotalDefaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-TrackerToken", Environment.getInstance().getApiToken("pivotal tracker"));
        return headers;
    }

    /**
     * Builds default headers for Trello API.
     *
     * @return default headers map.
     */
    private static Map<String, String> getTrelloDefaultParams() {
        Map<String, String> params = new HashMap<>();
        params.put("key", Environment.getInstance().getApiKey("trello"));
        params.put("token", Environment.getInstance().getApiToken("trello"));
        return params;
    }

    /**
     * Builds default headers for Trello API.
     *
     * @return default headers map.
     */
    private static Map<String, String> getTrelloDefaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    /**
     * Builds default headers for Todoist API.
     *
     * @return default headers map.
     */
    private static Map<String, String> getTodoistDefaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Request-Id", UUID.randomUUID().toString());
        headers.put("Authorization", "Bearer ".concat(Environment.getInstance().getApiToken("todoist")));
        return headers;
    }
}
