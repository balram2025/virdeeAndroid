package android.TestScripts;

import BaseClass.AppLaunching;
import android.PageObjects.emailOTPPage;
import org.testng.annotations.Test;

public class emaILotppTest extends AppLaunching {

    @Test(priority = 1)
    public void ToCheckIfUserCanLoginSuccessfullyUsingEmailAddress() throws InterruptedException {
        emailOTPPage ep = new emailOTPPage();
        ep.loginSuccessfullyUsingEmailAddress();
    }
}
