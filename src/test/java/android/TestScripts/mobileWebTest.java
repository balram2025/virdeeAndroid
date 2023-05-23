package android.TestScripts;

import android.PageObjects.mobileWebPage;
import org.testng.annotations.Test;

public class mobileWebTest {

    @Test(priority = 1)
    public void verifyMobileWebNavigationToApp() throws InterruptedException {
        mobileWebPage mwp = new mobileWebPage();
        mwp.navigateToDepLink();

    }
}
