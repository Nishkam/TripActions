import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class BookingTest extends BaseTest {

    @BeforeMethod
    @Parameters({"url"})
    public void setup(String url) {
        driver.get(url);
    }

    @Test
    @Parameters({"cityName"})
    public void bookHotelTest(String cityName){
        BookingHomePage bookingHomePage = new BookingHomePage(driver);
        String startDate = Utils.getDate(0);
        String endDate = Utils.getDate(5);

        String parentHandle = driver.getWindowHandle();

        HotelPage hotelPage = bookingHomePage.
                enterDestination(cityName).
                chooseDestinationFromList(cityName).
                enterTravelDates(startDate).
                enterTravelDates(endDate).
                clickSearchBtn().
                selectRandomHotel(3);

        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(parentHandle);
        // change focus to new tab
        driver.switchTo().window(newTab.get(0));
        hotelPage.clickOnReserveBtn();
    }


}
