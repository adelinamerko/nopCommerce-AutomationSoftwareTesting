package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.training.utils.ExplicitWait;

public class LoginPage extends BasePage {


    private final NavigationMenu navigationMenu;

    public LoginPage() {
        super();
        navigationMenu = new NavigationMenu();
    }

    @FindBy(id = "Email")
    public WebElement emailElement;

    @FindBy(id = "Password")
    public WebElement passwordElement;

    @FindBy(css = ".button-1.login-button")
    public WebElement submitLoginElement;

    @FindBy(css = ".topic-block-title>h2")
    public WebElement successMessageElement;

    @FindBy(css = ".message-error.validation-summary-errors")
    public WebElement errorMessage;

    public void login(String email, String password) {
        emailElement.clear();
        emailElement.sendKeys(email);

        passwordElement.clear();
        passwordElement.sendKeys(password);

        submitLoginElement.click();
    }

    public void logout() {
        navigationMenu.logout();
    }

    public void navigateToLoginPage() {
        navigationMenu.navigateToHomePage();
        navigationMenu.navigateToLoginPage();
    }

    public String getSuccessMessage() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(successMessageElement));
        return successMessageElement.getText();
    }

    public String getErrorMessage() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}
