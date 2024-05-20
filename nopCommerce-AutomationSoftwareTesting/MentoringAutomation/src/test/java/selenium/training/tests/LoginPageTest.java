package selenium.training.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import selenium.training.pages.LoginPage;
import static selenium.training.utils.GlobalConfigs.EMAIL;
import static selenium.training.utils.GlobalConfigs.PASSWORD;

@Listeners(selenium.training.utils.Listeners.class)
public class LoginPageTest {

    private final LoginPage loginPage;

    public LoginPageTest() {
        loginPage = new LoginPage();
    }

    @Test
    public void successfulLoginTest() {
        loginPage.navigateToLoginPage();
        loginPage.login(EMAIL, PASSWORD);

        String expectedSuccessMessage = "Welcome to our store";
        Assert.assertEquals(loginPage.getSuccessMessage(), expectedSuccessMessage, "Login was not successful");
        loginPage.logout();
    }

    @Test
    public void unsuccessfulLoginTest() {
        loginPage.navigateToLoginPage();
        loginPage.login("WrongEmail@gmail.com", "WrongPassword");
        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
    }
}
