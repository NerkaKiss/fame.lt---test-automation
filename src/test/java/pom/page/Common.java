package pom.page;

import io.restassured.response.Response;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.util.Driver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class Common {

    public static void setUpChrome() {
        Driver.setChromeDriver();
    }

    public static void setUpChrome(int sec) {
        Driver.setChromeDriver();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
//        getWebDriverWait(sec).until(
//                driver ->  getJsExecutor().executeScript("return document.readyState").equals("complete")
//        );
    }

    public static void openUrl(String url) {
        Driver.getDriver().get(url);
    }

    public static void close() {
        Driver.quitDriver();
    }

    private static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) Driver.getDriver();
    }

    public static String getCurrentUrl() {
        return Driver.getDriver().getCurrentUrl();
    }

    public static String getTitle() {
        return Driver.getDriver().getTitle();
    }

    private static WebElement getElement(By locator) {
        return Driver.getDriver().findElement(locator);
    }

    private static List<WebElement> getElements(By locator) {
        return Driver.getDriver().findElements(locator);
    }

    public static void sendKeysToElement(By locator, String input) {
        getElement(locator).sendKeys(input);
    }

    public static void clickOnElement(By locator) {
        getElement(locator).click();
    }

    public static String getTextFromElement(By locator) {
        return getElement(locator).getText();
    }

    public static Map<String, Object> getCommonElementDetails(By locator) {
        Map<String, Object> elementDetails = new LinkedHashMap<>();
        WebElement element = getElement(locator);
        elementDetails.put("TagName", element.getTagName());
        elementDetails.put("isDisplayed", element.isDisplayed());
        elementDetails.put("isEnabled", element.isEnabled());
        elementDetails.put("isSelected", element.isSelected());
        elementDetails.put("location", element.getLocation());

        return elementDetails;
    }

    public static String getElementAttributeValue(By locator, String attributeName) {
        return getElement(locator).getAttribute(attributeName);
    }

    public static Map<String, String> getElementAllAttributes(By locator) {
        String script = "var items = {}; " + "for (index = 0; index < arguments[0].attributes.length; ++index) { " + "   items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value; " + "}; " + "return items;";
        return (Map<String, String>) getJsExecutor().executeScript(script, getElement(locator));
    }

    public static boolean isElementEnabled(By locator) {
        return getElement(locator).isEnabled();
    }

    public static boolean isElementSelected(By locator) {
        return getElement(locator).isSelected();
    }

    public static boolean waitCustomisedElementSelected(By locator, int seconds) {
        WebElement element = getElement(locator);

        for (int i = 0; i < seconds * 2; i++) {
            try {
                if (element.isSelected()) return true;
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static WebDriverWait getWebDriverWait(int seconds) {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
    }

    public static void waitElementSelected(By locator, int seconds) {
        getWebDriverWait(seconds).until(ExpectedConditions.elementToBeSelected(locator));
    }

    public static void waitElementIsClickable(By locator, int seconds) {
        getWebDriverWait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitElementAttributeContains(By locator, String attributeName, String attributeValueContains, int seconds) {
        getWebDriverWait(seconds).until(ExpectedConditions.attributeContains(locator, attributeName, attributeValueContains));
    }

    public static void waitElementIsVisible(By locator, int seconds) {
        getWebDriverWait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean isElementDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }

    public static void waitForPageLoadAndAjaxComplete(int seconds) {
        getWebDriverWait(seconds).until(driver -> {
            // Tikrina puslapio būklę ir aktyvias užklausas
            String readyState = (String) getJsExecutor().executeScript("return document.readyState");
            Boolean ajaxComplete = (Boolean) getJsExecutor()
                    .executeScript("return (typeof jQuery !== 'undefined' ? jQuery.active == 0 : true) && " + "!(window.fetch && window.__pendingFetchCount > 0);");
            return "complete".equals(readyState) && ajaxComplete;
        });
    }

    public static void clickOnElements(By locator) {
        getElements(locator).forEach(WebElement::click);
    }

    public static List<String> getTextFromElements(By locator) {
        List<String> textList = new ArrayList<>();
        for (WebElement element : getElements(locator)) {
            textList.add(element.getText());
        }
        return textList;
    }

    private static Select getSelectElement(By locator) {
        return new Select(getElement(locator));
    }

    public static void selectOptionByValue(By locator, String value) {
        getSelectElement(locator).selectByValue(value);
    }

    public static String readSelectedOptionValue(By locator) {
        return getSelectElement(locator).getFirstSelectedOption().getAttribute("value");
    }

    public static String readSelectedOptionText(By locator) {
        return getSelectElement(locator).getFirstSelectedOption().getText();
    }

    public static void selectMultipleValues(By locator, List<String> values) {
        Select select = getSelectElement(locator);
        for (String value : values) {
            select.selectByValue(value);
        }

    }

    public static List<String> getSelectedOptionValues(By locator) {
        List<String> selectedValues = new ArrayList<>();
        List<WebElement> allSelectedOptions = getSelectElement(locator).getAllSelectedOptions();
        for (WebElement element : allSelectedOptions) {
            selectedValues.add(element.getAttribute("value"));
        }
        return selectedValues;
    }

    public static void scrollDownWithJSExecutor() {
        getJsExecutor().executeScript("window.scroll(0,800)");
    }

    public static void scrollDownWithJSExecutorToElement(By locator) {
        getJsExecutor().executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public static void scrollDownWithActions() {
        getActions().sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    private static Actions getActions() {
        return new Actions(Driver.getDriver());
    }

    public static void clickOnElementWithActions(By locator) {
        getActions().click(getElement(locator)).perform();
    }

    public static void doubleClickOnElementWithActions(By locator) {
        getActions().doubleClick(getElement(locator)).perform();
    }

    public static void rightClickOnElementWithActions(By locator) {
        getActions().contextClick(getElement(locator)).perform();
    }

    public static List<Boolean> getListAllElementsSelected(By locator) {
        List<Boolean> elementsSelectedList = new ArrayList<>();
        for (WebElement element : getElements(locator)) {
            elementsSelectedList.add(element.isSelected());
        }
        return elementsSelectedList;
    }

    public static int getResponseStatus(String userName, String password, String url) {
        Response response = given().auth().basic(userName, password).get(url);
        return response.getStatusCode();
    }

    public static int countElements(By element) {
        return getElements(element).size();
    }

    public static boolean isElementDisplayedBySize(By locator) {
        return !getElements(locator).isEmpty();
    }

    public static boolean isImageExists(String url) {
        Response response = given().when().head(url);
        return response.statusCode() == 200 &&
                response.getHeader("Content-Type") != null &&
                response.getHeader("Content-Type").startsWith("image/");
    }

    public static String getElementValueByTag(By locator, String tag) {
        String value = getElement(locator).getDomAttribute(tag);
        return value != null ? value : getElement(locator).getAttribute(tag);
    }

    public static boolean isJavascriptAlertPresent() {
        try {
            Driver.getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public static String getTextFromJavaScriptAlert() {
        return Driver.getDriver().switchTo().alert().getText();
    }

    public static void dragAndDropWithActionsFromElementToElement(By locator, By locatorTarget) {
        getActions().dragAndDrop(getElement(locator), getElement(locatorTarget)).build().perform();
    }

    public static void dragAndDropWithActionsFromElementToXY(By locator, int x, int y) {
        getActions().dragAndDropBy(getElement(locator), x, y).build().perform();
    }

    public static void switchToFrame(By locator) {
        Driver.getDriver().switchTo().frame(getElement(locator));
    }

    public static void moveSliderWithJSExecutor(By locator, double number) {
        getJsExecutor().executeScript(
                "arguments[0].value = arguments[1]; " +
                        "arguments[0].dispatchEvent(new Event('input')); " +
                        "arguments[0].dispatchEvent(new Event('change'));",
                getElement(locator), String.valueOf(number));
    }

    public static void waitNumberOfElementsToBeMoreThan(By locator, int number, int seconds) {
        getWebDriverWait(seconds).until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
    }

    public static void sendInputTimesWithActions(Object input, int number) {
        getActions().sendKeys(input.toString().repeat(Math.abs(number))).build().perform();
    }

    public static void alertIsPresent(int seconds) {
        getWebDriverWait(seconds).until(ExpectedConditions.alertIsPresent());
    }

    public static void clickJavaScriptAlertOk() {
        Driver.getDriver().switchTo().alert().accept();
    }

    public static void clickJavaScriptAlertCancel() {
        Driver.getDriver().switchTo().alert().dismiss();
    }

    public static void sendKeysToJavaScriptAlert(String message) {
        Driver.getDriver().switchTo().alert().sendKeys(message);
    }

    public static boolean checkForJavaScriptErrors() {
        LogEntries logEntries = Driver.getDriver().manage().logs().get(LogType.BROWSER);

        for (LogEntry entry : logEntries) {
            if (entry.getMessage().contains("Error")) {
                return true;
            }
        }
        return false;
    }

    public static void switchToWindow(int windowNumber) {
        Driver.getDriver().switchTo().window((String) Driver.getDriver().getWindowHandles().toArray()[windowNumber]);
    }

    public static String getTextFromShadow(By shadowLocator, By insideLocator) {
        return getElement(shadowLocator).getShadowRoot().findElement(insideLocator).getText();
    }

    public static void moveMouseToLocatorWithActions(By locator) {
        getActions().moveToElement(getElement(locator)).build().perform();
    }

    public static String getCustomAttribute(By locator, String attribute) {
        return getElement(locator).getDomProperty(attribute);
    }
}
