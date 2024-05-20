package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.training.utils.Driver;
import selenium.training.utils.ExplicitWait;

public class DashboardPage extends BasePage {

    private final NavigationMenu navigationpage;

    public DashboardPage() {
        super();
        this.navigationpage = new NavigationMenu();
    }

    @FindBy(linkText = "Computers")
    public WebElement btnComputerMenu;

    @FindBy(linkText = "Notebooks")
    private WebElement btnNotebooks;

    private void hoverOverComputersButton() {
        Actions actions = new Actions(Driver.getDriver());
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(btnComputerMenu));
        actions.moveToElement(btnComputerMenu).perform();
    }

    public void navigateToNotebooksPage() {
        hoverOverComputersButton();
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(btnComputerMenu));
        btnNotebooks.click();
    }

    public void navigateToLoginPage() {
        navigationpage.navigateToHomePage();
        navigationpage.navigateToLoginPage();
    }

}
