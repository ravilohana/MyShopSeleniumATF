package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {




    private LoggerUtility() {}

    public static Logger getLogger(Class<?> className){
        Logger logger = null;
        if (logger == null){
            logger = LogManager.getLogger(className);
        }
        return logger;
    }
}
