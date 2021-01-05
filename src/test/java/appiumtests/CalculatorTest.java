package appiumtests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class CalculatorTest {

    public static String CALC_KEY_TWO = "com.android.calculator2:id/digit_2";
    public static String CALC_KEY_THREE = "com.android.calculator2:id/digit_3";
    public static String CALC_KEY_PLUS = "com.android.calculator2:id/op_add";
    public static String CALC_KEY_EQUALS = "com.android.calculator2:id/eq";
    public static String CALC_KEY_RESULT = "com.android.calculator2:id/result";

    public DesiredCapabilities cap;
    public TestConfig testConfig;
    AppiumDriver<MobileElement> appiumDriver;

    public static void main(String[] args) {
        CalculatorTest calculatorTest = new CalculatorTest();
        System.out.println("---Connect Appium and Open Calculator Application---");
        try {
            Assert.assertEquals("Calculated value result is not same",5, calculatorTest.openCalculator());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println("---Validation Successful---");
    }

    public int openCalculator() throws MalformedURLException {
        appiumDriver = setupAppDetails();
        MobileElement two = appiumDriver.findElement(By.id(CALC_KEY_TWO));
        MobileElement plus = appiumDriver.findElement(By.id(CALC_KEY_PLUS));
        MobileElement three = appiumDriver.findElement(By.id(CALC_KEY_THREE));
        MobileElement equals = appiumDriver.findElement(By.id(CALC_KEY_EQUALS));
        MobileElement result = appiumDriver.findElement(By.id(CALC_KEY_RESULT));

        two.click();
        plus.click();
        three.click();
        equals.click();
        return Integer.parseInt(result.getText());
    }

    public AppiumDriver<MobileElement> setupAppDetails() throws MalformedURLException {
        cap = testConfig.setUp();
        System.out.println("---Setup Package Details---");
        cap.setCapability("appPackage","com.android.calculator2");
        cap.setCapability("appActivity","com.android.calculator2.Calculator");

        System.out.println("-----Application Started-----");
        appiumDriver = testConfig.startAppium(cap);
        return appiumDriver;
    }
}
