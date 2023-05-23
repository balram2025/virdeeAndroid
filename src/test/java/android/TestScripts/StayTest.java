package android.TestScripts;

import android.PageObjects.StayPage;
import org.testng.annotations.Test;

public class StayTest {

    @Test(priority = 1)
    public void check_if_reservation_id_is_displayed() throws InterruptedException {
        StayPage sp = new StayPage();
        sp.validate_reservation_id();
    }
}
