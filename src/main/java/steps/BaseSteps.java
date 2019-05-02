package steps;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import util.TestProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {
    protected static WebDriver driver;
    protected static String baseUrl;
    public static Properties properties = TestProperties.getInstance().getProperties();

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public static void setUp() throws Exception {

        switch (properties.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
        }

        baseUrl = properties.getProperty("app.url");
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("defaultTimeout")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Attachment(type = "image/png", value = "Скриншот")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot)BaseSteps.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Step("выполнен скриншот текущего окна")
    public void stepTakeScreenshot() {
        takeScreenshot();
    }

    @Step("выполнен переход на главную страницу")
    public void stepGoToMainPage() {
        driver.get(baseUrl);
    }

    //метод подкрутки к элементу с помощью JS
    protected void moveToElementByJS(WebElement element){
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true);", element);
    }

    // метод переключения к только что открытому (открытому последним) окну.
    // В качестве параметра надо передать список хендлов окон, которые были открыты до открытия последнего окна.
    protected void switchToJustOpenedWindow(ArrayList<String> windowsHandlesBeforeClick) {
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(TestProperties.getInstance().getProperties().getProperty("defaultTimeout")));
        wait.withMessage("Количество окон не изменилось");
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.numberOfWindowsToBe(windowsHandlesBeforeClick.size() + 1));

        try {
            ArrayList<String> windowsHandlesAfterClick = new ArrayList<String>(driver.getWindowHandles());
            windowsHandlesAfterClick.removeAll(windowsHandlesBeforeClick);
            String newWindow = windowsHandlesAfterClick.get(0);
            driver.switchTo().window(newWindow);
        }catch (IndexOutOfBoundsException e) {
            throw new AssertionError("Новое окно не обнаружено");
        }
    }

}
