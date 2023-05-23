package android.PageObjects;

import BaseClass.Util;
import org.openqa.selenium.support.PageFactory;

import static BaseClass.AppLaunching.driver;

public class AmenitiesPage extends Util {


    public AmenitiesPage(){
        PageFactory.initElements(driver,this);
    }

    public void navigateToAmenities() throws InterruptedException {
        clickIt(findUsingText("Amenities"));
        sleep(3);
        clickIt(findUsingText("Amenities"));
        sleep(2);
        clickIt(findUsingText("Gym"));
        sleep(2);
        clickIt(findUsingText("Pool"));
        sleep(2);
        scrollDown();
//        scrollIntoView("Laundry");
//        sleep(8);
//        scrollIntoView("Test URL");
        sleep(2);
        scrollDown();
//        scrollIntoView("Wifi");
        sleep(2);
        scrollDown();

        scrollIntoView("Community");
        sleep(3);

        scrollIntoView("Amenities");
        sleep(10);
    }


}
