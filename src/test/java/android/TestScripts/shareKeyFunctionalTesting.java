package android.TestScripts;

import BaseClass.AppLaunching;
import android.PageObjects.keyCard1Page;
import org.testng.annotations.Test;

public class shareKeyFunctionalTesting extends AppLaunching
{

    @Test(priority = 1)
    public void shareKeyFunctionalTest() throws InterruptedException {
        keyCard1Page kp = new keyCard1Page();
        kp.shareKeyFunctionality();
    }
}
