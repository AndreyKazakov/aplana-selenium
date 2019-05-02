package steps;

import pages.InsuranceTravelAndShopping;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;

public class InsuranceTravelAndShoppingSteps extends BaseSteps {

    @Step("на странице отображается заголовок - {0}")
    public void checkHeaderDisplaying(String headerText) {
        new InsuranceTravelAndShopping().checkVisibilityOfHeader(headerText);
    }

    @Step("выполнено нажатие на кнопку Оформить Онлайн и переключение к новому открывшемуся окну")
    public void stepClickOrderOnlineButtonAndSwitchToNewWindow() {
        ArrayList<String> windowsHandlesBeforeClick = new ArrayList<String>(driver.getWindowHandles());
        new InsuranceTravelAndShopping().btnOrderOnline.click();
        switchToJustOpenedWindow(windowsHandlesBeforeClick);
    }
}
