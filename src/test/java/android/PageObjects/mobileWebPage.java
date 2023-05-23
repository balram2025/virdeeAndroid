package android.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static BaseClass.AppLaunching.driver;
import static BaseClass.Util.*;

public class mobileWebPage {
    public mobileWebPage(){
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//android.widget.TextView[@text='Google']")
    WebElement googleFolder;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Google']")
    WebElement googleMenu;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Search…\"]/android.widget.TextView")
    WebElement searchMenu;

    @FindBy(xpath = "//android.widget.EditText")
    WebElement searchBox;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Secured with encryption\"]/android.widget.TextView")
     WebElement searchMenu1;

    String hotelVirdeeLink1 = "https://mobileweb-dev.virdee.co/?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZyI6IjhmYzQzNmI5LWRhN2UtNDI0ZS04MzQ4LWYwZTdiZmRiNGE3NiIsImNEYXRhIjoiYTAzMjFmYjY0YmRiODVjN2ZiYzI3N2Y5ZjRhNTMwOTQiLCJpYXQiOjE2NzY2MzA3OTYsImV4cCI6MTY3OTMwOTE5Nn0.8ec1-QXB7prXGTb7BOgHI9kVQjkjfHjK_Bu-AFsQFW0";

    @FindBy(id = "com.google.android.googlequicksearchbox:id/googleapp_search_box")
    WebElement  searchUsingGoogle;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Google\"]")
    WebElement googleSearchMenu;

    public void navigateToDepLink() {
        sleep(10);
        android_HomeKeyEvent();
        sleep(2);
        clickIt(googleFolder);
        sleep(3);
        clickIt(googleSearchMenu);
        sleep(2);
        try{
            clickIt(searchMenu);
        }catch(Exception e){
            clickIt(searchMenu1);
        }
        sleep(2);
        searchUsingGoogle.sendKeys(hotelVirdeeLink1);
        android_Enter();
        sleep(10);
    }
}


