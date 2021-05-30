package com.jalasoft.bdd.config;

/**
 * Models api config entity.
 */
public class ApiConfig {

    private String name;
    private String baseURL;
    private String apiToken;
    private String apiKey;

    /**
     * Gets name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name name to be set.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets base url.
     *
     * @return base url.
     */
    public String getBaseURL() {
        return baseURL;
    }

    /**
     * Sets base url.
     *
     * @param baseURL base url to be set.
     */
    public void setBaseURL(final String baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * Gets api token.
     *
     * @return api token.
     */
    public String getApiToken() {
        return apiToken;
    }

    /**
     * Sets api token.
     *
     * @param apiToken api token to be set.
     */
    public void setApiToken(final String apiToken) {
        this.apiToken = apiToken;
    }

    /**
     * Gets api key.
     *
     * @return api key.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets api key.
     *
     * @param apiKey api key to be set.
     */
    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }
}
