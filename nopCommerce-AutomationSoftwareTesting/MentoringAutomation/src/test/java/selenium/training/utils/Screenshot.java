package selenium.training.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Screenshot {

    static void getScreenshot(String failedMethodName) throws IOException {
        LocalDateTime dateTime = LocalDateTime.now();
        String currentDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss", new Locale("en")));

        File src = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("test-output\\failure_screenshots\\TestFail_"+currentDate+"_"+failedMethodName+".png"));
    }
}
