import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
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

    //метод заполнения тектовых полей с предварительной очисткой
    protected void fillTextField(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    //метод подкрутки к элементу с помощью JS
    protected void moveToElementByJS(WebElement element){
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true);", element);
    }

    //метод проверки отображения элемента на странице
    protected boolean isElementPresent(By locator){
        try{
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return driver.findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("defaultTimeout")), TimeUnit.SECONDS);
        }
    }

}
