package selenium.training.tests;

import org.junit.jupiter.api.AfterAll;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import selenium.training.pages.*;
import selenium.training.utils.Driver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static selenium.training.pages.ShoppingCartPage.EXPECTED_ORDER_MSG;
import static selenium.training.utils.GlobalConfigs.*;

@Listeners(selenium.training.utils.Listeners.class)
public class ShoppingCartPageTest {

    private final ShoppingCartPage shoppingCartPage;
    private LoginPage loginPage;

    public ShoppingCartPageTest() {
        this.shoppingCartPage = new ShoppingCartPage();
        this.loginPage = new LoginPage();
    }

    @AfterAll
    public static void tearDown() {
        Driver.getDriver().quit();
    }

    @Test
    public void checkoutOrderTest() {

        loginPage.navigateToLoginPage();
        loginPage.login(EMAIL, PASSWORD);
        shoppingCartPage.navigateToShoppingCartPage();

        assertEquals(shoppingCartPage.getPageTitle(), ShoppingCartPage.EXPECTED_PAGE_TITLE);

        assertTrue(shoppingCartPage.continueBtnAndEstimateBtnDisplayed(), "Contimue & Estimate Buttons are dispalyed");

        assertEquals(shoppingCartPage.getProductsTotalPrice(), shoppingCartPage.getTotalPrice(), "Total price is not as expected");

        shoppingCartPage.clickTermsOfServiceCheckBox();
        shoppingCartPage.clickCheckoutButton();

        //Verify that first name, last name and email are displayed correctly
        assertEquals(shoppingCartPage.getFirstName(), FIRST_NAME, "First name is not as expected");
        assertEquals(shoppingCartPage.getLastName(), LAST_NAME, "Last name is not as expected");
        assertEquals(shoppingCartPage.getEmail(), EMAIL, "Email is not as expected");

        //Fill the billing address form
        shoppingCartPage.fillBillingAddressForm("Albania", "Tirane", "Tirane", "1000", "1112223330");
        shoppingCartPage.clickContinueButtonAfterBillFilling();

        shoppingCartPage.selectShippingMethod();
        shoppingCartPage.selectPaymentMethod();
        shoppingCartPage.clickContinuePaymentInformationButton();

        assertEquals(shoppingCartPage.getTotalPrice(), shoppingCartPage.getTotalPriceValue(), "Total price is not as expected");
        shoppingCartPage.confirmOrder();

        //Verify your order is done successful and an order number is displayed.
        Assert.assertEquals(shoppingCartPage.getOrderSuccessMessage(), EXPECTED_ORDER_MSG);
        Assert.assertFalse(shoppingCartPage.getOrderNumber().isEmpty());
        shoppingCartPage.clickContinueButtonLastStep();

    }
}
