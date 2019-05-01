package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InsuranceTravelAndShopping extends BasePage {

    @FindBy(xpath = "//h3[text()='Страхование путешественников']")
    public WebElement textTripsAndPurchasesTitle;

    @FindBy(xpath = "//h3[text()='Страхование путешественников']/ancestor::div[contains(@class,'sbrf-div-list-inner')][1]/following-sibling::div//a[text()='Оформить онлайн']")
    public WebElement btnOrderOnline;

    public InsuranceTravelAndShopping(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(textTripsAndPurchasesTitle));
    }

}
