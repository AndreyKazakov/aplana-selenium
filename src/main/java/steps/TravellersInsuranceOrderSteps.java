package steps;

import org.junit.Assert;
import pages.TravellersInsuranceOrder;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashMap;

public class TravellersInsuranceOrderSteps {

    @Step("поле {0} заполняется значением {1}")
    public void stepFillTextField(String fieldName, String value) {
        new TravellersInsuranceOrder().fillTextField(fieldName, value);
    }

    @Step("заполняются поля:")
    public void stepFillTextFields(HashMap<String, String> fields) {
        fields.forEach(this::stepFillTextField);
    }

    @Step("выбирается Пол - Женский")
    public void stepSelectFemale() {
        new TravellersInsuranceOrder().btnFemale.click();
    }

    @Step("значение поля {0} равно {1}")
    public void stepCheckFieldValue(String fieldName, String value) {
        String actual = new TravellersInsuranceOrder().getTextFieldValue(fieldName);
        Assert.assertEquals(String.format("Значение поля [%s] не равно [%s]. Было получено [%s]", fieldName, value, actual),value, actual);
    }

    @Step("проверена корректность заполнения полей")
    public void stepCheckFieldsValues(HashMap<String, String> fields) {
        fields.forEach(this::stepCheckFieldValue);
    }

    @Step("в поле Пол выбрано - Женский")
    public void stepCheckFemaleSelected() {
        TravellersInsuranceOrder page = new TravellersInsuranceOrder();
        Assert.assertTrue(String.format("в поле Пол не выбрано значение Женский"), page.btnFemale.isSelected());
    }

    @Step("на странице отображается сообщение об ошибке {0}")
    public void stepCheckErrorMessageDisplaying(String errorMessage) {
        new TravellersInsuranceOrder().checkVisibilityOfErrorMessage(errorMessage);
    }

    @Step("выполнено нажатие на кнопку - Продолжить")
    public void stepClickContinueButton(){
        TravellersInsuranceOrder page = new TravellersInsuranceOrder();
        page.moveToElementByJS(page.btnContinue);
        page.btnContinue.click();
    }
}
