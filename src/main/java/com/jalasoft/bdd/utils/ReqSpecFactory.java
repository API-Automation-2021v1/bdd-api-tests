package com.jalasoft.bdd.utils;

import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Defines method to build request specifications.
 */
public final class ReqSpecFactory {

    private static final Logger LOGGER = LogManager.getLogger(ReqSpecFactory.class);

    /**
     * Private constructor for ReqSpecFactory utility class.
     */
    private ReqSpecFactory() {
        // Default constructor.
    }

    /**
     * Builds request specification according to api name.
     *
     * @param apiName api name.
     * @return request specification.
     */
    public static RequestSpecification buildReqSpec(final String apiName) {
        RequestSpecification reqSpec;
        switch (apiName.toLowerCase()) {
            case "pivotal tracker":
                reqSpec = ReqSpecUtils.buildPivotalBaseRequest();
                break;
            case "trello":
                reqSpec = ReqSpecUtils.buildTrelloBaseRequest();
                break;
            case "todoist":
                reqSpec = ReqSpecUtils.buildTodoistBaseRequest();
                break;
            default:
                LOGGER.error("API name parameter is invalid.");
                reqSpec = null;
        }
        return reqSpec;
    }

    /**
     * Builds request specification according to api name.
     *
     * @param apiName api name.
     * @param headers request headers.
     * @return request specification.
     */
    public static RequestSpecification buildReqSpec(final String apiName, final Map<String, String> headers) {
        RequestSpecification reqSpec;
        switch (apiName.toLowerCase()) {
            case "pivotal tracker":
                reqSpec = ReqSpecUtils.buildPivotalBaseRequest(headers);
                break;
            case "trello":
                reqSpec = ReqSpecUtils.buildTrelloBaseRequest(headers);
                break;
            case "todoist":
                reqSpec = ReqSpecUtils.buildTodoistBaseRequest(headers);
                break;
            default:
                LOGGER.error("API name parameter is invalid.");
                reqSpec = null;
        }
        return reqSpec;
    }
}
