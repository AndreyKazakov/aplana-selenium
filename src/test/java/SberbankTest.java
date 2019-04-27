import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SberbankTest {
    private WebDriver driver;
    private String url;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");

        driver = new ChromeDriver();
        url = "http://www.sberbank.ru/ru/person";
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void SberTest1() {

        //Переход на страницу http://www.sberbank.ru/ru/person и нажатие на Страхование
        driver.get(url);
        driver.findElement(By.xpath("//div[@role='navigation']//span[text()='Страхование']")).click();

        //Дожидаемся, что "Путешествие и покупки" появилось и затем нажимаем на него.
        WebElement btnTripsAndPurchasesMenuItem = driver.findElement(By.xpath("//div[contains(@id,'submenu')]//ul//li//a[text()='Путешествия и покупки']"));
        Wait<WebDriver> wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOf(btnTripsAndPurchasesMenuItem));
        btnTripsAndPurchasesMenuItem.click();

        //Дожидаемся заголовка "Страхование путешественников"
        WebElement textTripsAndPurchasesTitle = driver.findElement(By.xpath("//h3[text()='Страхование путешественников']"));
        wait.until(ExpectedConditions.visibilityOf(textTripsAndPurchasesTitle));

        //Запоминаем хендлы всех окон до того, как откроется новое окно
        ArrayList<String> windowsHandlesBeforeClick = new ArrayList<String>(driver.getWindowHandles());

        //Находим кнопку "Оформить Онлайн" именну ту, которая лежит рядом с заголовком "Страхование путешественников" и кликаем в нее
        WebElement btnOrderOnline = driver.findElement(By.xpath("//h3[text()='Страхование путешественников']/ancestor::div[contains(@class,'sbrf-div-list-inner')][1]/following-sibling::div//a[text()='Оформить онлайн']"));
        btnOrderOnline.click();

        //Дожидаемся, что количество окон стало на один больше
        wait.until(ExpectedConditions.numberOfWindowsToBe(windowsHandlesBeforeClick.size()+1));

        // Находим хендл нового открывшегося окна и переключаемся к нему
        ArrayList<String> windowsHandlesAfterClick = new ArrayList<String>(driver.getWindowHandles());
        windowsHandlesAfterClick.removeAll(windowsHandlesBeforeClick);
        String newWindow = windowsHandlesAfterClick.get(0);
        driver.switchTo().window(newWindow);

        //Дожидаемся появления заголовка "Выбор полиса"
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Выбор полиса']"))));

        //Выбираем сумму страховой защиты – "Минимальная"
        WebElement textMinSum = driver.findElement(By.xpath("//*[text()='Выберите сумму страховой защиты']/following-sibling::div//div[text()='Минимальная']"));
        textMinSum.click();

        //Перемещаемся к кнопке "Оформить" и нажимаем ее
        WebElement btnOrder = driver.findElement(By.xpath("//span[contains(@ng-click,'save') and text()='Оформить']"));
        moveToElementByJS(btnOrder);
        btnOrder.click();

        //Дожидаемся появления поля Фамилия застрахованного, как свидельство того, что мы видим вкладку "Оформить"
        //Определяем элементы и заполняем их:
        //Фамилию и Имя, Дату рождения застрахованных
        //Данные страхователя: Фамилия, Имя, Отчество, Дата рождения, Пол
        //Паспортные данные
        WebElement inputInsuredSurname = driver.findElement(By.xpath("//input[@name='insured0_surname']"));
        wait.until(ExpectedConditions.visibilityOf(inputInsuredSurname));
        WebElement inputInsuredName = driver.findElement(By.xpath("//input[@name='insured0_name']"));
        WebElement inputInsuredBirthDate = driver.findElement(By.xpath("//input[@name='insured0_birthDate']"));

        WebElement inputSurname = driver.findElement(By.xpath("//input[@name='surname']"));
        WebElement inputName = driver.findElement(By.xpath("//input[@name='name']"));
        WebElement inputMiddlename = driver.findElement(By.xpath("//input[@name='middlename']"));
        WebElement inputBirthDate = driver.findElement(By.xpath("//input[@name='birthDate']"));
        WebElement btnFemale = driver.findElement(By.xpath("//input[@type='radio' and @name='female']"));

        WebElement inputPassportSeries = driver.findElement(By.xpath("//input[@name='passport_series']"));
        WebElement inputPassportNumber = driver.findElement(By.xpath("//input[@name='passport_number']"));
        WebElement inputIssueDate = driver.findElement(By.xpath("//input[@name='issueDate']"));
        WebElement textAreaIssuePlace = driver.findElement(By.xpath("//textarea[@name='issuePlace']"));

        fillTextField(inputInsuredSurname, "Petrov");
        fillTextField(inputInsuredName, "Ivan");
        fillTextField(inputInsuredBirthDate, "05.11.1993");
        fillTextField(inputSurname, "Смирнова");
        fillTextField(inputName, "Елена");
        fillTextField(inputMiddlename, "Борисовна");
        fillTextField(inputBirthDate, "05.05.1997");
        btnFemale.click();
        fillTextField(inputPassportSeries, "1234");
        fillTextField(inputPassportNumber, "567890");
        fillTextField(inputIssueDate, "26.05.2017");
        fillTextField(textAreaIssuePlace, "ОВД Центрального р-на");


        //Проверяем, что все поля заполнены правильно
        Assert.assertEquals("Фамилия застрахованного введена некорректно","Petrov", inputInsuredSurname.getAttribute("value"));
        Assert.assertEquals("Имя застрахованного введено некорректно", "Ivan", inputInsuredName.getAttribute("value"));
        Assert.assertEquals("Дата рождения застрахованного введена некорректно", "05.11.1993", inputInsuredBirthDate.getAttribute("value"));
        Assert.assertEquals("Фамилия страхователя введена некорректно", "Смирнова", inputSurname.getAttribute("value"));
        Assert.assertEquals("Имя страхователя введено некорректно","Елена", inputName.getAttribute("value"));
        Assert.assertEquals("Отчество страхователя введено некорректно","Борисовна", inputMiddlename.getAttribute("value"));
        Assert.assertEquals("Дата рождения страхователя введена некорректно","05.05.1997", inputBirthDate.getAttribute("value"));
        Assert.assertTrue("Пол страхователя введен некорректно", btnFemale.isSelected());
        Assert.assertEquals("Серия паспорта введена некорректно","1234", inputPassportSeries.getAttribute("value"));
        Assert.assertEquals("Номер паспорта введен некорректно","567890", inputPassportNumber.getAttribute("value"));
        Assert.assertEquals("Дата выдачи паспорта введена некорректно","26.05.2017", inputIssueDate.getAttribute("value"));
        Assert.assertEquals("Кем выдан введено некорректно","ОВД Центрального р-на", textAreaIssuePlace.getAttribute("value"));

        //Нажимаем Продолжить
        WebElement btnContinue = driver.findElement(By.xpath("//span[text()='Продолжить']"));
        moveToElementByJS(btnContinue);
        btnContinue.click();

        //Проверяем, что появилось сообщение - Заполнены не все обязательные поля
        Assert.assertTrue("Отсутствует сообщение: Заполнены не все обязательные поля", isElementPresent(By.xpath("//div[text()='Заполнены не все обязательные поля']")));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    //метод заполнения тектовых полей с предварительной очисткой
    private void fillTextField(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    //метод подкрутки к элементу с помощью JS
    private void moveToElementByJS(WebElement element){
        ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollIntoView(true);", element);
    }

    private boolean isElementPresent(By locator){
        try{
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return driver.findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }finally {
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        }
    }


}
