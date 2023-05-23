package android.PageObjects;

import BaseClass.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static BaseClass.AppLaunching.driver;

public class regIdClass extends Util {

    public regIdClass(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//android.widget.TextView)[2]")
    WebElement regId;


    public void clickOnRegId() throws InterruptedException {
        sleep(5);
        clickIt(regId);
       sleep(5);
       clickIt(regId);
       sleep(3);
    }



}
