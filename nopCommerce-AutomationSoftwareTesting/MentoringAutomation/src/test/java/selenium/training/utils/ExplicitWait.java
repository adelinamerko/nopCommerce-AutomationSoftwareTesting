package selenium.training.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {
    private static final long TIME_IN_SEC = 20;
    private static WebDriverWait wait;

    public static WebDriverWait getWait() {
        if (wait == null) {
            initWait(Driver.getDriver(), TIME_IN_SEC);
        }
        return wait;
    }

    public static WebDriverWait getWait(long timeoutInSeconds) {
        if (wait == null || timeoutInSeconds != TIME_IN_SEC) {
            initWait(Driver.getDriver(), timeoutInSeconds);
        }
        return wait;
    }

    private static void initWait(WebDriver driver, long timeoutInSeconds) {
        try {
            Duration duration = Duration.ofSeconds(timeoutInSeconds);
            wait = new WebDriverWait(driver, duration);
        } catch (Exception e) {
            System.out.println("Web Driver is not Initialized " + e.getMessage());
        }
    }
}