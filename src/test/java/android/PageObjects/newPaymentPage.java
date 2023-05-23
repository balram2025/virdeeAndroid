package android.PageObjects;

import BaseClass.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static BaseClass.AppLaunching.driver;

public class newPaymentPage extends Util {

    public newPaymentPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "android.widget.EditText")
    List<WebElement> textFields;

    public void enterDetails() throws InterruptedException {
        textFields.get(0).sendKeys("Balram Kumar");
        Thread.sleep(3000);
        textFields.get(1).sendKeys("4242424242424242");
        Thread.sleep(3000);
        textFields.get(2).sendKeys("05/26");
        Thread.sleep(3000);
        textFields.get(3).sendKeys("145");
        Thread.sleep(3000);
        textFields.get(4).sendKeys("245561");
        Thread.sleep(3000);
        clickIt(findUsingText("Save"));
        Thread.sleep(3000);

    }


}
