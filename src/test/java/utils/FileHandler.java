package utils;

import java.io.FileReader;
import java.util.Properties;

public class FileHandler {
    private static final String configurationFile = "src/test/resources/configurationFile.properties";
    private static final Properties properties = new Properties();

    private static Properties loadFile() {
        try {
            FileReader reader = new FileReader(configurationFile);
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty(String property) {
        Properties properties = FileHandler.loadFile();
        return properties.getProperty(property);
    }
}
