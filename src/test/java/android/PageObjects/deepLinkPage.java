package android.PageObjects;

import BaseClass.Util;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

import static BaseClass.AppLaunching.driver;

public class deepLinkPage extends Util {



    public deepLinkPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Google']")
    WebElement googleFolder;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Google']")
    WebElement googleMenu;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Search…\"]/android.widget.TextView")
    WebElement searchMenu;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Secured with encryption\"]/android.widget.TextView")
    WebElement searchMenu1;

    @FindBy(id = "com.google.android.googlequicksearchbox:id/googleapp_search_box")
    WebElement  searchUsingGoogle;
    @FindBy(xpath = "//android.widget.EditText")
    WebElement searchBox;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Google\"]")
    WebElement googleSearchMenu;

    String hotelVirdeeLink = "https://virdee.test-app.link/RYQQ8JcBVwb";


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
        searchUsingGoogle.sendKeys(hotelVirdeeLink);
        android_Enter();
        sleep(10);
    }
}
