import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

import static java.util.concurrent.TimeUnit.*;

public class BasePage {

    private static final int TIMEOUT = 1; //minute
    private static final int THIRTY_SECONDS_POLLING = 30; //seconds
    private static final int FIVE_SECONDS_POLLING = 5; //seconds


    protected WebDriver driver;
    protected Wait five_seconds_wait;
    protected Wait thirty_seconds_wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        five_seconds_wait  = new FluentWait<WebDriver>(driver)
                .withTimeout(TIMEOUT, MINUTES)
                .pollingEvery(FIVE_SECONDS_POLLING, MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        thirty_seconds_wait  = new FluentWait<WebDriver>(driver)
                .withTimeout(TIMEOUT, MINUTES)
                .pollingEvery(THIRTY_SECONDS_POLLING, SECONDS)
                .ignoring(NoSuchElementException.class);

        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected WebElement waitForElementToAppear(Wait wait, WebElement webElement) {
            return (WebElement) wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected List<WebElement> waitForAllElementsToAppear(Wait wait, List<WebElement> webElement) {
        return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    protected WebElement waitForElementToBeClickable(Wait wait, WebElement webElement) {
       return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected String replaceXpath(String xpath, String toBeReplaced, String replacedString) {
        return xpath.replaceAll(toBeReplaced, replacedString);
    }

    protected WebElement scrollIntoView(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }
}
