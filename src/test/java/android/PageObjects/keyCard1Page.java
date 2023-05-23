package android.PageObjects;

import BaseClass.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static BaseClass.AppLaunching.driver;

public class keyCard1Page extends Util {

    public keyCard1Page(){
        PageFactory.initElements(driver,this);
    }

    HomePage hp = new HomePage();


    @FindBy(xpath = "//android.widget.EditText[@text='First Name']")
    WebElement firstNameKeyCard;

    @FindBy(xpath = "//android.widget.EditText[@text='Last Name']")
    WebElement lastNameKeyCard;

    @FindBy(xpath = "//android.widget.EditText[@text='Email']")
    WebElement emailKeyCard;

    @FindBy(xpath = "(//android.widget.ImageView)[3]")
    WebElement countryDefaultUS;

    @FindBy(className = "android.widget.Spinner")
    WebElement countrySpinner;

    @FindBy(xpath = "//android.widget.EditText[@content-desc='Telephone input']")
    WebElement phoneInput;

    @FindBy(xpath = "//android.widget.TextView[@text='Go back to mobile key']")
    WebElement shareKeyError;


    public void shareKeyFunctionality() throws InterruptedException {
        clickIt(findUsingText("Keycard"));
        Thread.sleep(12000);
//        clickIt(findUsingText("share key"));
//        Thread.sleep(5000);
        try {
            clickIt(findUsingText("Share key"));
        }catch (Exception e){
            JSClick(findUsingText("Share key"));
        }
        Thread.sleep(3000);
        clickIt(findUsingText("Continue"));
        Thread.sleep(3000);
        clickIt(findUsingText("Share key with guest"));
        fillDetails();
        selectCountryKeyCard();
        phoneInput.sendKeys("918319404520");
        clickIt(findUsingText("Share key with guest"));
        Thread.sleep(5000);
        clickIt(findUsingText("Ok"));
        Thread.sleep(3000);
    }

    public void selectCountryKeyCard() throws InterruptedException {
        countryDefaultUS.click();
        countryDefaultUS.click();
        Thread.sleep(2000);
        clickIt(countrySpinner);
        Thread.sleep(2000);
        scrollIntoView("India (भारत)");
        Thread.sleep(2000);
        clickIt(findUsingText("India (भारत)"));
        Thread.sleep(2000);
        clickIt(findUsingText("Confirm"));
        Thread.sleep(2000);
    }

    public void fillDetails() throws InterruptedException {
        firstNameKeyCard.sendKeys("balram");
        lastNameKeyCard.sendKeys("kumar");
        emailKeyCard.sendKeys("balram.k2025@gmail.com");
        android_BackKeyEvent();
        Thread.sleep(2000);
    }
}
