package selenium.training.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import selenium.training.pages.RegistrationPage;

import static selenium.training.utils.GlobalConfigs.*;

@Listeners(selenium.training.utils.Listeners.class)
public class RegistrationPageTest {

    private final RegistrationPage registrationPage;

    public RegistrationPageTest() {
        registrationPage = new RegistrationPage();
    }

    @Test
    public void successfulRegistrationTest() {
        registrationPage.navigateToRegistrationPage();

        String actualTitle = "Register";
        Assert.assertEquals(actualTitle, registrationPage.getPageTitle());

        registrationPage.register(FIRST_NAME, LAST_NAME, BIRTH_DAY, BIRTH_MONTH,BIRTH_YEAR, EMAIL, COMPANY, PASSWORD, PASSWORD);

        Assert.assertEquals(registrationPage.getRegistrationResult(), "Your registration completed");
        registrationPage.logout();
    }
}

