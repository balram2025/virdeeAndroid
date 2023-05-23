package android.TestScripts;

import android.PageObjects.AmenitiesPage;
import android.PageObjects.AmenitiesPage;
import org.testng.annotations.Test;

public class AmenitiesTest {

    @Test(priority = 1)
    public void check_if_Amenities_page_is_displayed() throws InterruptedException {
        AmenitiesPage ap = new AmenitiesPage();
        ap.navigateToAmenities();
    }
}
