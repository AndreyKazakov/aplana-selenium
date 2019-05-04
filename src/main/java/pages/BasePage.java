package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;
import util.TestProperties;

import java.time.Duration;
import java.util.ArrayList;
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

    //метод подкрутки к элементу с помощью JS
    public void moveToElementByJS(WebElement element){
        ((JavascriptExecutor) BaseSteps.getDriver()).executeScript("return arguments[0].scrollIntoView(true);", element);
    }

    // метод переключения к только что открытому (открытому последним) окну.
    // В качестве параметра надо передать список хендлов окон, которые были открыты до открытия последнего окна.
    public void switchToJustOpenedWindow(ArrayList<String> windowsHandlesBeforeClick) {
        WebDriverWait wait = new WebDriverWait(BaseSteps.getDriver(), Integer.parseInt(TestProperties.getInstance().getProperties().getProperty("defaultTimeout")));
        wait.withMessage("Количество окон не изменилось");
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.numberOfWindowsToBe(windowsHandlesBeforeClick.size() + 1));

        try {
            ArrayList<String> windowsHandlesAfterClick = new ArrayList<String>(BaseSteps.getDriver().getWindowHandles());
            windowsHandlesAfterClick.removeAll(windowsHandlesBeforeClick);
            String newWindow = windowsHandlesAfterClick.get(0);
            BaseSteps.getDriver().switchTo().window(newWindow);
        }catch (IndexOutOfBoundsException e) {
            throw new AssertionError("Новое окно не обнаружено");
        }
    }


}
