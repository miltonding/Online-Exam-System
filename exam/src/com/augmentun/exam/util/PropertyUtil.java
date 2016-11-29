package com.augmentun.exam.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static Properties properties = null;
    static {
        InputStream  inputStream = null;
        try {
            inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
        public static String getProperty(String key) {
            return properties.getProperty(key);
        }

        public static String getStaticUrl() {
            return properties.getProperty("static_url");
        }

}
