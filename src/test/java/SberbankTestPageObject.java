import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pages.MainPage;

public class SberbankTestPageObject extends BaseTest {

    @Test
    public void newSberTest() {

        driver.get(baseUrl);

        MainPage mainPage = new MainPage(driver);
        mainPage.moveMouseOverMainMenu(driver, "Страхование");
        mainPage.selectFromSubMenu("Путешествия и покупки");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
