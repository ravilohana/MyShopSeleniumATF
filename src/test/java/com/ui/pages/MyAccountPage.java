package com.ui.pages;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BrowserUtility {

    private static final By USER_NAME_LOCATOR = By.cssSelector("a.account");
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName(){
        logger.info("Getting the visible text of  logged account of user");
        return getVisibleText(USER_NAME_LOCATOR);
    }
}
