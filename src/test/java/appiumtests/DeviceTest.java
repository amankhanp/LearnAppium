package appiumtests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DeviceTest {

    public AndroidDriver<AndroidElement> androidDriver;
    public DesiredCapabilities cap;
    public TestConfig testConfig;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        cap = testConfig.setUp();
        cap.setCapability("unlockType","pin");
        cap.setCapability("unlockKey", "1111");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }

    @Test(priority = 0)
    public void setDeviceLockWithPin() throws InterruptedException {
        Map<String, Object> args = new HashMap<>();
        args.put("command", "locksettings set-pin");
        args.put("args", "--new 1111");
        androidDriver.executeScript("mobile: shell", args);
        Thread.sleep(500);
        androidDriver.lockDevice();
        System.out.println("Device lock pin set successful");
    }

    @Test(priority = 1)
    public void unlockDeviceWithPin() throws InterruptedException {
        if(androidDriver.isDeviceLocked()) {
            //Runtime.getRuntime().exec( "locksettings set-pin --new 1234");
            Map<String, Object> args = new HashMap<>();
            args.put("command", "locksettings set-pin");
            args.put("args", "--old 1111");
            androidDriver.executeScript("mobile: shell", args);
            Thread.sleep(500);
        }
        System.out.println("Device unlocked successful");
        //androidDriver.unlockDevice();
    }

    @Test(priority = 2)
    public void removeDeviceLock() throws InterruptedException {
        if(androidDriver.isDeviceLocked()) {
            Map<String, Object> args = new HashMap<>();
            args.put("command", "locksettings clear");
            args.put("args", "--old 1111");
            androidDriver.executeScript("mobile: shell", args);
            Thread.sleep(500);
        }
        System.out.println("Device pin removed successful");
    }

    /*@Test
    public void unlockDeviceUsingPattern() throws Exception {
        System.out.println("Unlock Device Using pattern");
        Runtime.getRuntime().exec("adb shell input swipe 257 1235 572 1235");
        Thread.sleep(500);
        Runtime.getRuntime().exec("adb shell input swipe 572 1235 260 1564");
        Thread.sleep(500);
        Runtime.getRuntime().exec("adb shell input swipe 260 1564 532 1504");
        Thread.sleep(500);
        System.out.print("Executed Successfully");
    }*/

    @AfterMethod
    public void tearDown() {
        androidDriver.quit();
    }
}
