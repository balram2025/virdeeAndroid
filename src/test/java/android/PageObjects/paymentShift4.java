package android.PageObjects;

import BaseClass.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static BaseClass.AppLaunching.driver;

public class paymentShift4 extends Util {

    public paymentShift4(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//android.widget.EditText)[1]")
    WebElement cardHolderName;

    @FindBy(xpath = "(//android.widget.EditText)[2]")
    WebElement cardNumber;

    @FindBy(xpath = "(//android.widget.EditText)[3]")
    WebElement cvv;

    @FindBy(xpath = "(//android.widget.EditText)[4]")
    WebElement billingPostalCode;

    @FindBy(xpath = "(//*[@text='Payment Type'])[2]")
    WebElement selectPaymentType;

    @FindBy(xpath = "(//*[@text='Expiration'])[2]")
    WebElement selectExpiration;

    @FindBy(xpath = "//*[@text='Year...']")
    WebElement selectYear;

    @FindBy(xpath = "//*[@text='Secure My Payment Information']")
    WebElement secureButton;

    public void fillForm() throws InterruptedException {
       cardHolderName.sendKeys("balram kumar");
       sleep(2);
       clickIt(selectPaymentType);
       clickIt(findUsingText("MasterCard"));
        sleep(2);
       cardNumber.sendKeys("4242424242424242");
        sleep(2);
       clickIt(selectExpiration);
       clickIt(findUsingText("05 - May"));
        sleep(2);
       clickIt(selectYear);
       clickIt(findUsingText("2026"));
        sleep(2);
       cvv.sendKeys("543");
        sleep(2);
       billingPostalCode.sendKeys("245561");
        sleep(2);
       clickIt(secureButton);
    }





}
