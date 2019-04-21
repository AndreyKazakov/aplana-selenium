import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
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

        WebElement btnTripsAndPurchasesMenuItem = driver.findElement(By.xpath("//div[contains(@id,'submenu')]//ul//li//a[text()='Путешествия и покупки']"));
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(btnTripsAndPurchasesMenuItem));
        btnTripsAndPurchasesMenuItem.click();

        WebElement textTripsAndPurchasesTitle = driver.findElement(By.xpath("//h3[text()='Страхование путешественников']"));
        wait.until(ExpectedConditions.visibilityOf(textTripsAndPurchasesTitle));

        String initialWindow = driver.getWindowHandle();
        ArrayList<String> windowsHandlesBeforeClick = new ArrayList<String>(driver.getWindowHandles());


        WebElement btnOrderOnline = driver.findElement(By.xpath("//h3[text()='Страхование путешественников']/ancestor::div[contains(@class,'sbrf-div-list-inner')][1]/following-sibling::div//a[text()='Оформить онлайн']"));
        btnOrderOnline.click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(windowsHandlesBeforeClick.size()+1));

        ArrayList<String> windowsHandlesAfterClick = new ArrayList<String>(driver.getWindowHandles());
        windowsHandlesAfterClick.removeAll(windowsHandlesBeforeClick);
        String newWindow = windowsHandlesAfterClick.get(0);

        driver.switchTo().window(newWindow);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Выбор полиса']"))));

        WebElement textMinSum = driver.findElement(By.xpath("//*[text()='Выберите сумму страховой защиты']/following-sibling::div//div[text()='Минимальная']"));
        textMinSum.click();

        WebElement btnOrder = driver.findElement(By.xpath("//span[contains(@ng-click,'save') and text()='Оформить']"));
        btnOrder.click();


        WebElement inputInsuredSurname = driver.findElement(By.xpath("//input[@name='insured0_surname']"));
        WebElement inputInsuredName = driver.findElement(By.xpath("//input[@name='insured0_name']"));
        WebElement inputInsuredBirthDate = driver.findElement(By.xpath("//input[@name='insured0_birthDate']"));
        wait.until(ExpectedConditions.visibilityOf(inputInsuredSurname));

        WebElement inputSurname = driver.findElement(By.xpath("//input[@name='surname']"));
        WebElement inputName = driver.findElement(By.xpath("//input[@name='name']"));
        WebElement inputMiddlename = driver.findElement(By.xpath("//input[@name='middlename']"));
        WebElement inputBirthDate = driver.findElement(By.xpath("//input[@name='birthDate']"));
        WebElement btnFemale = driver.findElement(By.xpath("//input[@type='radio' and @name='female']"));

        fillTextField(inputInsuredSurname, "Petrov");
        fillTextField(inputInsuredName, "Ivan");
        fillFieldByJS(inputInsuredBirthDate, "10.10.1980");
        fillTextField(inputSurname, "Смирнова");
        fillTextField(inputName, "Елена");
        fillTextField(inputMiddlename, "Борисовна");
        fillFieldByJS(inputBirthDate, "08.11.1982");
        btnFemale.click();

        WebElement inputPassportSeries = driver.findElement(By.xpath("//input[@name='passport_series']"));
        WebElement inputPassportNumber = driver.findElement(By.xpath("//input[@name='passport_number']"));
        WebElement inputIssueDate = driver.findElement(By.xpath("//input[@name='issueDate']"));
        WebElement textAreaIssuePlace = driver.findElement(By.xpath("//textarea[@name='issuePlace']"));

        fillTextField(inputPassportSeries, "1234");
        fillTextField(inputPassportNumber, "567890");
        fillFieldByJS(inputIssueDate, "05.06.2004");
        fillTextField(textAreaIssuePlace, "ОВД Центрального р-на");

        //проверка правильности ввода всех значений


        //нажатие Продолжить
        WebElement btnContinue = driver.findElement(By.xpath("//span[text()='Продолжить']"));





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

    private void fillTextField(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    private void moveToElementByJS(WebElement element){
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true);", element);
    }

    private void fillFieldByJS(WebElement element, String value){
        //((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=''", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=\'"+value+"\'", element);

//        for (int i=0; i<value.length(); i++) {
//            element.sendKeys(String.valueOf(value.charAt(i)));
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        //element.sendKeys(Keys.ENTER);
    }

}
