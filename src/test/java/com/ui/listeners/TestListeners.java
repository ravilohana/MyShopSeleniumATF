package com.ui.listeners;

import com.aventstack.extentreports.Status;
import com.ui.test.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListeners implements ITestListener {

    Logger logger = LoggerUtility.getLogger(this.getClass());


    @Override
    public void onTestStart(ITestResult result) {

        logger.info(result.getClass().getName());
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());

        logger.info(Arrays.toString(result.getMethod().getGroups()));

        ExtentReporterUtility.createExtentTest(result.getClass().getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " " + "PASSED");
        ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName() + " " + "FAILED");
        logger.error(result.getThrowable().getMessage());
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
        ExtentReporterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());

        Object testClass = result.getInstance();

        BrowserUtility browserUtility = ((TestBase) testClass).getInstance();
        logger.info("Capturing the screenshot for failed test case.");
        String screenShotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());

        ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenShotPath);

        logger.info("Screenshot Path: {} ========> ",screenShotPath);

        logger.info("Attaching the screenshot for failed test in extent HTML report.");


    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
        ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started....");
        ExtentReporterUtility.setUpSparkReports("extent_report.html");

    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed....");
        ExtentReporterUtility.flushReport();
    }


}
