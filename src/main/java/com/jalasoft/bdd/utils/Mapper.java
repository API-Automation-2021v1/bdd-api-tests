package com.jalasoft.bdd.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines utility method to map values according to map data.
 */
public final class Mapper {

    /**
     * Private constructor for Mapper utility class.
     */
    private Mapper() {
        // Defaults constructor.
    }

    /**
     * Maps value according to map.
     *
     * @param value original value to be mapped.
     * @param data  stored data to be used during mapping.
     * @return value mapped.
     */
    public static String mapValue(final String value, final Map<String, String> data) {
        String output = value;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            output = output.replace("{" + entry.getKey() + "}", String.format("%s", entry.getValue()));
        }
        return output;
    }

    /**
     * Maps values according to map.
     *
     * @param values original values to be mapped.
     * @param data   stored data to be used during mapping.
     * @return values mapped.
     */
    public static Map<String, String> mapValues(final Map<String, String> values, final Map<String, String> data) {
        Map<String, String> output = new HashMap<>();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            output.put(entry.getKey(), mapValue(entry.getValue(), data));
        }
        return output;
    }
}
