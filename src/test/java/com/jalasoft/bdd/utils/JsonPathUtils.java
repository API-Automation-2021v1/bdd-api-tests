package com.jalasoft.bdd.utils;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Defines Json Path utility methods.
 */
public final class JsonPathUtils {

    private static final Logger LOGGER = LogManager.getLogger(JsonPathUtils.class);

    /**
     * Private constructor for JsonPathUtils utility class.
     */
    private JsonPathUtils() {
        // Default constructor for utility class.
    }

    /**
     * Gets string value according to Json path expression.
     *
     * @param response response object.
     * @param jsonPath json path expression.
     * @return string value.
     */
    public static String getValue(final Response response, final String jsonPath) {
        String value = response.jsonPath().getString(jsonPath);
        if (value == null) {
            LOGGER.error("The JsonPath is not valid or the value is null.");
        }
        return value;
    }

    /**
     * Gets the body of response.
     *
     * @param response response object.
     * @return ArrayList value.
     */
    public static List getResponseBody(final Response response) {
        List value = response.jsonPath().get();
        if (value == null) {
            LOGGER.error("The JsonPath is not valid or the value is null.");
        }
        return value;
    }
}
