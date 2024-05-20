package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import selenium.training.utils.ExplicitWait;

public class RegistrationPage extends BasePage {

    private final NavigationMenu navigationBar;

    public RegistrationPage() {
        super();
        navigationBar = new NavigationMenu();
    }


    @FindBy(id = "gender-female")
    public WebElement genderFemaleRadioButton;

    @FindBy(id = "FirstName")
    public WebElement firstNameWebElement;

    @FindBy(id = "LastName")
    public WebElement lastNameWebElement;

    @FindBy(name = "DateOfBirthDay")
    public WebElement dropdownDay;
    public Select dropdownBirthDay;

    @FindBy(name = "DateOfBirthMonth")
    public WebElement dropdownMonth;
    public Select dropdownBirthMonth;

    @FindBy(name = "DateOfBirthYear")
    public WebElement dropdownYear;
    public Select dropdownBirthYear;

    @FindBy(id = "Email")
    public WebElement emailWebElement;

    @FindBy(id = "Company")
    public WebElement companyWebElement;

    @FindBy(id = "Password")
    public WebElement passwordWebElement;

    @FindBy(id = "ConfirmPassword")
    public WebElement confirmPasswordWebElement;

    @FindBy(css = "#register-button")
    public WebElement submitRegisterButton;

    @FindBy(css = "div.result")
    public WebElement result;

    @FindBy(css = "div.page-title>h1")
    public WebElement registerPageTitle;


    public void fillRegistrationForm(String firstName, String lastName, String day, String month, String year, String email, String company, String password, String confirmPassword) {
        genderFemaleRadioButton.click();

        firstNameWebElement.clear();
        firstNameWebElement.sendKeys(firstName);

        lastNameWebElement.clear();
        lastNameWebElement.sendKeys(lastName);

        selectDay(day);
        selectMonth(month);
        selectYear(year);

        emailWebElement.clear();
        emailWebElement.sendKeys(email);

        companyWebElement.clear();
        companyWebElement.sendKeys(company);

        passwordWebElement.clear();
        passwordWebElement.sendKeys(password);

        confirmPasswordWebElement.clear();
        confirmPasswordWebElement.sendKeys(confirmPassword);

    }

    public void register(String firstName, String lastName, String day, String month, String year, String email, String company, String password, String confirmPassword) {
        navigateToRegistrationPage();
        fillRegistrationForm(firstName, lastName, day, month, year, email, company, password, confirmPassword);
        submitRegistrationForm();
    }

    public void submitRegistrationForm() {
        submitRegisterButton.click();
    }

    public void navigateToRegistrationPage() {
        navigationBar.navigateToHomePage();
        navigationBar.navigateToLoginPage();
        navigationBar.navigateToRegisterPage();
    }

    public void logout() {
        navigationBar.logout();
    }

    public void selectDay(String value) {
        dropdownBirthDay = new Select(dropdownDay);
        dropdownBirthDay.selectByValue(value);
    }

    public void selectMonth(String value) {
        dropdownBirthMonth = new Select(dropdownMonth);
        dropdownBirthMonth.selectByValue(value);
    }

    public void selectYear(String value) {
        dropdownBirthYear = new Select(dropdownYear);
        dropdownBirthYear.selectByValue(value);
    }

    public String getPageTitle() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(registerPageTitle));
        return registerPageTitle.getText();
    }

    public String getRegistrationResult() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(result));
        return result.getText();
    }

}
