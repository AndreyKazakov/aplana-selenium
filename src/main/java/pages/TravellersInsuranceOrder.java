package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;
import util.TestProperties;

public class TravellersInsuranceOrder extends BasePage {

    @FindBy(xpath="//input[@name='insured0_surname']")
    public WebElement inputInsuredSurname;
    
    @FindBy(xpath="//input[@name='insured0_name']")
    public WebElement inputInsuredName;
    
    @FindBy(xpath="//input[@name='insured0_birthDate']")
    public WebElement inputInsuredBirthDate;

    @FindBy(xpath="//input[@name='surname']")
    public WebElement inputSurname;

    @FindBy(xpath="//input[@name='name']")
    public WebElement inputName;

    @FindBy(xpath="//input[@name='middlename']")
    public WebElement inputMiddlename;

    @FindBy(xpath="//input[@name='birthDate']")
    public WebElement inputBirthDate;

    @FindBy(xpath="//input[@type='radio' and @name='female']")
    public WebElement btnFemale;

    @FindBy(xpath="//input[@name='passport_series']")
    public WebElement inputPassportSeries;

    @FindBy(xpath="//input[@name='passport_number']")
    public WebElement inputPassportNumber;

    @FindBy(xpath="//input[@name='issueDate']")
    public WebElement inputIssueDate;

    @FindBy(xpath="//textarea[@name='issuePlace']")
    public WebElement textAreaIssuePlace;

    @FindBy(xpath="//span[text()='Продолжить']")
    public WebElement btnContinue;


    public TravellersInsuranceOrder() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
        Wait<WebDriver> wait = new WebDriverWait(BaseSteps.getDriver(), Integer.parseInt(TestProperties.getInstance().getProperties().getProperty("defaultTimeout")));
        wait.until(ExpectedConditions.visibilityOf(inputInsuredSurname));
    }

    public void fillTextField(String fieldName, String value) {
        switch (fieldName) {
            case "Фамилия застрахованного":
                fillTextField(inputInsuredSurname, value);
                break;
            case "Имя застрахованного":
                fillTextField(inputInsuredName, value);
                break;
            case "Дата рождения застрахованного":
                fillTextField(inputInsuredBirthDate, value);
                break;
            case "Фамилия":
                fillTextField(inputSurname, value);
                break;
            case "Имя":
                fillTextField(inputName, value);
                break;
            case "Отчество":
                fillTextField(inputMiddlename, value);
                break;
            case "Дата рождения":
                fillTextField(inputBirthDate, value);
                break;
            case "Серия паспорта":
                fillTextField(inputPassportSeries, value);
                break;
            case "Номер паспорта":
                fillTextField(inputPassportNumber, value);
                break;
            case "Дата выдачи":
                fillTextField(inputIssueDate, value);
                break;
            case "Кем выдан":
                fillTextField(textAreaIssuePlace, value);
                break;
            default: throw new AssertionError("Поле '"+fieldName+"' не объявлено на странице");
        }
    }

    public String getTextFieldValue(String fieldName) {
        switch(fieldName) {
            case "Фамилия застрахованного":
               return inputInsuredSurname.getAttribute("value");
            case "Имя застрахованного":
                return inputInsuredName.getAttribute("value");
            case "Дата рождения застрахованного":
                return inputInsuredBirthDate.getAttribute("value");
            case "Фамилия":
                return inputSurname.getAttribute("value");
            case "Имя":
                return inputName.getAttribute("value");
            case "Отчество":
                return inputMiddlename.getAttribute("value");
            case "Дата рождения":
                return inputBirthDate.getAttribute("value");
            case "Серия паспорта":
                return inputPassportSeries.getAttribute("value");
            case "Номер паспорта":
                return inputPassportNumber.getAttribute("value");
            case "Дата выдачи":
                return inputIssueDate.getAttribute("value");
            case "Кем выдан":
                return textAreaIssuePlace.getAttribute("value");
            default:
                throw new AssertionError("Поле '"+fieldName+"' не объявлено на странице");
        }
    }

    public void checkVisibilityOfErrorMessage(String errorMessage) {
        String xpath="//div[text()='"+errorMessage+"']";
        Assert.assertTrue(String.format("Отсутствует сообщение: [%s]", errorMessage), isElementDisplayed(By.xpath(xpath)));
    }

}
