package com.ui.test;

import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listeners.TestListeners.class)
public class LoginTest extends TestBase{

    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Test(description = "Login with valid credentials DDT - JSON",
            groups = {"smoke", "sanity", "e2e"},
            dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,
            dataProvider = "LoginTestDataProvider"
    )
    public void loginTestJSONCredentials(User user) {
        logger.info("Performing logging of the user");
        String actualUserName = homePage.goToLoginPage()
                .doLoginWith(user.getEmailAddress(), user.getPassword())
                .getUserName();

        logger.info("User logged into his/her account by entering {} and {}" , user.getEmailAddress(), user.getPassword());
        String expectedUserName = "Jack Dock";
        Assert.assertEquals(actualUserName, expectedUserName);

        logger.info("Assertion for checking {} and {} ", actualUserName,expectedUserName);
        logger.info("Assertion is passed");
    }

/*
    @Test(description = "Login with valid credentials DDT - CSV",
            groups = {"smoke", "sanity", "e2e"},
            dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,
            dataProvider = "LoginTestCSVDataProvider"
    )
    public void loginTestCSVCredentials(User user) {
        logger.info("Performing logging of the user");
        String actualUserName = homePage.goToLoginPage()
                .doLoginWith(user.getEmailAddress(), user.getPassword())
                .getUserName();

        logger.info("User logged into his/her account by entering {} and {}" , user.getEmailAddress(), user.getPassword());
        String expectedUserName = "Jack Dock";
        logger.info("Assertion for checking {} and {} ", actualUserName,expectedUserName);
        Assert.assertEquals(actualUserName, expectedUserName);

        logger.info("Assertion is passed");
    }

    @Test(description = "Login with valid credentials DDT - XLSX",
            groups = {"smoke", "sanity", "e2e"},
            dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,
            dataProvider = "LoginTestXLSXDataProvider"
    )
    public void loginTestXLSXCredentials(User user) {

        logger.info("Performing logging of the user");
        String actualUserName = homePage.goToLoginPage()
                .doLoginWith(user.getEmailAddress(), user.getPassword())
                .getUserName();

        logger.info("User logged into his/her account by entering {} and {}" , user.getEmailAddress(), user.getPassword());

        String expectedUserName = "Jack Dock";
        logger.info("Assertion for checking {} and {} ", actualUserName,expectedUserName);
        Assert.assertEquals(actualUserName, expectedUserName);
        logger.info("Assertion is passed");


    }



*/

}
