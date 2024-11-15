package com.utility;

import com.constants.Env;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {

    private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
    private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<>();

    public static WebDriver initializeLambdaTestSession(String browser,String testName){

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "130");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", PropertiesUtility.readProperty(Env.QA,"LT_USERNAME"));
        ltOptions.put("accessKey", PropertiesUtility.readProperty(Env.QA,"LT_ACCESS_KEY"));
        ltOptions.put("resolution", "1920x1080");
        ltOptions.put("platformName", "Windows 11");
        ltOptions.put("seCdp", true);
        ltOptions.put("build", "MyShopAssignment_01");
        ltOptions.put("name", testName);
        ltOptions.put("project", "MyShopApp");
        ltOptions.put("selenium_version", "4.26.0");
        capabilities.setCapability("LT:Options", ltOptions);

        capabilitiesLocal.set(capabilities);
        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driverLocal.set(driver);

        return driverLocal.get();

    }


    public static void quitSession(){
        if(driverLocal !=null){
            driverLocal.get().quit();
        }
    }
}
