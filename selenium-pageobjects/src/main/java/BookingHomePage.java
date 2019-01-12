import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class BookingHomePage extends BasePage {
    @FindBy(xpath = "//*[@id=\"ss\"]")
    private WebElement searchInputBox;

    @FindBy(xpath = "//button[@data-sb-id='main']")
    private WebElement searchBtn;

    public BookingHomePage(WebDriver driver) {
        super(driver);
    }

    public BookingHomePage enterDestination(String destination){
            waitForElementToAppear(five_seconds_wait, searchInputBox).sendKeys(destination);
        return this;
    }

    public BookingHomePage chooseDestinationFromList(final String destination){
        final String replacedXpath = replaceXpath(XpathFile.DESTINATION_DROP_DWN,
                "replaceString", destination);

       five_seconds_wait.until(new ExpectedCondition<Boolean>() {
           public Boolean apply(WebDriver webDriver) {
               WebElement elem = webDriver.findElement(By.xpath(replacedXpath));
               try {
                   if (elem.isDisplayed()) {
                       elem.click();
                       return true;
                   }
               } catch (Exception e) {
               }
               return false;
           }
        });
       return this;
    }

    public BookingHomePage enterTravelDates(final String date){
        final String replacedXpath = replaceXpath(XpathFile.DATE_PICKER, "replaceString", date);

        five_seconds_wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                WebElement elem = webDriver.findElement(By.xpath(replacedXpath));
                try {
                    if (elem.isDisplayed()) {
                        elem.click();
                        return true;
                    }
                } catch (Exception e) {
                }
                return false;
            }
        });
        return this;
    }

    public HotelListPage clickSearchBtn(){
        waitForElementToBeClickable(five_seconds_wait, searchBtn).click();
        return new HotelListPage(driver);
    }
}
