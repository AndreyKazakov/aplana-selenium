package steps;

import pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

public class MainSteps {

    @Step("выбран пункт верхнего меню: {0}")
    public void stepSelectTopMenuItem(String menuItem){
        new MainPage().moveMouseOverTopMenuItem(menuItem);
    }

    @Step("выбран вид страхования: {0}")
    public void stepSelectInsuranceSubItem(String menuItem){
        new MainPage().selectFromInsuranceSubMenu(menuItem);
    }
}
