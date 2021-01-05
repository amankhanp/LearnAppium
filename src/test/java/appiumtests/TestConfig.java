package appiumtests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestConfig {

    static AppiumDriver<MobileElement> appiumDriver;

    public static DesiredCapabilities setUp(){
        DesiredCapabilities cap = new DesiredCapabilities();

        System.out.println("---Setup Device Connection Details---");
        cap.setCapability("deviceName", "nexus6");
        cap.setCapability("udid","emulator-5554");
        cap.setCapability("platformName","Android");
        cap.setCapability("platformVersion","9");
        return cap;
    }

    public static AppiumDriver<MobileElement> startAppium(DesiredCapabilities cap) throws MalformedURLException {
        System.out.println("---Setup Server Detailss---");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        appiumDriver = new AppiumDriver<MobileElement>(url, cap);
        return appiumDriver;
    }
}
