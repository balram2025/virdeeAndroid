package android.TestScripts;

import BaseClass.AppLaunching;
import android.PageObjects.keyCardPage;
import android.PageObjects.servicesPage;
import org.testng.annotations.Test;

public class servicesTest extends AppLaunching {

    @Test(priority = 1)
    public void validate_services1_menu_in_services() throws InterruptedException {
        servicesPage sp = new servicesPage();
        sp.navigate_to_services();
        sp.validate_services1_menu();

    }

    @Test(priority = 2)
    public void validate_earlyCheckIn_menu_in_services() throws InterruptedException {
        servicesPage sp = new servicesPage();
        sp.early_checkin_menu();

    }

    @Test(priority = 3)
    public void validate_lateCheckIn_menu_in_services() throws InterruptedException {
        servicesPage sp = new servicesPage();
        sp.late_checkin_menu();
    }

    @Test(priority = 4)
    public void validate_houseKeeping_menu_in_services() throws InterruptedException {
        servicesPage sp = new servicesPage();
        sp.housekeeping_menu();
    }

    @Test(priority = 5)
    public void validate_pet_menu_in_services() throws InterruptedException {
        servicesPage sp = new servicesPage();
        sp.pet_menu();
    }

    @Test(priority = 6)
    public void validate_additionalHousekeeping_menu_in_services() throws InterruptedException {
        servicesPage sp = new servicesPage();
        sp.additional_housekeeping();
    }
}
