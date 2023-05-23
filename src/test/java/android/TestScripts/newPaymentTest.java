package android.TestScripts;

import android.PageObjects.Payment;
import android.PageObjects.mobileWebPage;
import android.PageObjects.newPaymentPage;
import org.testng.annotations.Test;

public class newPaymentTest {

    @Test(priority = 1)
    public void enterNewPaymentDetails() throws InterruptedException {
        Payment p = new Payment();
        p.navigateToPaymentForm();
        newPaymentPage mwp = new newPaymentPage();
        mwp.enterDetails();
    }

}
