package com.ui.listeners;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtility;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {

    // read from properties file
    //    private static final int MAX_NUMBER_OF_ATTEMPTS =Integer.parseInt(PropertiesUtility.readProperty(Env.QA,"MAX_NUMBER_OF_ATTEMPTS"));

    // read max number of attempts from json file
    private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMax_number_of_attempts();

    private static int currentAttempts = 1;


    @Override
    public boolean retry(ITestResult result) {
        if (currentAttempts <= MAX_NUMBER_OF_ATTEMPTS) {
            currentAttempts++;
            return true;
        }
        return false;
    }
}
