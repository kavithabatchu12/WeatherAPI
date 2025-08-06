package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties;

    static {
        properties = new Properties();
        // Load the properties file from the classpath
        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getApiKey() {
        return properties.getProperty("api.key");
    }

    public static String getBaseUri() {
        return properties.getProperty("api.base.uri");
    }
}