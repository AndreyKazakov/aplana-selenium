package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import steps.BaseSteps;
import util.TestProperties;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public void fillTextField(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    public boolean isElementDisplayed(By locator){
        try{
            BaseSteps.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return BaseSteps.getDriver().findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }finally {
            BaseSteps.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(TestProperties.getInstance().getProperties().getProperty("defaultTimeout")), TimeUnit.SECONDS);
        }

    }

    public void moveMouseOverElement(WebElement element){
        Actions actions = new Actions(BaseSteps.getDriver());
        actions.moveToElement(element);
        actions.pause(Duration.ofSeconds(1));
        actions.perform();
    }


}
