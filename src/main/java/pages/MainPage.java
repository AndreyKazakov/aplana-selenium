package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends BasePage {

    @FindBy(xpath = "//div[@role='navigation']//ul[contains(@class,'lg-menu__list')]")
    WebElement topMenu;

    @FindBy(xpath = "//span[text()='Страхование' and contains(@class,'lg-menu')]/ancestor::button[contains(@class,'lg-menu')]/following-sibling::div")
    WebElement insuranceSubMenu;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void selectFromTopMenu(String menuItem){
        topMenu.findElement(By.xpath(".//*[text()='"+menuItem+"']")).click();
    }

    // было замечено, что подменю показывается уже по наведению курсора мыши на пункт главного меню, и иногда
    // клик в этот же пункт наоборот закрывает уже открытое подменю. Поэтому решил вызывать подменю просто наведением мыши.
    public void moveMouseOverTopMenuItem(String menuItem){
        moveMouseOverElement(topMenu.findElement(By.xpath(".//*[text()='"+menuItem+"']")));
    }

    public void selectFromInsuranceSubMenu(String subMenuItem){
        insuranceSubMenu.findElement(By.xpath(".//*[text()='"+subMenuItem+"']")).click();
    }

}
