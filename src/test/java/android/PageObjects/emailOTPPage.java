package android.PageObjects;

import BaseClass.DriverClass;
import BaseClass.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class emailOTPPage extends Util {

    public emailOTPPage() {
        PageFactory.initElements(DriverClass.getDriver(), this);
    }


    @FindBy(id = "android:id/text")
    List<WebElement> messageTexts;

    @FindBy(xpath = "(//android.view.ViewGroup[@index='4'])[2]")
            WebElement singleOtpPutter; ;

    @FindBy(xpath = "(//android.view.ViewGroup[@index='4'])[3]")
    WebElement firstDigitOtp;

    @FindBy(xpath = "(//android.view.ViewGroup[@index='7'])[2]")
    WebElement fourthDigitOtp;

    @FindBy(xpath = "(//android.view.ViewGroup[@index='6'])[2]")
    WebElement thirdDigitOtp;

    @FindBy(xpath = "//android.view.ViewGroup[@index='5']")
    WebElement secondDigitOtp;

    @FindBy(id = "com.android.systemui:id/clear_all")
            WebElement clearNotifications;

    @FindBy(xpath = "(//android.widget.EditText)[2]")
            WebElement otpEntry;

    @FindBy(xpath = "//*[@content-desc='Profile, tab, 2 of 2']")
    WebElement ProfileMenu;

    String OTP = new String();

    public String loginSuccessfullyUsingEmailAddress() throws InterruptedException {
        System.out.println("00000000000000000000000");
//        Thread.sleep(5000);
        Assert.assertTrue(findUsingText("EMAIL").isDisplayed());
        Assert.assertTrue(findUsingText("PHONE NUMBER").isDisplayed());
        Assert.assertTrue(findUsingText("CONFIRMATION CODE").isDisplayed());
        Thread.sleep(3000);
        clickIt(findUsingText("EMAIL"));
        Thread.sleep(3000);
        findUsingText("Email").sendKeys("balram@wirdee.co");
        Thread.sleep(2000);
        clickIt(findUsingText("SUBMIT"));
        Thread.sleep(1000);
        Assert.assertTrue(findUsingText("Email is not found on the System.").isDisplayed());
        Thread.sleep(2000);
        findUsingText("balram@wirdee.co").clear();
        Thread.sleep(2000);
        findUsingText("Email").sendKeys("balram@virdee.co");
        Thread.sleep(2000);
        clickIt(findUsingText("SUBMIT"));
        Thread.sleep(13000);
        try {
            android_openNotifications();
            Thread.sleep(3000);
            int Size = messageTexts.size();


            for (int i = 0; i <= 3; i++) {

                Thread.sleep(2000);
                if (OTP.length() == 0) {
                    OTP = OTPloop(Size, messageTexts);
                } else {
                    System.out.println("OTP Found");
                    break;
                }
            }

            if (OTP.length() < 4) {
                System.out.println("---- Failed to retrieve OTP ----");
                android_BackKeyEvent();
                return "";
            } else {
                OTP = extractOTP(OTP);
            }

            if (OTP.length() == 0) {
                System.out.println("OTP fail");
            } else {
                System.out.println("OTP is: " + OTP);
            }

//            android_BackKeyEvent();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return OTP;
    }



    private String OTPloop(int size, List<WebElement> element) {
        System.out.println("Inside OTP Loop method");
        for (int i = 0; i < size; i++) {
            System.out.println("Current position = " + i);
            if (element.get(i).getText().contains("is ")) {
                return element.get(i).getText();
            }
        }
        return "";
    }

    private String extractOTP(String OTP) throws InterruptedException {

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(OTP);

        while(m.find()) {

            System.out.println("OTP is of length : " +m.group().length());
            System.out.println("OTP is : " +m.group());

            if(m.group().length()==4) {
                System.out.println("The OTP is: " + m.group());
                clickIt(clearNotifications);
//                android_clearNotifications();
                Thread.sleep(10000);
                otpEntry.sendKeys(m.group());
                Thread.sleep(10000);
                clickIt(findUsingText("CONTINUE"));
                Thread.sleep(5000);
                return m.group();

            }
        }return "";
    }

    public void insideFunctionalities() throws InterruptedException {
        Thread.sleep(5000);
        clickIt(findUsingText("Stay"));
        Thread.sleep(2000);
        scrollIntoView("Ticket");
        Thread.sleep(2000);
        clickIt(findUsingText("Ticket"));
        Thread.sleep(2000);
    }

     public void logoutAndExitFromApp() throws InterruptedException {
            clickIt(ProfileMenu);
            Thread.sleep(2000);
            scrollIntoView("Logout");
            clickIt(findUsingText("Logout"));
            Thread.sleep(2000);
            clickIt(findUsingText("LOG OUT"));
            Thread.sleep(7000);
        }

        public void loginByManuallyEnteringOTP() throws InterruptedException {
            Assert.assertTrue(findUsingText("EMAIL").isDisplayed());
            Thread.sleep(3000);
            clickIt(findUsingText("EMAIL"));
            Thread.sleep(3000);
            findUsingText("Email").sendKeys("balram@virdee.co");
            Thread.sleep(2000);
            clickIt(findUsingText("SUBMIT"));
            Thread.sleep(15000);
            clickIt(findUsingText("CONTINUE"));
            Thread.sleep(10000);
        }



    }














