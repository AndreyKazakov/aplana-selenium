package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "//div[@role='navigation']//ul[contains(@class,'lg-menu__list')]")
    WebElement topMenu;

    @FindBy(xpath = "//span[text()='Страхование' and contains(@class,'lg-menu')]/ancestor::button[contains(@class,'lg-menu')]/following-sibling::div//ul[contains(@class,'lg-menu__sub-list')]")
    WebElement insuranceSubMenu;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectFromMainMenu(String menuItem){
        topMenu.findElement(By.xpath(".//*[text()='"+menuItem+"']")).click();
    }

    public void selectFromInsuranceSubMenu(String menuItem){
        insuranceSubMenu.findElement(By.xpath(".//*[text()='"+menuItem+"']")).click();
    }

}
