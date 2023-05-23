package BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppLaunching {

    public static AppiumDriver<MobileElement> driver;
    public static DesiredCapabilities dc;

    @SneakyThrows
    @BeforeSuite
    public static void Android_LaunchApp() {

ExtentManager.setExtent();
        dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("deviceName", "28cd0d7");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.virdee.dev.mobile_app");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.virdee.mobile_app.MainActivity");
        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        dc.setCapability(MobileCapabilityType.APP, new File("src/main/resources/APK/Virdee.apk").getAbsolutePath());

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        DriverClass.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Thread.sleep(15000);

    }

    @BeforeClass
    public void beforeClass() {


    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method) {

    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) throws IOException {

//        if(result.FAILURE==result.getStatus())
//        {
//            Util.getScreenShotMethod(AppLaunching.driver,result.getName());
//        }

    }


    @SneakyThrows
    public static void IOS_LaunchApp() {
        dc = new DesiredCapabilities();
        dc.setCapability("platformName", "IOS");
        dc.setCapability("deviceName", "I phone X");
        dc.setCapability("automationName", "XCUITest");
        dc.setCapability("platformVersion", "13.0");
        dc.setCapability("bundleId", "com.example.apple-samplecode-.UIcatalog");
        driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        DriverClass.setDriver(driver);

    }

    @AfterSuite
    public static void closeApp() {
        ExtentManager.endReport();
        driver.quit();
    }

}
