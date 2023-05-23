package BaseClass;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static BaseClass.AppLaunching.driver;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class Util  {

    private static Dimension windowSize;
    private static Duration SCROLL_DUR = Duration.ofMillis(1000);
    private static double SCROLL_RATIO = 0.8;
    private static int ANDROID_SCROLL_DIVISOR = 3;

    private static WebDriverWait wait;


    public static String getScreenShotMethod(WebDriver driver, String filename) throws IOException {
        String date = new SimpleDateFormat("[dd-MM-yyyy]-hh-mm-ss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + date + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        // This new path for jenkins
        String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
                + date + ".png";
        return newImageString;
    }

    public static void getFullPageScreenshot(WebDriver driver, String filename){
        Screenshot Screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(500))
                .takeScreenshot(driver);

        try {
            ImageIO.write(Screenshot.getImage(),"png",
                    new File(System.getProperty("user.dir")+"\\FullPageScreenshots\\"+filename+".png"));
        }catch (IOException e){
            e.getMessage();
        }



    }


    public static void scrollDown(){
        Dimension dimension = DriverClass.getDriver().manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);

        new TouchAction((PerformsTouchActions) DriverClass.getDriver())
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }

    public static void scrollUp(){
        Dimension dimension = DriverClass.getDriver().manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.2);
        int scrollEnd = (int) (dimension.getHeight() * 0.5);

        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }

    public static void scrollNClick(By listItems, String Text){
        boolean flag = false;

        while(true){
            for(WebElement el: DriverClass.getDriver().findElements(listItems)){
                if(el.getAttribute("text").equals(Text)){
                    el.click();
                    flag=true;
                    break;
                }
            }
            if(flag)
                break;
            else
                scrollDown();
        }
    }



    public static void swipeUp(WebElement element){
        Dimension size = element.getSize();
        int endX = (int) (size.height * 0.70);
        int startY = (int) (size.height * 0.30);
        int startX = (size.width / 2);
        new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.
                        waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, startY))
                .release()
                .perform();

    }


    public static void scrollNClick(WebElement el){
        int retry = 0;
        while(retry <= 5){
            try{
                el.click();
                break;
            }catch (NoSuchElementException e){
                scrollDown();
                retry++;
            }
        }
    }

    public static void scrollNClickUp(WebElement el){
        int retry = 0;
        while(retry <= 5){
            try{
                el.click();
                break;
            }catch (NoSuchElementException e){
                scrollUp();
                retry++;
            }
        }
    }

    public static boolean scrollIntoView(String Text){

        String mySelector = "new UiSelector().text(\"" + Text + "\").instance(0)";
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + mySelector + ");";
        ((AndroidDriver<?>) DriverClass.getDriver()).findElementByAndroidUIAutomator(command);

        return true;

    }

    public static void scrollTo(String Text){

        if(DriverClass.getDriver() instanceof AndroidDriver<?>){
            scrollIntoView(Text);
        }else
        if(DriverClass.getDriver() instanceof IOSDriver<?>){
            final HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("predicateString", "value == '" + Text + "'");
            scrollObject.put("toVisible", "true");
            ((IOSDriver<?>) DriverClass.getDriver()).executeScript("mobile: scroll", scrollObject);
        }
    }


    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private static Dimension getWindowSize() {
        if (windowSize == null) {
            windowSize = DriverClass.getDriver().manage().window().getSize();
        }
        return windowSize;
    }

    public static void scroll(ScrollDirection dir, double distance) {
        if (distance < 0 || distance > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = getWindowSize();
        Point midPoint = new Point((int)(size.width * 0.5), (int)(size.height * 0.5));
        int top = midPoint.y - (int)((size.height * distance) * 0.5);
        int bottom = midPoint.y + (int)((size.height * distance) * 0.5);
        int left = midPoint.x - (int)((size.width * distance) * 0.5);
        int right = midPoint.x + (int)((size.width * distance) * 0.5);
        if (dir == ScrollDirection.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }

    protected static void swipe(Point start, Point end, Duration duration) {
        boolean isAndroid = DriverClass.getDriver() instanceof AndroidDriver<?>;

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        if (isAndroid) {
            duration = duration.dividedBy(ANDROID_SCROLL_DIVISOR);
        } else {
            swipe.addAction(new Pause(input, duration));
            duration = Duration.ZERO;
        }
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver<?>) DriverClass.getDriver()).perform(ImmutableList.of(swipe));
    }

    public static void click(By byEl){
        new WebDriverWait(DriverClass.getDriver(), 20).until(ExpectedConditions.presenceOfElementLocated(byEl)).click();
    }

    public boolean JSClick(WebElement ele) {
        boolean flag = false;
        try {
            // WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) AppLaunching.driver;
            executor.executeScript("arguments[0].click();", ele);
            // driver.executeAsyncScript("arguments[0].click();", element);

            flag = true;

        }

        catch (Exception e) {
            throw e;

        } finally {
            if (flag) {
                System.out.println("Click Action is performed");
            } else if (!flag) {
                System.out.println("Click Action is not performed");
            }
        }
        return flag;
    }
    public static void clickIt(WebElement element) {
        new WebDriverWait(DriverClass.getDriver(), 20).until(ExpectedConditions.visibilityOf(element)).click();
        sleep(2);
    }
    public static void clickInOneOf(int index,String Text ){
        List<WebElement> elements= findUsingTexts(Text);
        assert elements != null;
        elements.get(index).click();
    }

