package BaseClass;

import org.openqa.selenium.WebDriver;


public class DriverClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver()
    {
        return driver.get();
    }
    public static void setDriver(WebDriver Driver){
        driver.set(Driver);
    }


}
