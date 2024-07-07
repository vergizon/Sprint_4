package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends pages.BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // локатор для кнопки "Заказать" сверху
    private static final String UPPER_BUTTON_TO_ORDER = ".//button[@class='Button_Button__ra12g' and text()='Заказать']";

    // локатор для блока "Вопросы о важном"
    private static final String QUESTIONS_BLOCK = ".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном']";

    // локатор для выпадающих вопросов в блоке "Вопросы о важном"
    private static final String ACCORDEON_QUESTIONS = ".//div[@id='accordion__heading-%d']";

    // локатор для выпадающих ответов в блоке "Вопросы о важном"
    private static final String ACCORDEON_REPLIES = ".//div[@id='accordion__panel-%d']";

    // локатор для кнопки "Да все привыкли"
    private static final String COOKIE_BUTTON = "rcc-confirm-button";

    // локатор для кнопки "Заказать" снизу
    private static final String LOWER_BUTTON_TO_ORDER = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']";

    public void scrollTillQuestionsBlock() {
        WebElement element = driver.findElement(By.xpath(QUESTIONS_BLOCK));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickAccordionItem(int dropDownItemId) {
        String accordionItemXpath = String.format(ACCORDEON_QUESTIONS, dropDownItemId);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(accordionItemXpath)));
        driver.findElement(By.xpath(accordionItemXpath)).click();
    }

    public String getDropDownItemText(int dropDownItemId) {
        return driver.findElement(By.xpath(String.format(ACCORDEON_REPLIES, dropDownItemId))).getText();
    }

    public void clickRemoveCookieButton() {
        driver.findElement(By.id(COOKIE_BUTTON)).click();
    }

    public void clickUpperButtonToOrder() {
        driver.findElement(By.xpath(UPPER_BUTTON_TO_ORDER)).click();
    }

    public void clickLowerButtonToOrder() {
        driver.findElement(By.xpath(LOWER_BUTTON_TO_ORDER)).click();
    }

    public void openReply(int dropDownItemId) {
        scrollTillQuestionsBlock();
        clickAccordionItem(dropDownItemId);
    }

    public static final String UPPER_ORDER_BUTTON_NAME = "upperOrderButton";
    public static final String LOWER_ORDER_BUTTON_NAME = "bottomOrderButton";
}
