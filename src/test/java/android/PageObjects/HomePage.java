package android.PageObjects;

import BaseClass.AppLaunching;
import BaseClass.DriverClass;
import BaseClass.Util;
//import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.TouchAction;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static BaseClass.AppLaunching.driver;

public class HomePage extends Util {
    emailOTPPage ep = new emailOTPPage();

    public HomePage() {
        PageFactory.initElements(DriverClass.getDriver(), this);
    }


    @FindBy(className = "android.widget.ImageView")
    List<WebElement> countrySelect;



    @FindBy(className = "android.widget.Spinner")
    WebElement countrySpinner;

    @FindBy(className = "android.widget.EditText")
    WebElement enterPhoneNumber;

    @FindBy(id = "android:id/text")
    WebElement messageTexts;

    @FindBy(xpath = "(//android.widget.Button)[10]")
    WebElement submitButton1;

    @FindBy(xpath = "(//android.view.ViewGroup//android.widget.Button)[1]")
    WebElement submitButton2;

    @FindBy(xpath = "(//android.widget.ScrollView//android.view.ViewGroup//android.widget.Button)[7]")
    WebElement submitButton3;

    @FindBy(xpath = "//*[@content-desc='Profile, tab, 3 of 3']")
    WebElement ProfileMenu;


    public void enter_password() throws InterruptedException {
        clickIt(findUsingText("Password"));
        findUsingText("Password").sendKeys("password");
        Thread.sleep(3000);
        android_BackKeyEvent();
        Thread.sleep(2000);
    }







    public void logoutAndExitFromApp() throws InterruptedException {
        Thread.sleep(8000);
        clickIt(ProfileMenu);
        Thread.sleep(2000);
        scrollIntoView("Logout");
        clickIt(findUsingText("Logout"));
        Thread.sleep(2000);
        clickIt(findUsingText("LOG OUT"));
        Thread.sleep(7000);



    }


//    public void confirmation_code() throws InterruptedException {
//        clickIt(findUsingText("CONFIRMATION CODE"));
//        Thread.sleep(7000);
//        clickIt(findUsingText("Enter your confirmation number"));
//        Thread.sleep(3000);
//        findUsingText("Enter your confirmation number").sendKeys("4Q4TXXVRD_D");
//        Thread.sleep(3000);
//        clickIt(findUsingText("Last Name"));
//        Thread.sleep(3000);
//        findUsingText("Last Name").sendKeys("BALRAM KUMAR");
//        android_BackKeyEvent();
//        Thread.sleep(3000);
//        clickIt(findUsingText("CONTINUE"));
//        clickIt(findUsingText("CONTINUE"));
//        Thread.sleep(5000);
//        Assert.assertTrue(findUsingText("For your security, you will now start an ID verification process.").isDisplayed());
//        clickIt(findUsingText("START"));
//        Thread.sleep(5000);
//    }
//
//
//
//
//    public void select_country() throws InterruptedException {
//        Thread.sleep(2000);
//        clickIt(countrySelect.get(1));
//        Thread.sleep(2000);
//        clickIt(countrySpinner);
//        Thread.sleep(2000);
//        scrollIntoView("India (भारत)");
//        Thread.sleep(2000);
//        clickIt(findUsingText("India (भारत)"));
//        Thread.sleep(2000);
//        clickIt(findUsingText("Confirm"));
//        Thread.sleep(2000);
//    }
//
//    public void enter_your_phone_number() throws InterruptedException {
//        clickIt(findUsingText("PHONE NUMBER"));
//        clickIt(findUsingText("Enter your phone number"));
//        Thread.sleep(3000);
//        Thread.sleep(3000);
//        enterPhoneNumber.sendKeys("8319404520");
//        Thread.sleep(3000);
//        enterPhoneNumber.sendKeys("8319404520");
//        Thread.sleep(6000);
//        android_BackKeyEvent();
//    }
//
//    public void submit_at_index_page() throws InterruptedException {
//        clickIt(findUsingText("SUBMIT"));
//        Thread.sleep(6000);
//        clickIt(findUsingText("SUBMIT"));
//        Thread.sleep(6000);
//    }
//
//
//    public void navigate_to_ticket() {
//        scrollNClick(findUsingText("Ticket"));
//    }
//
//    public void navigate_to_amenities() {
//        scrollNClick(findUsingText("Amenities"));
//    }
//
//    public void validate_all_options_in_amenities() {
//
//        scrollNClick(findUsingText("Gym"));
//        scrollNClick(findUsingText("Pool"));
//        scrollNClick(findUsingText("Laundry"));
//        scrollNClick(findUsingText("Test URL"));
//        scrollNClick(findUsingText("Wifi SSID: Hotel"));
//        scrollNClick(findUsingText("adsjh"));
//    }
//
//    public void navigate_to_qr() {
//        scrollNClick(findUsingText("QR"));
//    }
//
//
//
}



