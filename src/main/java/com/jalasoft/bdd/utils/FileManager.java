package com.jalasoft.bdd.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Defines utility methods for file management.
 */
public final class FileManager {

    private static final Logger LOGGER = LogManager.getLogger(FileManager.class);

    /**
     * Private constructor for utility class.
     */
    private FileManager() {
        // Default constructor for utility class.
    }

    /**
     * Gets reader from file path.
     *
     * @param path file path.
     * @return file reader object.
     */
    public static Reader getReader(final String path) {
        try {
            return new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            LOGGER.error("Cannot create reader object.");
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
