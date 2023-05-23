package android.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static BaseClass.AppLaunching.driver;

public class StayPage {

    public StayPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//android.widget.TextView)[2]")
    WebElement regId;

    public void validate_reservation_id() throws InterruptedException {
        Thread.sleep(8000);
        regId.click();
        Thread.sleep(5000);
    }



}
