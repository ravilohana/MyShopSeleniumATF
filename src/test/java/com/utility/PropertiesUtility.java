package com.utility;

import com.constants.Env;


import java.io.File;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

    public static String readProperty(Env env,String propertyName) {

        File file = new File(System.getProperty("user.dir") + "\\config\\" + env  + ".properties");
        FileReader fileReader = null;
        Properties properties =  new Properties();
        try {
            fileReader = new FileReader(file);
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(propertyName.toUpperCase());


    }
}
