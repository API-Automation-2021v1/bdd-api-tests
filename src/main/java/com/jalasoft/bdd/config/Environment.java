package com.jalasoft.bdd.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jalasoft.bdd.utils.FileManager;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines environment class to get configuration parameters.
 */
public final class Environment {

    private static final String JSON_CONFIG_PATH = "config.json";
    private static Environment instance;

    private List<ApiConfig> apiConfigs;

    /**
     * Private constructor for singleton class.
     */
    private Environment() {
        Gson gson = new GsonBuilder().create();
        Type listType = TypeToken.getParameterized(ArrayList.class, ApiConfig.class).getType();
        Reader reader = FileManager.getReader(JSON_CONFIG_PATH);
        apiConfigs = gson.fromJson(reader, listType);
    }

    /**
     * Gets singleton instance of environment class.
     *
     * @return singleton instance.
     */
    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    /**
     * Gets token.
     *
     * @param apiName api name.
     * @return token value.
     */
    public String getApiToken(final String apiName) {
        return getApiConfig(apiName).getApiToken();
    }

    /**
     * Gets service base URL.
     *
     * @param apiName api name.
     * @return base URL.
     */
    public String getApiKey(final String apiName) {
        return getApiConfig(apiName).getApiKey();
    }

    /**
     * Gets service base URL.
     *
     * @param apiName api name.
     * @return base URL.
     */
    public String getBaseURL(final String apiName) {
        return getApiConfig(apiName).getBaseURL();
    }

    /**
     * Gets api config according to api name.
     *
     * @param apiName api name.
     * @return api config object.
     */
    private ApiConfig getApiConfig(final String apiName) {
        for (ApiConfig config : apiConfigs) {
            if (config.getName().equalsIgnoreCase(apiName)) {
                return config;
            }
        }
        return new ApiConfig();
    }
}
