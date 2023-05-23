package android.TestScripts;

import BaseClass.AppLaunching;
import android.PageObjects.AmenitiesPage;
import android.PageObjects.deepLinkPage;
import org.testng.annotations.Test;

public class DeepLinkTest extends AppLaunching {
    @Test(priority = 1)
    public void checkIfDeppLinkIsOpening() throws InterruptedException {
        deepLinkPage dp = new deepLinkPage();
        dp.navigateToDepLink();
    }
}
