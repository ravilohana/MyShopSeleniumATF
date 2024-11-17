package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {

    private static  ThreadLocal<WebDriver> thlDriver = new ThreadLocal<>();
    Logger logger = LoggerUtility.getLogger(this.getClass());


    public BrowserUtility(WebDriver driver) {
        super();
        thlDriver.set(driver);
    }

    public WebDriver getDriver() {
        return thlDriver.get();
    }


    // BrowserUtility Constructor with browser only
    public BrowserUtility(Browser browserName) {
        logger.info(" {} Web browser is launched.", browserName);
        switch (browserName) {
            case CHROME:
                thlDriver.set(new ChromeDriver());
                break;
            case FIREFOX:
                thlDriver.set(new FirefoxDriver());
                break;
            case MS_EDGE:
                thlDriver.set(new EdgeDriver());
                break;
            default:
                System.err.println("Invalid browser name... please select chrome,firefox,msEdge.." + browserName);

        }
    }

    // BrowserUtility Constructor with browser and  headless mode
    public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info(" {} Web browser is launched.", browserName);
        switch (browserName) {
            case CHROME:
                if (isHeadless) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless=old");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    thlDriver.set(new ChromeDriver(chromeOptions));
                } else {
                    thlDriver.set(new ChromeDriver());
                }
                break;
            case FIREFOX:

                if (isHeadless) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    thlDriver.set(new FirefoxDriver(firefoxOptions));

                } else {
                    thlDriver.set(new FirefoxDriver());
                }
                break;
            case MS_EDGE:
                if (isHeadless) {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless=old");
                    edgeOptions.addArguments("disbale-gpu");
                    thlDriver.set(new EdgeDriver(edgeOptions));
                } else {
                    thlDriver.set(new EdgeDriver());
                }

                break;
            default:
                System.err.println("Invalid browser name... please select chrome,firefox,msEdge.." + browserName);

        }
    }


    public void goToWebsite(String url) {
        thlDriver.get().get(url);
        logger.info(" {} Web app url entered ", url);
    }

    public void maximizeWindow() {
        logger.info("Maximize the browser windows");
        getDriver().manage().window().maximize();
    }

    public void clickOn(By locator) {
        WebElement element = getDriver().findElement(locator);
        logger.info("{} clicking on element ", element);
        element.click();
        logger.info("{} element is clicked.", element);
    }

    public void enterText(By locator, String textToEnter) {
        WebElement element = getDriver().findElement(locator);
        logger.info("entering text {}", textToEnter);
        element.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator) {
        WebElement element = getDriver().findElement(locator);
        logger.info("getting  visible text of element {} and text is {}", element, element.getText());
        return element.getText();
    }


    public String takeScreenShot(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String timeStamp = format.format(date);
        String screenshotFolderName = "screenshots";
        String screenshotFileName = name + "_" + timeStamp + ".PNG";
        String path = getPath(screenshotFolderName,screenshotFileName);
        File screenShotData = screenshot.getScreenshotAs(OutputType.FILE);
        File screenShotFile = new File(path);

        try {
            FileUtils.copyFile(screenShotData, screenShotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("PATH of screenhots: ----------> {}", path);

        return path;

    }


    public void quitDriver() {
        getDriver().quit();
    }

    // get path

    public String getPath(String folderName,String fileName){
        Path currDirWork = Paths.get("").toAbsolutePath();
        Path filePath = currDirWork.resolve(Paths.get(folderName,fileName));
        return filePath.toString();

    }

}
