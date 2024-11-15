package com.ui.pages;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BrowserUtility {

    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    private static final By Sign_In_Btn_LOCATOR = By.id("SubmitLogin");

    Logger logger  = LoggerUtility.getLogger(this.getClass());


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage doLoginWith(String username,String password){
        enterText(EMAIL_TEXT_BOX_LOCATOR,username);
        logger.info("text is entered {}", username);
        enterText(PASSWORD_TEXT_BOX_LOCATOR,password);
        logger.info("text is entered {}", password);
        clickOn(Sign_In_Btn_LOCATOR);
        logger.info("Sign In button is clicked.");
        return new MyAccountPage(getDriver());
    }


}
