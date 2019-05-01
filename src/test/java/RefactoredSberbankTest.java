import org.junit.Test;
import pages.InsuranceTravelAndShopping;
import pages.MainPage;
import pages.TravellersInsuranceOrder;
import pages.TravellersInsurancePolicySelection;

import java.util.ArrayList;

public class RefactoredSberbankTest extends BaseTest {

    @Test
    public void newSberTest() throws Exception {

        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.moveMouseOverTopMenuItem("Страхование");
        mainPage.selectFromInsuranceSubMenu("Путешествия и покупки");

        ArrayList<String> windowsHandlesBeforeClick = new ArrayList<String>(driver.getWindowHandles());

        new InsuranceTravelAndShopping(driver).btnOrderOnline.click();

        switchToJustOpenedWindow(windowsHandlesBeforeClick);

        TravellersInsurancePolicySelection policySelectionPage = new TravellersInsurancePolicySelection(driver);
        policySelectionPage.textMinSum.click();
        moveToElementByJS(policySelectionPage.btnOrder);
        policySelectionPage.btnOrder.click();

        TravellersInsuranceOrder orderPage = new TravellersInsuranceOrder(driver);
        orderPage.fillTextField("Фамилия застрахованного", "Petrov");
        orderPage.fillTextField("Имя застрахованного", "Ivan");
        orderPage.fillTextField("Дата рождения застрахованного", "05.11.1993");
        orderPage.fillTextField("Фамилия", "Смирнова");
        orderPage.fillTextField("Имя", "Елена");
        orderPage.fillTextField("Отчество", "Борисовна");
        orderPage.fillTextField("Дата рождения", "05.05.1997");
        orderPage.btnFemale.click();
        orderPage.fillTextField("Серия паспорта", "1234");
        orderPage.fillTextField("Номер паспорта", "567890");
        orderPage.fillTextField("Дата выдачи", "26.05.2017");
        orderPage.fillTextField("Кем выдан", "ОВД Центрального р-на");

        orderPage.checkFieldValue("Фамилия застрахованного", "Petrov");
        orderPage.checkFieldValue("Имя застрахованного", "Ivan");
        orderPage.checkFieldValue("Дата рождения застрахованного", "05.11.1993");
        orderPage.checkFieldValue("Фамилия", "Смирнова");
        orderPage.checkFieldValue("Имя", "Елена");
        orderPage.checkFieldValue("Отчество", "Борисовна");
        orderPage.checkFieldValue("Дата рождения", "05.05.1997");
        orderPage.checkFieldIsSelected(orderPage.btnFemale);
        orderPage.checkFieldValue("Серия паспорта", "1234");
        orderPage.checkFieldValue("Номер паспорта", "567890");
        orderPage.checkFieldValue("Дата выдачи", "26.05.2017");
        orderPage.checkFieldValue("Кем выдан", "ОВД Центрального р-на");

        moveToElementByJS(orderPage.btnContinue);
        orderPage.btnContinue.click();

        orderPage.checkVisibilityOfErrorMessage("Заполнены не все обязательные поля");
    }
}
