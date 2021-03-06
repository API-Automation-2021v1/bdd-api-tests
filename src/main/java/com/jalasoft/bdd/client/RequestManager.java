package com.jalasoft.bdd.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Defines methods to send requests.
 */
public final class RequestManager {

    private static final Logger LOGGER = LogManager.getLogger(RequestManager.class);
    private static RequestSpecification requestSpecification;

    /**
     * Private constructor for RequestManager utility class.
     */
    private RequestManager() {
        // Default constructor for utility class.
    }

    /**
     * Sets request specification.
     *
     * @param reqSpec request specification.
     */
    public static void setReqSpec(final RequestSpecification reqSpec) {
        requestSpecification = reqSpec;
    }

    /**
     * Sends a GET request.
     *
     * @param endpoint service endpoint.
     * @return response object.
     */
    public static Response sendGetRequest(final String endpoint) {
        LOGGER.info("Sending GET request");
        LOGGER.info("Endpoint: " + endpoint);
        return RestAssured.given(requestSpecification).when().get(endpoint);
    }

    /**
     * Sends POST request.
     *
     * @param endpoint service endpoint.
     * @param body     request body.
     * @return response object.
     */
    public static Response sendPostRequest(final String endpoint, final String body) {
        LOGGER.info("Sending POST request");
        LOGGER.info("Endpoint: " + endpoint);
        LOGGER.info("Body: " + body);
        return RestAssured.given(requestSpecification.body(body)).when().post(endpoint);
    }

    /**
     * Sends PUT request.
     *
     * @param endpoint service endpoint.
     * @param body     request body.
     * @return response object.
     */
    public static Response sendPutRequest(final String endpoint, final String body) {
        LOGGER.info("Sending PUT request");
        LOGGER.info("Endpoint: " + endpoint);
        LOGGER.info("Body: " + body);
        return RestAssured.given(requestSpecification.body(body)).when().put(endpoint);
    }

    /**
     * Sends DELETE request.
     *
     * @param endpoint service endpoint.
     * @return response object.
     */
    public static Response sendDeleteRequest(final String endpoint) {
        LOGGER.info("Sending DELETE request");
        LOGGER.info("Endpoint: " + endpoint);
        return RestAssured.given(requestSpecification).when().delete(endpoint);
    }
}