//    public static void selectfromlist(WebDriver driver,String text,int index){
//       driver.findElements(By.xpath(""));
//    }


    public static void sendKeys(By byEl, String text){
        waitForEl(byEl);
        DriverClass.getDriver().findElement(byEl).sendKeys(text);
    }

    public static void waitForEl(By byEl){
        new WebDriverWait(DriverClass.getDriver(), 20).until(ExpectedConditions.presenceOfElementLocated(byEl));
    }


    // ******* EXPLICIT WAITS ON SINGLE ELEMENT ******************

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS CLICKABLE - DISPLAYED AND ENABLED

    public static WebElement wait_until_MobileElementIs_Clickable(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public static WebElement wait_until_MobileElementIs_Visible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement wait_until_visible(WebDriver driver,WebElement element) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean check_wait_until_visible(WebDriver driver,WebElement element) {

        boolean flag = false;
        try {
            wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
            flag = true;
        } catch (Exception e) {
            throw e;
        } finally {
            if (flag) {
                System.out.println("element is visible");
            } else if (!flag) {
                System.out.println("element is not visible");
            }
        }
        return flag;
    }


    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
    public static WebElement wait_until_MobileElementIs_Present(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    // ************* EXPLICIT WAITS ON MULTIPLE ELEMENTS ***************//

    // WAIT FOR MAX TIME 5 SECS TILL THE ELEMENT IS PRESENT
    public static List<WebElement> wait_until_MobileElementsAre_Present(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // WAIT FOR MAX TIME 5 SECS TILL THE ELEMENT IS VISIBLE
    public static List<WebElement> wait_until_MobileElementsAre_Visible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static  List<WebElement> wait_until_MobileElementsAre_Visible(
            WebDriver driver, By locator, int timeInSeconds) {
        wait = new WebDriverWait(driver, timeInSeconds);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // **** RETURNING MOBILE ELEMENT ANDROID *****//

    public WebElement android_returnMobileElementPresentUsingText(String text) {
        return wait_until_MobileElementIs_Visible(
                driver, MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")"));
    }

    public WebElement android_returnMobileElementPresentUsingID(String id) {
        return wait_until_MobileElementIs_Visible(
                driver, MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + id + "\")"));
    }

    public WebElement android_returnMobileElementPresentUsingContentDesc(String contentDesc) {
        return wait_until_MobileElementIs_Visible(
                driver,
                MobileBy.AndroidUIAutomator("new UiSelector().description(\"" + contentDesc + "\")"));
    }

    public WebElement android_returnMobileElementPresentUsingXPath(String xpath) {
        return wait_until_MobileElementIs_Visible(driver, MobileBy.xpath(xpath));
    }

    public WebElement android_returnMobileElementPresentUsingClassName(String className) {
        return wait_until_MobileElementIs_Visible(driver, MobileBy.className(className));
    }

    // **** RETURNING MOBILE ELEMENTS ANDROID *****//

    public List<WebElement> android_returnMobileElementsPresentUsingXPath(String xpath) {
        return wait_until_MobileElementsAre_Visible(driver, MobileBy.xpath(xpath));
    }

    public List<WebElement> android_returnMobileElementsPresentUsingClassName(String className) {
        return wait_until_MobileElementsAre_Visible(driver, MobileBy.className(className));
    }

    // open notifications Android
    // ************************************************************

    public void android_openNotifications() {
        ((AndroidDriver) driver).openNotifications();
    }

    // rotate screen
    // ************************************************************

    public void rotateScreenPotrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void rotateScreenlandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    // clear notifications
    // ************************************************************

    public void android_clearNotifications() {

        // google pixel related
        if (android_isMobileElementPresentUsingText("Clear")) {
            wait_until_MobileElementIs_Clickable(
                    driver, MobileBy.AndroidUIAutomator("new UiSelector().text(\"CLEAR ALL\")"))
                    .click();

        } else {
        }

        // TODO update clear notifications present
    }

    // Set location Android

    public void android_SetLocation(double latitude, double longitude) {
        Location loc = new Location(latitude, longitude, 10); // latitude, longitude, altitude
        driver.setLocation(loc);
    }

    // *************** EXTRAS ********************//

    // REFRESH PAGE
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // Sleep
    public static void sleep(int s) {
        try {
            Thread.sleep(s * 1000);
        } catch (InterruptedException ex) {
        } catch (IllegalArgumentException ex) {
        }
    }

    public boolean verify_Element_NotPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() == 0;
    }

    public void wait_until_Element_is_Not_Present(WebDriver driver, By locator) {
        if (driver.findElements(locator).size() > 0) {
            sleep(2);
        }
    }

    public String randomString() {
        Date date = new Date();
        Format dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateformat.format(date);
    }
    public static WebElement findUsingText(String Text){
        return  driver.findElement(By.xpath("//*[@text='"+Text+"']"));
    }
    public static WebElement findUsingTextAndIndex(String Text,int index){
        return  driver.findElement(By.xpath("//*[@text='"+Text+"'][@index='"+index+"']"));
    }

    public static List<WebElement> findUsingTexts(String Text){
        driver.findElement(By.xpath("//*[@text='"+Text+"']"));
        return null;
    }

//    public void clickText(String text) { // used to get focus out of a text box
//        hoverAndClick(driver, By.xpath("//*[contains(text(),'" + text + "')]"));
//        sleep(2);
//    }


    // **** SCROLL FUNCTIONS (SCROLL'S ON ENTIRE PAGE) *****//

    public static MobileElement android_ScrollToText(String text) {

        MobileElement el =
                (MobileElement)
                        wait_until_MobileElementIs_Visible(
                                driver,
                                MobileBy.AndroidUIAutomator(
                                        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
        return el;
    }

    public MobileElement android_ScrollToContentDesc(String contentDesc) {
        MobileElement el =
                (MobileElement)
                        wait_until_MobileElementIs_Visible(
                                driver,
                                MobileBy.AndroidUIAutomator(
                                        "new UiScrollable(new UiSelector()).scrollIntoView(description(\""
                                                + contentDesc
                                                + "\"));"));
        return el;
    }

    public MobileElement android_scrollToID(String id) {
        MobileElement el =
                (MobileElement)
                        wait_until_MobileElementIs_Visible(
                                driver,
                                MobileBy.AndroidUIAutomator(
                                        "new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\""
                                                + id
                                                + "\"));"));
        return el;
    }

    public boolean android_isMobileElementPresentUsingText(String text) {
        try {
            if (wait_until_MobileElementsAre_Visible(
                    driver,
                    (MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")")),
                    5)
                    .size()
                    >= 1) {
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean android_isMobileElementPresentUsingID(String id) {
        try {
            if (wait_until_MobileElementsAre_Visible(
                    driver,
                    (MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + id + "\")")),
                    5)
                    .size()
                    >= 1) {
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean android_isMobileElementPresentUsingContentDesc(String contentDesc) {
        try {
            if (wait_until_MobileElementsAre_Visible(
                    driver,
                    (MobileBy.AndroidUIAutomator(
                            "new UiSelector().description(\"" + contentDesc + "\")")),
                    5)
                    .size()
                    >= 1) {
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean android_isMobileElementPresentUsingXpath(String xPath) {
        try {
            if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.xpath(xPath)), 5).size() >= 1) {
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    // ********** SCROLL AND CHECK FOR PRESENCE OF MOBILE ELEMENT ANDROID **************//

    public static boolean android_isMobileElementPresentUsingText_Scroll(String text) {
        if (wait_until_MobileElementsAre_Present(
                driver,
                (MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));")))
                .size()
                >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean android_isMobileElementPresentUsingID_Scroll(String id) {
        if (wait_until_MobileElementsAre_Present(
                driver,
                (MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\""
                                + id
                                + "\"));")))
                .size()
                >= 1) return true;
        else return false;
    }

    public static boolean android_isMobileElementPresentUsingContentDesc_Scroll(String contentDesc) {
        if (wait_until_MobileElementsAre_Present(
                driver,
                (MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(description(\""
                                + contentDesc
                                + "\"));")))
                .size()
                >= 1) {
            return true;
        } else return false;
    }

    // ********** TOUCH ACTIONS **************//
    // ************************************************************

    public void pressAndHold(WebElement element1, int timeInSeconds) {
        Rectangle rect1 = element1.getRect();

        TouchAction touch = new TouchAction(driver);
        touch
                .longPress(
                        PointOption.point(
                                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight() / 2))
                .waitAction(waitOptions(Duration.ofSeconds(timeInSeconds)))
                .release()
                .perform();
    }

    public void pressElement(MobileElement element, long seconds) {
        new TouchAction(driver)
                .press(element(element))
                .waitAction(waitOptions(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    public void tap(WebElement element) {
        new TouchAction(driver).tap(TapOptions.tapOptions().withElement(element(element))).perform();
    }

    public void tap(WebElement element, int milliseconds) {
        new TouchAction(driver)
                .tap(TapOptions.tapOptions().withElement(element(element)))
                .waitAction(waitOptions(Duration.ofMillis(milliseconds)))
                .perform();
    }

    public static void dragAndDrop(WebElement element1,WebElement element2){
        Actions act = new Actions(driver);
        act.clickAndHold(element1)
                .pause(1000)
                .moveToElement(element2)
                .release(element2)
                .build().perform();
    }

    public static void tapByCoordinates(int x, int y) {
        new TouchAction(driver)
                .tap(PointOption.point(x, y))
                .waitAction(waitOptions(Duration.ofMillis(1000)))
                .perform();
    }

    public void basicSwipe(WebElement element1, WebElement element2) {
        Rectangle rect1 = element1.getRect();
        Rectangle rect2 = element2.getRect();

        TouchAction touch = new TouchAction(driver);
        touch
                .press(
                        PointOption.point(
                                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight()))
                .moveTo(
                        PointOption.point(
                                rect2.getX() + rect2.getWidth() / 2, rect2.getY() + rect2.getHeight()))
                .release()
                .perform();
    }

    public void dragDrop(WebElement element1, WebElement element2) {
        Rectangle rect1 = element1.getRect();
        Rectangle rect2 = element2.getRect();

        TouchAction touch = new TouchAction(driver);
        touch
                .longPress(
                        PointOption.point(
                                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight() / 2))
                .moveTo(
                        PointOption.point(
                                rect2.getX() + rect2.getWidth() / 2, rect2.getY() + rect2.getHeight() / 2))
                .release()
                .perform();
    }

    public void zoom_Using_MultiTouchActions() {

        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        int halfWidth = width / 2;
        int halfHeight = height / 2;

        MultiTouchAction multiTouch = new MultiTouchAction(driver);
        TouchAction touch1 = new TouchAction(driver);
        TouchAction touch2 = new TouchAction(driver);

        touch1
                .press(PointOption.point(halfHeight, halfHeight))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, 60))
                .release();
        touch2
                .press(PointOption.point(halfHeight, halfHeight + 40))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, 80))
                .release();

        multiTouch.add(touch1).add(touch2);
        multiTouch.perform();
    }

    // ****************************  SELENIUM FUNCTIONS *****************************//

    // ******* EXPLICIT WAITS ON SINGLE ELEMENT ******************//

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS CLICKABLE - DISPLAYED AND ENABLED
    public WebElement wait_until_ElementIs_Clickable(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public WebElement wait_until_ElementIs_Visible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
    public static WebElement wait_until_ElementIs_Present(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL SELENIUM FINDS 2 WINDOWS
    public void wait_until_Two_Windows_Are_Available(WebDriver driver) {
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    // ************* EXPLICIT WAITS ON MULTIPLE ELEMENTS ***************//

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
    public List<WebElement> wait_until_ElementsAre_Present(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
    public List<WebElement> wait_until_ElementsAre_Visible(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // ******** EXPLICIT WAITS ON PAGE TITLE,URL AND ELEMENT_NOT_PRESENT ************//

    public boolean wait_until_TitleContains(WebDriver driver, String keyword) {
        wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.titleContains(keyword));
    }

    public boolean wait_until_URL_Matches(WebDriver driver, String regex) {
        wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.urlMatches(regex));
    }

    public boolean IS_Element_NotPresent(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ************** NORMAL CLICK *******************//

    public static void click(WebDriver driver, By locator) {
        wait_until_ElementIs_Present(driver, locator).click();
        waitForPageToLoad(driver);
    }

    // ******************** ACTIONS ***********************//

    // Hover over an element
    public void hover(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(wait_until_ElementIs_Visible(driver, locator)).build().perform();
        waitForPageToLoad(driver);
    }

    // Hover over an element and click
    public void hoverAndClick(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(wait_until_ElementIs_Visible(driver, locator)).click().build().perform();
        waitForPageToLoad(driver);
    }

    public void hoverAndClear(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        WebElement el = wait_until_ElementIs_Visible(driver, locator);
        action.moveToElement(el).click().build().perform();
        el.clear();
        waitForPageToLoad(driver);
    }

    // Hover over an element, click and press enter
    public void hoverClickAndPressEnter(WebDriver driver, By locator) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .click()
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // Hover over an element click and send data
    public void hoverClickAndSendData(WebDriver driver, By locator, String data) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .click()
                .sendKeys(data)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // Hover over an element click, send data and press enter
    public void hoverClickSendDataAndPressEnter(WebDriver driver, By locator, String data) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .click()
                .sendKeys(data)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // sendkeys
    public void hoverAndSendData(WebDriver driver, By locator, String data) {
        Actions action = new Actions(driver);
        action
                .moveToElement(wait_until_ElementIs_Visible(driver, locator))
                .sendKeys(data)
                .build()
                .perform();
        waitForPageToLoad(driver);
    }

    // Double click
    public void doubleClick(WebDriver driver, By locator) {
        Actions doubleClick = new Actions(driver);
        doubleClick.doubleClick(wait_until_ElementIs_Visible(driver, locator)).build().perform();
        waitForPageToLoad(driver);
    }

    // Drag and Drop by offset
    public void dragAndDropOffset(WebDriver driver, By locator, int offsetX, int offsetY) {
        WebElement el = wait_until_ElementIs_Visible(driver, locator);
        Actions builder = new Actions(driver);
        builder.dragAndDropBy(el, offsetX, offsetY).build().perform();
        waitForPageToLoad(driver);
    }

    // Drag and drop Elements
    public void dragAndDropToElementContainner(
            WebDriver driver, WebElement source, WebElement target) {
        Actions builder = new Actions(driver);
        builder.dragAndDrop(source, target).build().perform();
        waitForPageToLoad(driver);
    }

    // *********** JAVA SCRIPT CLICK **********************************//

    public void jsClick(WebDriver driver, By locator) {
        String code =
                "var fireOnThis = arguments[0];"
                        + "var evObj = document.createEvent('MouseEvents');"
                        + "evObj.initEvent( 'click', true, true );"
                        + "fireOnThis.dispatchEvent(evObj);";

        WebElement el = wait_until_ElementIs_Visible(driver, locator);
        ((JavascriptExecutor) driver).executeScript(code, el);
        waitForPageToLoad(driver);
    }

    public void jsFocusAndClick(WebDriver driver, By locator) {
        WebElement element = wait_until_ElementIs_Present(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForPageToLoad(driver);
    }

    public void jsFocusAndClick(WebDriver driver, WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        waitForPageToLoad(driver);
    }

    // ************ PAGE LOAD STATE ****************************//

    // Get Page State
    public static String getPageState(WebDriver driver) {
        WebElement el = driver.findElement(By.cssSelector("body"));
        String code = "return document.readyState";
        String result = (String) ((JavascriptExecutor) driver).executeScript(code, el);
        return result;
    }

    // Wait For Page to Load Completely
    public static void waitForPageToLoad(WebDriver driver) {
        while (!getPageState(driver).equals("complete")) {
            sleep(1);
        }
    }

    // Wait for Page title to change
    public void waitForPageTitleToChange(WebDriver driver, String title) {
        while (driver.getTitle().equalsIgnoreCase(title)) {
            sleep(1);
        }
        while (driver.getTitle().matches(".*?" + title + ".*?")) {
            sleep(1);
        }
    }


    // **** ANDROID KEY EVENT FUNCTIONS *****//

    public static void android_HomeKeyEvent() {
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.HOME));
        sleep(2);
    }

    public static void android_BackKeyEvent() {
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.BACK));
        sleep(2);
    }

    public static void android_Enter() {
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.ENTER));
        sleep(2);
    }

    public static void android_Tab() {
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.TAB));
        sleep(2);
    }

    public static void android_MultiTaskingKeyEvent() {
        ((AndroidDriver) driver)
                .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.APP_SWITCH));
        sleep(2);


    }










}
