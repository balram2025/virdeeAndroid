package android.TestScripts;

import android.PageObjects.Payment;
import android.PageObjects.signatureClass;
import org.testng.annotations.Test;

public class signatureTest {

    @Test(priority = 1)
    public void signatureTest() throws InterruptedException {
        signatureClass sc = new signatureClass();
        sc.enter_signature_and_proceed();
    }
}