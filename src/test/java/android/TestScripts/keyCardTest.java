package android.TestScripts;

import BaseClass.AppLaunching;
import android.PageObjects.keyCardPage;
import org.testng.annotations.Test;

public class keyCardTest extends AppLaunching {

    @Test(priority = 2)
    public void validate_keycard_while_bluetooth_is_off() throws InterruptedException {
       keyCardPage kp = new keyCardPage();
        kp.clickOnBluetoothMenuAfterOpeningNotifications();
        kp.after_turning_bluetooth_off();

    }

    @Test(priority = 1)
    public void validate_keycard_while_bluetooth_is_on() throws InterruptedException {
        keyCardPage kp = new keyCardPage();
        kp.clickOnKeyCardMenu();
        kp.clickOnBluetoothMenuAfterOpeningNotifications();
        kp.after_turning_bluetooth_on();

    }



}
