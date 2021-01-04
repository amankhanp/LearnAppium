package appiumtests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {

    //WebDriver webDriver;
    static AppiumDriver<MobileElement> appiumDriver;
    //AndroidDriver androidDriver;

    public static void main(String[] args) {
        try {
            System.out.println("---Connect Appium and Open Calculator Application---");
            openCalculator();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    public static void openCalculator() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();

        System.out.println("---Device Connection Details---");
        cap.setCapability("deviceName", "nexus6");
        cap.setCapability("udid","emulator-5554");
        cap.setCapability("platformName","Android");
        cap.setCapability("platformVersion","9");

        //cap.setCapability("appPackage","com.google.android.calculator");
        //cap.setCapability("appActivity","com.android.calculator2.Calculator");

        System.out.println("---App Package Details---");
        cap.setCapability("appPackage","com.android.calculator2");
        cap.setCapability("appActivity","com.android.calculator2.Calculator");

        System.out.println("---Server Details---");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        appiumDriver = new AppiumDriver<MobileElement>(url, cap);

        System.out.println("-----Application Started-----");

        MobileElement two = appiumDriver.findElement(By.id("com.android.calculator2:id/digit_2"));
        MobileElement plus = appiumDriver.findElement(By.id("com.android.calculator2:id/op_add"));
        MobileElement three = appiumDriver.findElement(By.id("com.android.calculator2:id/digit_3"));
        MobileElement equals = appiumDriver.findElement(By.id("com.android.calculator2:id/eq"));

        two.click();
        plus.click();
        three.click();
        equals.click();
    }
}
