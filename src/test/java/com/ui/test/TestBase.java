package com.ui.test;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {

    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    private boolean isLambdaTest;


    @Parameters({"browser","isLambdaTest","isHeadless"})
    @BeforeMethod(description = "Home page of web application loads")
    public void setUp(@Optional("chrome") String browser,
                      @Optional("false") boolean isLambdaTest,
                      @Optional("true") boolean isHeadless,
                      ITestResult result) {

        WebDriver lambdaDriver;
        // test will run on lambda test cloud
        this.isLambdaTest = isLambdaTest;
        if (isLambdaTest) {
           lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        } else {

            // running test in local machine
            logger.info("Load the Home page of the web app MyShop");
            homePage = new HomePage(Browser.valueOf("chrome".toUpperCase()), isHeadless);

        }


    }

    public BrowserUtility getInstance() {
        return homePage;
    }

    @AfterMethod(description = "Quiting the browser session")
    public void tearDown() {

        if (isLambdaTest) {
            logger.info("Quit the lambda driver and its session");
            LambdaTestUtility.quitSession();
        } else {

            logger.info("Quit the driver and session");
            homePage.quitDriver();
        }

    }


}
