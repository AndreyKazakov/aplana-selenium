import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    protected static String baseUrl;
    public static Properties properties = TestProperties.getInstance().getProperties();

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

    //метод подкрутки к элементу с помощью JS
    protected void moveToElementByJS(WebElement element){
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true);", element);
    }

    // метод переключения к только что открытому (открытому последним) окну.
    // В качестве параметра надо передать список хендлов окон, которые были открыты до открытия последнего окна.
    protected void switchToJustOpenedWindow(ArrayList<String> windowsHandlesBeforeClick) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage("Количество окон не изменилось");
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.numberOfWindowsToBe(windowsHandlesBeforeClick.size() + 1));

        ArrayList<String> windowsHandlesAfterClick = new ArrayList<String>(driver.getWindowHandles());
        windowsHandlesAfterClick.removeAll(windowsHandlesBeforeClick);
        String newWindow = windowsHandlesAfterClick.get(0);
        driver.switchTo().window(newWindow);
    }

}
