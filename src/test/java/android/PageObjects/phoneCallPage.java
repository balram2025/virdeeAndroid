package android.PageObjects;

import BaseClass.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static BaseClass.AppLaunching.driver;

public class phoneCallPage extends Util {

    public phoneCallPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//android.widget.TextView)[21]")
    WebElement callingIconHomePage;

    @FindBy(xpath = "//android.widget.TextView[@text='Call']//preceding-sibling::android.view.ViewGroup")
    WebElement callingButton;

    @FindBy(xpath = "//android.widget.TextView[@text='End']//preceding-sibling::android.view.ViewGroup")
    WebElement endCallButton;

    @FindBy(xpath = "(//android.widget.TextView)[1]")
    WebElement cancelIconCallingF;

    @FindBy(id = "//android:id/button1")
    WebElement YES_to_cancel_the_call;


    public void ToCheckIfUserCanMakeCallAndExitIt() throws InterruptedException {
        clickIt(callingIconHomePage);
        sleep(3);
        clickIt(callingButton);
        sleep(3);
        clickIt(endCallButton);
        sleep(3);
        clickIt(findUsingText("YES"));
        sleep(2);
    }

    public void ToCheckIfUserCanMakeVideoCallAndExitIt(){
        clickIt(callingIconHomePage);
        sleep(3);
        clickIt(findUsingText("Video Call"));
        sleep(3);
        clickIt(endCallButton);
        sleep(3);
        clickIt(cancelIconCallingF);
        sleep(3);
        clickIt(findUsingText("YES"));
        sleep(2);


    }

    public void ToCheckIfUserCanNavigateToWhatsapp(){
        clickIt(callingIconHomePage);
        sleep(3);
        clickIt(findUsingText("Whatsapp"));
        sleep(7);
        android_BackKeyEvent();
        sleep(4);
        clickIt(cancelIconCallingF);


    }
    }




