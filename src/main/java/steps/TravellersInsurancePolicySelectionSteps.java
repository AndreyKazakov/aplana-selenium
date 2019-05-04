package steps;

import pages.TravellersInsurancePolicySelection;
import ru.yandex.qatools.allure.annotations.Step;

public class TravellersInsurancePolicySelectionSteps {

    @Step("выбирается сумма страховой защиты – Минимальная")
    public void stepSelectInsuranceSum() {
        new TravellersInsurancePolicySelection().textMinSum.click();
    }

    @Step("выполнено нажатие на кнопку - Оформить")
    public void stepClickOfferButton(){
        TravellersInsurancePolicySelection page = new TravellersInsurancePolicySelection();
        page.moveToElementByJS(page.btnOrder);
        page.btnOrder.click();
    }

}
