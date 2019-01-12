import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HotelPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"hprt-form\"]/table/tbody/tr/td[3]/div/div[2]/span")
    public List<WebElement> priceColumn;

    public HotelPage(WebDriver driver) {
        super(driver);
    }

    public int getLowestPriceRoom(){
        double min = Integer.MAX_VALUE;
        int index = -1, i = 0;
        priceColumn = waitForAllElementsToAppear(five_seconds_wait, priceColumn);

        for(WebElement elem : priceColumn){
            double cost = Utils.parseStringToDouble(elem.getText().replace("$", "")
                    .replace(",", "").trim());
            if(cost < min){
                min = cost;
                index = i;
            }
            System.out.println(cost);
            i++;

        }
        System.out.println("The lowest cost is " + min + " with index " + index);
        return index;
    }

    public HotelPage selectNoOfRooms(int index){
        String replacePath = replaceXpath(XpathFile.NO_OF_ROOM_DROP_DWN, XpathFile.REPLACE_STRING, String.valueOf(index + 2));
        Select select = new Select(driver.findElement(By.xpath(replacePath)));
        select.selectByIndex(1);
        return this;
    }


    public HotelPage reserveRoom(int index){
        String replacePath = replaceXpath(XpathFile.RESERVE_ROOM_BTN, XpathFile.REPLACE_STRING, String.valueOf(index + 2));
        waitForElementToBeClickable(five_seconds_wait, driver.findElement(By.xpath(replacePath))).click();
        return this;
    }

    public HotelPage clickOnReserveBtn(){
        scrollIntoView(driver.findElement(By.xpath("//*[@id=\"hprt-form\"]/table/thead/tr/th[6]")));
        int index = getLowestPriceRoom();

        try {
            selectNoOfRooms(index);
        } catch (Exception e){
            System.out.println(" There is only one room or no room available ");
        }
        reserveRoom(index);
        return this;
    }
}
