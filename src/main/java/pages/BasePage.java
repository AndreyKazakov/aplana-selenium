package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class BasePage {
    WebDriver driver;

    public void fillTextField(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    public boolean isElementDisplayed(By locator){
        try{
            return driver.findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void checkFieldValue(WebElement element, String value) {
        Assert.assertEquals(String.format("Значение поля [%s] не соответствует ожидаемому [%s], было получено [%s]", element, value, element.getAttribute("value")),value, element.getAttribute("value"));
    }

    public void checkFieldIsSelected(WebElement element) {
        Assert.assertTrue(String.format("Элемент [%s] не выбран", element), element.isSelected());
    }

    public void moveMouseOverElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.pause(Duration.ofSeconds(1));
        actions.perform();
    }


}
