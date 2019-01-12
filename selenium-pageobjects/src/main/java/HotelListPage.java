import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class HotelListPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"hotellist_inner\"]/div/div/div/div/table/tfoot/tr[2]/td/div/div/a/span")
    private List<WebElement> listHotel;


    public HotelListPage(WebDriver driver) {
        super(driver);
    }


    public HotelPage selectRandomHotel(int random){
        WebElement hotel = waitForAllElementsToAppear(five_seconds_wait, listHotel).
                get(new Random().nextInt(random));
        scrollIntoView(hotel);
        hotel.click();
        return new HotelPage(driver);
    }
}
