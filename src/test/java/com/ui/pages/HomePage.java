package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;


import com.utility.BrowserUtility;


import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public final class HomePage extends BrowserUtility {

    Logger logger = LoggerUtility.getLogger(this.getClass());



    private static final By SIGN_IN_LINK_LOCATOR =By.xpath("//a[contains(text(),'Sign')]");

    public HomePage(Browser browserName,boolean isHeadless) {
        super(browserName,isHeadless);
//        goToWebsite(readProperty(QA,"url")); // read property from properties file
        maximizeWindow();
        goToWebsite(JSONUtility.readJSON(QA).getUrl()); // read url from JSON file

    }

    public HomePage(WebDriver driver){
        super(driver);
        maximizeWindow();
        goToWebsite(JSONUtility.readJSON(QA).getUrl()); // read url from JSON file
    }

    public LoginPage goToLoginPage(){
        clickOn(SIGN_IN_LINK_LOCATOR);
        logger.info("Sign In link is clicked.");
        return new LoginPage(getDriver());
    }

}
