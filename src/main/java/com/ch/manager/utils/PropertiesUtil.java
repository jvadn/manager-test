package com.ch.manager.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties getProperties(String properties) {
        Properties pro = new Properties();
        try {
            pro.load(new PropertiesUtil().getClass().getResourceAsStream("/" + properties));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pro;
    }

}
