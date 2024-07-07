package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CustomerInfoOrderPage extends pages.BasePage {
    public CustomerInfoOrderPage(WebDriver driver) {
        super(driver);
    }

    // Локатор для ожидаемого результата: появление текста "Для кого самокат"
    private static final String ORDER_BUYER_NAME = "Order_Header__BZXOb";

    // Локатор поля "Имя"
    private static final String inputUserName = ".//input[@placeholder='* Имя']";
    // Локатор поля "Фамилия"
    private static final String inputUserLastName = ".//input[@placeholder='* Фамилия']";
    // Локатор поля "Адрес"
    private static final String inputUserAddress = ".//input[@placeholder='* Адрес: куда привезти заказ']";
    // Локатор для поля станции метро
    private static final String inputUserMetroStation = ".//input[@placeholder='* Станция метро']";
    // Локатор для выбора станции
    private static final String INPUT_USER_METRO_STATION_SELECT = ".//div[@class='select-search__select']/ul[@class='select-search__options']/li[@class='select-search__row']/button[@value='%d']";
    // Локатор для поля номера телефона
    private static final String inputUserPhoneNumber = ".//input[@placeholder='* Телефон: на него позвонит курьер']";

    private static final String nextButtonClick = ".//button[text()='Далее']";

    public void waitOrderBuyerBlankOpen() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className(ORDER_BUYER_NAME)));
    }

    public void setUserName (String userName) {
        driver.findElement(By.xpath(inputUserName)).sendKeys(userName);
    }

    public void setUserLastName (String userLastName) {
        driver.findElement(By.xpath(inputUserLastName)).sendKeys(userLastName);
    }

    public void setUserAddress (String userAddress) {
        driver.findElement(By.xpath(inputUserAddress)).sendKeys(userAddress);
    }

    public void selectMetroStation(int metroStationId) {
        driver.findElement(By.xpath(inputUserMetroStation)).click();
        String inputUserMetroStationSelect = String.format(INPUT_USER_METRO_STATION_SELECT,metroStationId);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(inputUserMetroStationSelect)));
        driver.findElement(By.xpath(inputUserMetroStationSelect)).click();
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        driver.findElement(By.xpath(inputUserPhoneNumber)).sendKeys(userPhoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(By.xpath(nextButtonClick)).click();
    }

    public void setAllCustomerInfoAndClickNextButton(
            String userName,
            String userLastName,
            String userAddress,
            int metroStationId,
            String userPhoneNumber
    ) {
        waitOrderBuyerBlankOpen();
        setUserName (userName);
        setUserLastName (userLastName);
        setUserAddress (userAddress);
        selectMetroStation(metroStationId);
        setUserPhoneNumber(userPhoneNumber);
        clickNextButton();
    }

    public static final int THE_FIRST_METRO_STATION_ID = 3;
    public static final int THE_SECOND_METRO_STATION_ID = 6;

}
