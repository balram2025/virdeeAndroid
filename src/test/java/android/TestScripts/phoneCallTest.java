package android.TestScripts;

import android.PageObjects.phoneCallPage;
import org.testng.annotations.Test;

public class phoneCallTest {


    @Test(priority = 1)
    public void ToCheckIfUserCanCallAndEndItSuccessFully() throws InterruptedException {
        phoneCallPage pc = new phoneCallPage();
        pc.ToCheckIfUserCanMakeCallAndExitIt();
    }

    @Test(priority = 2)
    public void ToCheckIfUserCanVideoCallAndEndItSuccessFully() throws InterruptedException {
        phoneCallPage pc = new phoneCallPage();
        pc.ToCheckIfUserCanMakeVideoCallAndExitIt();
    }

    @Test(priority = 3,enabled = false)
    public void ToCheckIfUserCanNavigateToWhatsappSuccessFully() throws InterruptedException {
        phoneCallPage pc = new phoneCallPage();
        pc.ToCheckIfUserCanNavigateToWhatsapp();
    }
}
