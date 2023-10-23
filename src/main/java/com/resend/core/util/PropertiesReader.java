package com.resend.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private final Properties properties;

    public PropertiesReader() {
        this.properties = new Properties();
    }
    public String retrieveProperty(String propertyName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("gradle.properties");
            properties.load(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(propertyName);
    }
}
