package selenium.training.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.training.utils.Driver;
import selenium.training.utils.GlobalConfigs;
import selenium.training.utils.ExplicitWait;

public class NavigationMenu extends BasePage {

    @FindBy(css = ".ico-login")
    public WebElement loginButton;

    @FindBy(css = ".ico-logout")
    public WebElement logoutButton;

    @FindBy(css = ".ico-register")
    public WebElement registerButton;

    @FindBy(css = ".ico-cart")
    public WebElement shoppingCartButton;

    @FindBy(css = "a.ico-wishlist > span.wishlist-qty")
    WebElement wishListQty;

    @FindBy(css = "a.ico-cart > span.cart-qty")
    WebElement cartQty;

    @FindBy(css = "div.mini-shopping-cart  button.button-1.cart-button")
    WebElement goToCartButton;


    public void navigateToLoginPage() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void navigateToRegisterPage() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    public void navigateToShoppingCartPage() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(shoppingCartButton));
        shoppingCartButton.click();
    }

    public void navigateToShoppingCartPage(boolean hover) {
        ExplicitWait.getWait().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(shoppingCartButton));
        if (hover) {
            Actions actions = new Actions(Driver.getDriver());
            actions.moveToElement(shoppingCartButton).perform();
            ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(goToCartButton));
            goToCartButton.click();
        } else {
            navigateToShoppingCartPage();
        }
    }

    public void logout() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public void navigateToHomePage() {
        Driver.getDriver().get(GlobalConfigs.URL);
    }

    public int getWishListQty() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(wishListQty));
        return Integer.parseInt(wishListQty.getText().replaceAll("[^0-9]", ""));
    }

    public int getShoppingCartQty() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(cartQty));
        return Integer.parseInt(cartQty.getText().replaceAll("[^0-9]", ""));
    }
}
