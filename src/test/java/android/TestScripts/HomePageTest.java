package android.TestScripts;

import BaseClass.AppLaunching;
import android.PageObjects.HomePage;
import org.testng.annotations.Test;

public class HomePageTest extends AppLaunching {


    @Test(priority = 1)
    public void verifyAllBeforeSignInPageTest() throws InterruptedException {
        HomePage sp = new HomePage();
        sp.logoutAndExitFromApp();
//        sp.confirmation_code();
    }





}
