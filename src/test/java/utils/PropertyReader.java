package utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {
    private static String propertiesPath = "config.properties";
    private static volatile Properties properties;
    private static InputStream inputStream;

    private PropertyReader() {
    }

    public static Properties readProperties() {
        properties = new Properties();
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propertiesPath)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("config.properties not found in classpath");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (properties.getProperty("config_file") != null) {
            Properties additionalProperties = getProperties(properties.getProperty("config_file"));
            properties.putAll(additionalProperties);
        }

        return properties;
    }

    private static Properties loadProperties() {
        return properties != null ? properties : readProperties();
    }

    public static Properties getProperties(String path) {
        propertiesPath = path;
        return readProperties();
    }

    public static String getProperty(String propertyName) {
        return loadProperties().getProperty(propertyName);
    }
}
