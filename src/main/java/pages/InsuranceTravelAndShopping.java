package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.BaseSteps;

public class InsuranceTravelAndShopping extends BasePage {

    @FindBy(xpath = "//h3[text()='Страхование путешественников']")
    public WebElement textTripsAndPurchasesTitle;

    @FindBy(xpath = "//h3[text()='Страхование путешественников']/ancestor::div[contains(@class,'sbrf-div-list-inner')][1]/following-sibling::div//a[text()='Оформить онлайн']")
    public WebElement btnOrderOnline;

    public InsuranceTravelAndShopping() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }

    public void checkVisibilityOfHeader(String headerText) {
        String xpath="//h3[text()='"+headerText+"']";
        Assert.assertTrue(String.format("Отсутствует заголовок: [%s]", headerText), isElementDisplayed(By.xpath(xpath)));
    }

}
