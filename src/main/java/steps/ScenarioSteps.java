package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScenarioSteps {

    BaseSteps baseSteps = new BaseSteps();
    MainSteps mainSteps = new MainSteps();
    InsuranceTravelAndShoppingSteps insuranceTravelAndShoppingSteps = new InsuranceTravelAndShoppingSteps();
    TravellersInsurancePolicySelectionSteps travellersInsurancePolicySelectionSteps = new TravellersInsurancePolicySelectionSteps();
    TravellersInsuranceOrderSteps travellersInsuranceOrderSteps = new TravellersInsuranceOrderSteps();


    @When("^выполнен переход на главную страницу$")
    public void stepGoToMainPage(){
        baseSteps.stepGoToMainPage();
    }

    @When("^выбирается пункт верхнего меню:\"(.*)\"$")
    public void stepSelectTopMenuItem(String menuItem) {
        mainSteps.stepSelectTopMenuItem(menuItem);
    }

    @When("^выбирается вид страхования:\"(.*)\"$")
    public void stepSelectInsuranceSubItem(String menuItem){
        mainSteps.stepSelectInsuranceSubItem(menuItem);
    }

    @Then("^на странице отображается заголовок \"(.*)\"$")
    public void checkHeaderDisplaying(String headerText) {
        insuranceTravelAndShoppingSteps.stepCheckHeaderDisplaying(headerText);
    }

    @When("^выполнено нажатие на кнопку Оформить Онлайн и переключение к новому открывшемуся окну$")
    public void stepClickOrderOnlineButtonAndSwitchToNewWindow() {
        insuranceTravelAndShoppingSteps.stepClickOrderOnlineButtonAndSwitchToNewWindow();
    }

    @When("^выбирается сумма страховой защиты – Минимальная$")
    public void stepSelectInsuranceSum() {
        travellersInsurancePolicySelectionSteps.stepSelectInsuranceSum();
    }

    @When("^выполнено нажатие на кнопку - Оформить$")
    public void stepClickOfferButton(){
        travellersInsurancePolicySelectionSteps.stepClickOfferButton();
    }

    @When("^заполняются поля:$")
    public void stepFillTextFields(DataTable fields) {
        fields.asMap(String.class, String.class).forEach((key, value) -> travellersInsuranceOrderSteps.stepFillTextField(key, value));
    }

    @When("^выбирается Пол - Женский$")
    public void stepSelectFemale() {
        travellersInsuranceOrderSteps.stepSelectFemale();
    }

    @Then("^проверяются поля:$")
    public void stepCheckFieldsValues(DataTable fields) {
        fields.asMap(String.class, String.class).forEach((key, value) -> travellersInsuranceOrderSteps.stepCheckFieldValue(key, value));
    }

    @Then("^проверяется, что в поле Пол выбрано - Женский$")
    public void stepCheckFemaleSelected() {
        travellersInsuranceOrderSteps.stepCheckFemaleSelected();
    }

    @When("^выполнено нажатие на кнопку - Продолжить$")
    public void stepClickContinueButton() {
        travellersInsuranceOrderSteps.stepClickContinueButton();
    }

    @Then("^на странице отображается сообщение об ошибке \"(.*)\"$")
    public void checkErrorMessageDisplaying(String errorMessage) {
        travellersInsuranceOrderSteps.stepCheckErrorMessageDisplaying(errorMessage);
    }

    @When("^выполнен скриншот текущего окна$")
    public void stepTakeScreenshot() {
        baseSteps.stepTakeScreenshot();
    }

}
