package android.TestScripts;

import BaseClass.AppLaunching;
import android.PageObjects.Payment;
import android.PageObjects.paymentShift4;
import org.testng.annotations.Test;

public class paymentShift4Test extends AppLaunching {

    @Test(priority = 1)
    public void paymentShift4test() throws InterruptedException {
        Payment p = new Payment();
        p.navigateToPaymentForm();
        paymentShift4 ps = new paymentShift4();
        ps.fillForm();
    }

}
