package selenium.training.pages;

import org.openqa.selenium.support.PageFactory;
import selenium.training.utils.Driver;

import java.time.Duration;

public class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
