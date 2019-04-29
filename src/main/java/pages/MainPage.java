package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class MainPage {

    @FindBy(xpath = "//div[@role='navigation']//ul[contains(@class,'lg-menu__list')]")
    WebElement topMenu;

    // если смотреть код страницы, то подменю каждого пункта главного меню - есть div с уникальным id.
    // Для пункта "Страхование" это id="submenu-5"
    // Но я специально не стал привязываться к такому id, а строю xpath для подменю от текста пункта главного меню,
    // т.к. если вдруг номера в id сменятся, то все xpath перестанут работать, а имя пункта меню останется, даже если id изменится

    //@FindBy(xpath = "//span[text()='Страхование' and contains(@class,'lg-menu')]/ancestor::button[contains(@class,'lg-menu')]/following-sibling::")
    @FindBy(xpath = "//div//ul[contains(@class,'lg-menu__sub-list')]")
    WebElement subMenu;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void selectFromMainMenu(String menuItem){
        topMenu.findElement(By.xpath(".//*[text()='"+menuItem+"']")).click();
    }

    // Поскольку каждое подменю имеет свой id, то мы заводили бы каждое подменю как отдельный элемент.
    // Но не хотелось бы иметь для каждого подменю свой метод выбора пункта, т.к. они все были бы однотипные.
    // Поэтому проще сделать один метод на все подменю, и передавать параметром - в каком именно подменю мы выбираем пункт.
    public void selectFromSubMenu(String subMenuItem){
        subMenu.findElement(By.xpath(".//*[text()='"+subMenuItem+"']")).click();
    }

    // было замечено, что подменю показывается уже по наведению курсора мыши на пункт главного меню, и иногда
    // клик в этот же пункт наоборот закрывает уже открытое подменю. Поэтому решил вызывать подменю просто наведением мыши.
    public void moveMouseOverMainMenu(WebDriver driver, String menuItem){
        Actions actions = new Actions(driver);
        actions.moveToElement(topMenu.findElement(By.xpath(".//*[text()='"+menuItem+"']")));
        actions.pause(Duration.ofSeconds(2));
        actions.perform();
    }



}
