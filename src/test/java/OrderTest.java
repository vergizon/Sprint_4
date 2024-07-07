import pages.CustomerInfoOrderPage;
import pages.MainPage;
import pages.RentInfoOrderPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderTest extends BaseTest {

    private final String userName;
    private final String userLastName;
    private final String userAddress;
    private final int metroStationId;
    private final String userPhoneNumber;
    private final String date;
    private final String rentDaysText;
    private final String scooterColorId;
    private final String comment;
    private final String orderButtonName;

    public OrderTest(
            String userName,
            String userLastName,
            String userAddress,
            int metroStationId,
            String userPhoneNumber,
            String date,
            String rentDaysText,
            String scooterColorId,
            String comment,
            String orderButtonName

    ) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.userAddress = userAddress;
        this.metroStationId = metroStationId;
        this.userPhoneNumber = userPhoneNumber;
        this.date = date;
        this.rentDaysText = rentDaysText;
        this.scooterColorId = scooterColorId;
        this.comment = comment;
        this.orderButtonName = orderButtonName;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {
                        "Александр",
                        "Вергизов",
                        "Луговая 115",
                        CustomerInfoOrderPage.THE_FIRST_METRO_STATION_ID,
                        "79265612740",
                        LocalDateTime.now().plusDays(1).format(dateFormatter),
                        RentInfoOrderPage.oneDayRentText,
                        RentInfoOrderPage.scooterColorBlackId,
                        "Привезите как можно скорее",
                        MainPage.UPPER_ORDER_BUTTON_NAME
                },
                {
                        "Карлсон",
                        "Малышев",
                        "Швеция",
                        CustomerInfoOrderPage.THE_SECOND_METRO_STATION_ID,
                        "3222233322233",
                        LocalDateTime.now().plusDays(2).format(dateFormatter),
                        RentInfoOrderPage.twoDaysRentText,
                        RentInfoOrderPage.scooterColorGrayId,
                        "Дам за самокат 8 тортов и одну свечку",
                        MainPage.LOWER_ORDER_BUTTON_NAME
                }
        };
    }

    @Test
    public void ScooterOrderTest(){
        MainPage objMainPage = new MainPage(driver);
        CustomerInfoOrderPage objCustomerDataPage = new CustomerInfoOrderPage(driver);
        RentInfoOrderPage objRentDataPage = new RentInfoOrderPage(driver);
        objMainPage.clickRemoveCookieButton();
        if (orderButtonName.equals(MainPage.UPPER_ORDER_BUTTON_NAME)) {
            objMainPage.clickUpperButtonToOrder();
        } else if (orderButtonName.equals(MainPage.LOWER_ORDER_BUTTON_NAME)) {
            objMainPage.clickLowerButtonToOrder();
        }
        objCustomerDataPage.setAllCustomerInfoAndClickNextButton(
                userName,
                userLastName,
                userAddress,
                metroStationId,
                userPhoneNumber
        );
        objRentDataPage.orderScooter(date, rentDaysText, scooterColorId, comment);
        String actualText = objRentDataPage.getStatusButtonText();
        Assert.assertEquals("Text in status button doesn't match!", EXPECTED_TEXT, actualText);
    }

    private static final String EXPECTED_TEXT = "Посмотреть статус";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
}
