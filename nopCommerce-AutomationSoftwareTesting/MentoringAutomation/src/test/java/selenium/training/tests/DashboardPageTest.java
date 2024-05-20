package selenium.training.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import selenium.training.pages.*;

import static selenium.training.utils.GlobalConfigs.EMAIL;
import static selenium.training.utils.GlobalConfigs.PASSWORD;

@Listeners(selenium.training.utils.Listeners.class)
public class DashboardPageTest {

    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;
    private final NotebooksPage notebooksPage;
    private final NavigationMenu navigationMenu;

    public DashboardPageTest() {
        this.loginPage = new LoginPage();
        this.dashboardPage = new DashboardPage();
        this.notebooksPage = new NotebooksPage();
        this.navigationMenu = new NavigationMenu();
    }

    @Test
    public void addItemsTest() {
        dashboardPage.navigateToLoginPage();
        loginPage.login(EMAIL, PASSWORD);
        dashboardPage.navigateToNotebooksPage();

        //Verify that we have navigate to Notebooks Page
        String expectedTitle = "Notebooks";
        Assert.assertEquals(notebooksPage.getPageTitle(), expectedTitle, "Page title is not as expected");

        // Add the second adn third items to wishlist
        notebooksPage.addSecondItemToWishList();
        Assert.assertEquals(notebooksPage.getSuccessMessageAndClickXBox(), "The product has been added to your wishlist");
        notebooksPage.addThirdItemToWishList();
        Assert.assertEquals(notebooksPage.getSuccessMessageAndClickXBox(), "The product has been added to your wishlist");

        // Add the fourth & fifth and sixth items to cart
        notebooksPage.addFourthItemToCart();
        Assert.assertEquals(notebooksPage.getSuccessMessageAndClickXBox(), "The product has been added to your shopping cart");
        notebooksPage.addFifthItemToCart();
        Assert.assertEquals(notebooksPage.getSuccessMessageAndClickXBox(), "The product has been added to your shopping cart");
        notebooksPage.addSixthItemToCart();
        Assert.assertEquals(notebooksPage.getSuccessMessageAndClickXBox(), "The product has been added to your shopping cart");

        //Verify that Wishlist on Menu bar displays 2, and Shopping Cart on Menu bar displays 3
        Assert.assertEquals(navigationMenu.getWishListQty(), 2, "Wishlist quantity is not as expected");
        Assert.assertEquals(navigationMenu.getShoppingCartQty(), 3, "Cart quantity is not as expected");

        //Logout
        loginPage.logout();
    }
}

