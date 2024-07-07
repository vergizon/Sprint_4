import pages.MainPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

public class RepliesTest extends BaseTest {

    private final int dropDownItemId;
    private final String expectedText;

    public RepliesTest(int dropDownItemId, String expectedText) {
        this.dropDownItemId = dropDownItemId;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] testingData() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void testGetDropDownText() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openReply(dropDownItemId);
        String actualText = objMainPage.getDropDownItemText(dropDownItemId);
        Assert.assertEquals("Text in replies block with Id = " + dropDownItemId + " is differ", expectedText, actualText);
    }
}
