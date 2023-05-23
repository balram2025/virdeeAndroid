package android.PageObjects;

import BaseClass.AppLaunching;
import BaseClass.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class servicesPage extends Util {

    public servicesPage(){
        PageFactory.initElements(AppLaunching.driver,this);
    }

    @FindBy(xpath = "//android.widget.EditText")
    WebElement comment;

    @FindBy(xpath = "//android.widget.Button")
    WebElement sendButton;

    @FindBy(xpath = "(//android.widget.ImageView)[4]")
    WebElement lateCheckOut;

    public void navigate_to_services() throws InterruptedException {
        sleep(5);
        clickIt(findUsingText("Services"));
        Thread.sleep(5000);
    }

    public void validate_services1_menu() throws InterruptedException {
        clickIt(findUsingText("Service1"));
        Thread.sleep(4000);
        findUsingText("Time").click();
        Thread.sleep(2000);
        findUsingText("10:00 am").click();
        Thread.sleep(2000);
       comment.sendKeys("Hello");
        Thread.sleep(2000);
        sendButton.click();
        Thread.sleep(5000);
         clickIt(findUsingText("Success"));

    }

    public void early_checkin_menu() throws InterruptedException {
        clickIt(findUsingText("Early Check in"));
        Thread.sleep(4000);
        scrollIntoView("SEND");
        Thread.sleep(2000);
        findUsingText("Time").click();
        Thread.sleep(2000);
        findUsingText("10:00 am").click();
        Thread.sleep(2000);
        comment.sendKeys("Hello");
        Thread.sleep(2000);
        sendButton.click();
        Thread.sleep(5000);
        clickIt(findUsingText("Success"));
    }

    public void late_checkin_menu() throws InterruptedException {
        clickIt(lateCheckOut);
        Thread.sleep(4000);
        findUsingText("Time").click();
        Thread.sleep(2000);
        findUsingText("10:00 am").click();
        Thread.sleep(2000);
        comment.sendKeys("Hello");
        Thread.sleep(2000);
        sendButton.click();
        Thread.sleep(5000);
        clickIt(findUsingText("Success"));
    }



    public void housekeeping_menu() throws InterruptedException {
        scrollIntoView("Additional Housekeeping");
        clickIt(findUsingText("Housekeeping"));
        Thread.sleep(4000);
        findUsingText("Time").click();
        Thread.sleep(2000);
        findUsingText("10:00 am").click();
        Thread.sleep(2000);
        sendButton.click();
        Thread.sleep(5000);
        clickIt(findUsingText("Success"));
    }

    public void pet_menu() throws InterruptedException {
        scrollIntoView("Additional Housekeeping");
        clickIt(findUsingText("Pet Fees"));
        Thread.sleep(4000);
        comment.sendKeys("Hello");
        Thread.sleep(2000);
        sendButton.click();
        Thread.sleep(5000);
        clickIt(findUsingText("Success"));
    }

    public void additional_housekeeping() throws InterruptedException {
        scrollIntoView("Additional Housekeeping");
        clickIt(findUsingText("Additional Housekeeping"));
        Thread.sleep(4000);
        findUsingText("Time").click();
        Thread.sleep(2000);
        findUsingText("10:00 am").click();
        Thread.sleep(2000);
        sendButton.click();
        Thread.sleep(5000);
        clickIt(findUsingText("Success"));
    }




}
