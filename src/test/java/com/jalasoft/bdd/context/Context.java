package com.jalasoft.bdd.context;

import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to store and share data among step definitions.
 */
public class Context {

    private static final Logger LOGGER = LogManager.getLogger(Context.class);

    private Map<String, String> data;
    private Map<String, Object> responseData;

    /**
     * Initializes an instance of context.
     */
    public Context() {
        data = new HashMap<>();
        responseData = new HashMap<>();
    }

    /**
     * Stores data.
     *
     * @param key   key.
     * @param value value.
     */
    public void storeData(final String key, final String value) {
        data.put(key, value);
    }

    /**
     * Gets data stored.
     *
     * @param key key.
     * @return data value.
     */
    public String getData(final String key) {
        return data.get(key);
    }

    /**
     * Gets all data stored.
     *
     * @return data.
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * Stores response data.
     *
     * @param response response instance.
     */
    public void storeResponseData(final Response response) {
        String body = response.getBody().asString();
        if (body.isEmpty() || body.startsWith("[")) {
            return;
        }
        try {
            Map<String, Object> data = response.jsonPath().getMap(".");
            this.responseData.putAll(data);
        } catch (ClassCastException | JsonPathException e) {
            LOGGER.error("Cannot store data from response.");
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Gets response data.
     *
     * @param key key.
     * @return data value.
     */
    public Object getResponseData(final String key) {
        return responseData.get(key);
    }
}
