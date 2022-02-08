package objectpage_and_allure;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.ByteArrayInputStream;

import static org.example.extensions.CommonPart.webDriver;

public class ActionEventListener extends AbstractWebDriverEventListener {
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES)));
    }
}
