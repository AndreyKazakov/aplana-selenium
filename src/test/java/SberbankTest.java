import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class SberbankTest {
    private WebDriver driver;
    private String url;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();
        url = "http://www.sberbank.ru/ru/person";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void SberTest1() {
        driver.get(url);
        driver.findElement(By.xpath("//div[@role='navigation']//span[text()='Страхование']")).click();

        WebElement tripsAndPurchasesMenuItem = driver.findElement(By.xpath("//div[contains(@id,'submenu')]//ul//li//a[text()='Путешествия и покупки']"));
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(tripsAndPurchasesMenuItem));




        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
