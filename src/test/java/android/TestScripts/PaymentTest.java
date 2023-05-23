package android.TestScripts;

import BaseClass.AppLaunching;
import android.PageObjects.HomePage;
import android.PageObjects.Payment;
import android.PageObjects.emailOTPPage;
import org.testng.annotations.Test;

public class PaymentTest  extends AppLaunching {

    @Test
    public void verify_Payment_part() throws InterruptedException {
        Payment ep = new Payment();
        ep.Payment();
    }
}
