package selenium.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import selenium.training.utils.Driver;
import selenium.training.utils.ExplicitWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingCartPage extends BasePage {

    public static final String EXPECTED_ORDER_MSG = "Your order has been successfully processed!";
    public static final String EXPECTED_PAGE_TITLE = "Shopping cart";

    private double totalPriceValue;
    private final NavigationMenu navigationpage;

    public ShoppingCartPage() {
        super();
        navigationpage = new NavigationMenu();
    }

    @FindBy(css = "div.page-title>h1")
    private WebElement pageTitle;


    @FindBy(css = "div.cart-options div.common-buttons button.button-2.continue-shopping-button")
    private WebElement continueShoppingButton;

    @FindBy(id = "open-estimate-shipping-popup")
    private WebElement estimateShippingButton;

    @FindBy(css = ".product-subtotal")
    private List<WebElement> productSubtotalPrice;

    @FindBy(css = ".cart-total-right .value-summary strong")
    private WebElement totalPrice;

    @FindBy(id = "termsofservice")
    private WebElement termsOfServiceCb;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "BillingNewAddress_FirstName")
    private WebElement firstNameElement;

    @FindBy(id = "BillingNewAddress_LastName")
    private WebElement lastNameElement;

    @FindBy(id = "BillingNewAddress_Email")
    private WebElement emailElement;

    @FindBy(id = "BillingNewAddress_Company")
    private WebElement companyElement;

    @FindBy(id = "BillingNewAddress_CountryId")
    private WebElement countryButtonDropDown;

    @FindBy(id = "BillingNewAddress_City")
    private WebElement cityElement;

    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement address1Element;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement zipPostalCodeElement;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement phoneNumberElement;


    @FindBy(xpath = "//*[@id=\"billing-buttons-container\"]/button[2]")
    public WebElement continueButtonAfterBillFilling;

    @FindBy(id = "shippingoption_1")
    private WebElement nextDayAirRadioButton;
    @FindBy(css = "div#shipping-method-buttons-container>button")
    public WebElement continueShippingMethodButton;

    @FindBy(id = "paymentmethod_0")
    private WebElement cashOnDeliveryRadioButton;
    @FindBy(css = "button.button-1.payment-method-next-step-button")
    public WebElement continuePaymentMethodButton;
    @FindBy(css = "button.button-1.payment-info-next-step-button")
    private WebElement continuePaymentInformationButton;

    @FindBy(css = "button.button-1.confirm-order-next-step-button")
    private WebElement confirmButton;
    @FindBy(css = ".section.order-completed .title strong")
    private WebElement orderSuccessMessage;
    @FindBy(css = ".section.order-completed .order-number strong")
    private WebElement orderNumber;

    @FindBy(css = ".button-1.order-completed-continue-button")
    private WebElement continueButtonLastStep;


    public void navigateToShoppingCartPage() {
        navigationpage.navigateToShoppingCartPage(true);
    }

    public boolean continueBtnAndEstimateBtnDisplayed() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(continueShoppingButton));
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(estimateShippingButton));
        return continueShoppingButton.isDisplayed() && estimateShippingButton.isDisplayed();
    }

    public void clickTermsOfServiceCheckBox() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(termsOfServiceCb));
        termsOfServiceCb.click();
    }


    public void clickCheckoutButton() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(checkoutButton));
        checkoutButton.click();
    }

    private void selectCountry(String countryName) {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(countryButtonDropDown));
        Select select = new Select(countryButtonDropDown);
        select.selectByVisibleText(countryName);
    }

    public void fillBillingAddressForm(String countryName, String city, String address1, String zipCode, String phoneNumber) {
        selectCountry(countryName);
        enterCity(city);
        enterAddress1(address1);
        enterZipPostalCode(zipCode);
        enterPhoneNumber(phoneNumber);
    }

    public void clickContinueButtonAfterBillFilling() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(continueButtonAfterBillFilling));
        continueButtonAfterBillFilling.click();
    }

    public void selectShippingMethod() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(nextDayAirRadioButton));
        nextDayAirRadioButton.click();
        continueShippingMethodButton.click();
    }

    public void selectPaymentMethod() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(cashOnDeliveryRadioButton));
        cashOnDeliveryRadioButton.click();
        continuePaymentMethodButton.click();
    }

    public void clickContinuePaymentInformationButton() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(continuePaymentInformationButton));
        continuePaymentInformationButton.click();
    }

    public void confirmOrder() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
    }

    public void clickContinueButtonLastStep() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(continueButtonLastStep));
        continueButtonLastStep.click();
    }

    public double getTotalPriceValue() {
        return this.totalPriceValue;
    }

    public double getProductsTotalPrice() {
        double sum = 0;
        for (WebElement totalElement : productSubtotalPrice) {
            String totalText = totalElement.getText().replace("$", "").replace(",", "").trim();
            double totalValue = Double.parseDouble(totalText);
            sum += totalValue;
        }
        return sum;
    }

    public double getTotalPrice() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(totalPrice));
        String totalPriceText = totalPrice.getText().replace("$", "").replace(",", "").trim();
        this.totalPriceValue = Double.parseDouble(totalPriceText);
        return totalPriceValue;
    }

    public String getPageTitle() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    public String getFirstName() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(firstNameElement));
        //catch element using css selector
        Driver.getDriver().findElement(By.id("BillingNewAddress_FirstName"));
        return firstNameElement.getAttribute("value");
    }

    public String getLastName() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(lastNameElement));
        return lastNameElement.getAttribute("value");
    }

    public String getEmail() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(emailElement));
        return emailElement.getAttribute("value");
    }

    public String getOrderSuccessMessage() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(orderSuccessMessage));
        return orderSuccessMessage.getText();
    }

    public String getOrderNumber() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(orderNumber.getText());
        if (matcher.find()) {
            return matcher.group();
        } else {
            return "";
        }
    }

    private void enterCity(String city) {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(cityElement));
        cityElement.sendKeys(city);
    }

    private void enterAddress1(String address1) {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(address1Element));
        address1Element.sendKeys(address1);
    }

    private void enterZipPostalCode(String zipPostalCode) {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(zipPostalCodeElement));
        zipPostalCodeElement.sendKeys(zipPostalCode);
    }

    private void enterPhoneNumber(String phoneNumber) {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(phoneNumberElement));
        phoneNumberElement.sendKeys(phoneNumber);
    }

}