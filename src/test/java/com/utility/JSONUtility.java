package com.utility;


import com.google.gson.Gson;
import com.constants.Env;
import com.ui.pojo.Config;
import com.ui.pojo.Environments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONUtility {

    public static Environments readJSON(Env env) {

        Gson gson = new Gson();
        File jsonFile = new File(System.getProperty("user.dir") + "\\config\\config.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Config config = gson.fromJson(fileReader, Config.class);
        return config.getEnvironments().get(String.valueOf(env));

    }
}
