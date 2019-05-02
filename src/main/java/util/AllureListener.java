package util;

import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.junit.AllureRunListener;
import steps.BaseSteps;

public class AllureListener extends AllureRunListener  {

    @Override
    public void testFailure(Failure failure) {
        BaseSteps.takeScreenshot();
        super.testFailure(failure);
    }

}
