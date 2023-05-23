package android.PageObjects;

import BaseClass.AppLaunching;
import BaseClass.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Payment extends Util {

    public Payment(){
        PageFactory.initElements(AppLaunching.driver,this);
    }


    @FindBy(xpath = "(//android.widget.Button)[2]")
    WebElement addCardButton;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='cc-name']")
    WebElement nameOnCard;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='cc-number']")
    WebElement cardNumber;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='cc-expiry']")
    WebElement cardExpiry;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='cc-cvv']")
    WebElement cardCVV;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='zipcode']")
    WebElement zipcode;

    @FindBy(xpath = "//*[@content-desc='Profile, tab, 3 of 3']")
    WebElement ProfileMenu;

    public void navigateToPaymentForm() throws InterruptedException {
        Thread.sleep(4000);
        clickIt(ProfileMenu);
        Thread.sleep(4000);
        clickIt(findUsingText("Payment"));
        Thread.sleep(4000);
        clickIt(addCardButton);
        Thread.sleep(9000);
    }

    public void Payment() throws InterruptedException {
        clickIt(ProfileMenu);
        Thread.sleep(4000);
        clickIt(findUsingText("Payment"));
        Thread.sleep(4000);
        clickIt(addCardButton);
        Thread.sleep(9000);
        clickIt(nameOnCard);
        Thread.sleep(2000);
        nameOnCard.sendKeys("BALRAM KUMAR");
        Thread.sleep(1000);
        cardNumber.sendKeys("4242424242424242");
        Thread.sleep(1000);
        cardExpiry.sendKeys("1223");
        Thread.sleep(1000);
        cardCVV.sendKeys("123");
        Thread.sleep(1000);
        android_BackKeyEvent();
        Thread.sleep(2000);
        zipcode.sendKeys("451116");
        android_BackKeyEvent();
        Thread.sleep(1000);
        clickIt(findUsingText("Confirm Payment"));
        Thread.sleep(6000);
    }

}