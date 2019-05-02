import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import steps.*;

import java.util.HashMap;

public class SberbankTestWithAllureReport extends BaseSteps {

    BaseSteps baseSteps = new BaseSteps();
    MainSteps mainSteps = new MainSteps();
    InsuranceTravelAndShoppingSteps insuranceTravelAndShoppingSteps = new InsuranceTravelAndShoppingSteps();
    TravellersInsurancePolicySelectionSteps travellersInsurancePolicySelectionSteps = new TravellersInsurancePolicySelectionSteps();
    TravellersInsuranceOrderSteps travellersInsuranceOrderSteps = new TravellersInsuranceOrderSteps();
    HashMap<String, String > testData = new HashMap<>();

    @Title("Отправка заявки на страхование путешественников без персональных данных")
    @Test
    public void sberbankInsuranceTestWithoutPersonalData(){

        testData.put("Фамилия застрахованного", "Petrov");
        testData.put("Имя застрахованного", "Ivan");
        testData.put("Дата рождения застрахованного", "05.11.1993");
        testData.put("Фамилия", "Смирнова");
        testData.put("Имя", "Елена");
        testData.put("Отчество", "Борисовна");
        testData.put("Дата рождения", "05.05.1997");
        testData.put("Серия паспорта", "1234");
        testData.put("Номер паспорта", "567890");
        testData.put("Дата выдачи", "26.05.2017");
        testData.put("Кем выдан", "ОВД Центрального р-на");

        baseSteps.stepGoToMainPage();
        mainSteps.stepSelectTopMenuItem("Страхование");
        mainSteps.stepSelectInsuranceSubItem("Путешествия и покупки");
        insuranceTravelAndShoppingSteps.checkHeaderDisplaying("Страхование путешественников");
        baseSteps.stepTakeScreenshot();
        insuranceTravelAndShoppingSteps.stepClickOrderOnlineButtonAndSwitchToNewWindow();
        travellersInsurancePolicySelectionSteps.stepSelectInsuranceSum();
        travellersInsurancePolicySelectionSteps.stepClickOfferButton();
        travellersInsuranceOrderSteps.stepFillTextFields(testData);
        travellersInsuranceOrderSteps.stepSelectFemale();
        baseSteps.stepTakeScreenshot();
        travellersInsuranceOrderSteps.stepCheckFieldsValues(testData);
        travellersInsuranceOrderSteps.stepCheckFemaleSelected();
        travellersInsuranceOrderSteps.stepClickContinueButton();
        travellersInsuranceOrderSteps.checkErrorMessageDisplaying("Заполнены не все обязательные поля");
        baseSteps.stepTakeScreenshot();

    }
}
