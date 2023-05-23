package android.PageObjects;

import BaseClass.AppLaunching;
import BaseClass.Util;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static BaseClass.AppLaunching.driver;

public class signatureClass extends Util {
    servicesPage sp = new servicesPage();

    public signatureClass() {
        PageFactory.initElements(driver, this);
    }



    public void enter_signature_and_proceed() throws InterruptedException {
        clickIt(findUsingText("START"));
        Thread.sleep(10000);
        clickIt(findUsingText("CONTINUE"));
        Thread.sleep(17000);
    }

}