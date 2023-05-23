package android.TestScripts;

import BaseClass.AppLaunching;
import android.PageObjects.regIdClass;
import org.testng.annotations.Test;

public class regIdTest extends AppLaunching {

    @Test(priority = 1)
    public void verifyRegistrationId() throws InterruptedException {
        regIdClass ri = new regIdClass();
        ri.clickOnRegId();
    }

}
