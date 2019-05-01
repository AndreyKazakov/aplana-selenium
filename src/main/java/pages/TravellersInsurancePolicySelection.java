package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravellersInsurancePolicySelection extends BasePage {

    @FindBy(xpath = "//span[text()='Выбор полиса']")
    public WebElement textFirstTabHeader;

    @FindBy(xpath = "//*[text()='Выберите сумму страховой защиты']/following-sibling::div//div[text()='Минимальная']")
    public WebElement textMinSum;

    @FindBy(xpath = "//span[contains(@ng-click,'save') and text()='Оформить']")
    public WebElement btnOrder;

    public TravellersInsurancePolicySelection(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(textFirstTabHeader));
    }
}
