package steps;

import pages.InsuranceTravelAndShopping;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;

public class InsuranceTravelAndShoppingSteps {

    @Step("на странице отображается заголовок - {0}")
    public void stepCheckHeaderDisplaying(String headerText) {
        new InsuranceTravelAndShopping().checkVisibilityOfHeader(headerText);
    }

    @Step("выполнено нажатие на кнопку Оформить Онлайн и переключение к новому открывшемуся окну")
    public void stepClickOrderOnlineButtonAndSwitchToNewWindow() {
        ArrayList<String> windowsHandlesBeforeClick = new ArrayList<String>(BaseSteps.getDriver().getWindowHandles());
        InsuranceTravelAndShopping page = new InsuranceTravelAndShopping();
        page.btnOrderOnline.click();
        page.switchToJustOpenedWindow(windowsHandlesBeforeClick);
    }
}
