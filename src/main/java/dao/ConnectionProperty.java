package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperty {
    public static final String CONFIG_NAME = "config.properties";
    public static final Properties GLOBAL_CONFIG = new Properties();

    public ConnectionProperty() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_NAME)) {
            if (input == null) {
                System.out.println("❌ Файл " + CONFIG_NAME + " не найден в resources");
                return;
            }
            GLOBAL_CONFIG.load(input);
            System.out.println("✅ Файл config.properties загружен");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String property) {
        return GLOBAL_CONFIG.getProperty(property);
    }
}