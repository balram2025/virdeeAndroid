package android.PageObjects;

import BaseClass.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static BaseClass.AppLaunching.driver;

public class keyCardPage extends Util {

    public keyCardPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc='Bluetooth,On,Button']")
    WebElement bluetoothOnDefault;

    @FindBy(xpath = "(//android.widget.LinearLayout)[18]")
    WebElement bluetoothOffDefault;

    @FindBy(xpath = "(//android.widget.TextView)[2]")
    WebElement regId;

    public void clickOnKeyCardMenu() throws InterruptedException {
        clickIt(findUsingText("Keycard"));
    }



    public void bluetooth_off_keycard_functionality() throws InterruptedException {
        Thread.sleep(10000);
        regId.click();
        Thread.sleep(19000);
        clickIt(findUsingText("Keycard"));
        Thread.sleep(2000);
        clickIt(findUsingText("Keycard"));
        Thread.sleep(6000);
        android_openNotifications();
        Thread.sleep(4000);
        clickIt(bluetoothOffDefault);
        Thread.sleep(10000);
        android_BackKeyEvent();
        Thread.sleep(4000);
    }

    public void after_turning_bluetooth_off() throws InterruptedException {
        Thread.sleep(6000);
        clickIt(findUsingText("Tap to use"));
        Thread.sleep(3000);

    }

    public void clickOnBluetoothMenuAfterOpeningNotifications() throws InterruptedException {
        Thread.sleep(18000);
        android_openNotifications();
        sleep(4);
        clickIt(bluetoothOffDefault);
        sleep(4);
        android_BackKeyEvent();
    }

    public void after_turning_bluetooth_on() throws InterruptedException {
        Thread.sleep(18000);
        findUsingText("Tap to use").click();
        tapByCoordinates(715,2644);
//        clickIt(findUsingText("No"));
        Thread.sleep(1000);
    }

    public void navigate_to_keycard() {
        scrollNClick(findUsingText("Keycard"));
    }


    HomePage hp = new HomePage();






}
