package selenium.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.training.utils.ExplicitWait;

public class NotebooksPage extends BasePage {

    @FindBy(css = "div.bar-notification.success")
    public WebElement succesMessageNottification;

    @FindBy(css = "div.page-title>h1")
    public WebElement pageTitle;

    @FindBy(css = "div.product-item[data-productid='5'] button.add-to-wishlist-button")
    public WebElement addToWishListElement2;

    @FindBy(css = "div.product-item[data-productid='8'] button.add-to-wishlist-button")
    public WebElement addToWishListElement3;

    @FindBy(css = "div.product-item[data-productid='7'] button.product-box-add-to-cart-button")
    public WebElement addToCartElement4;

    @FindBy(css = "div.product-item[data-productid='9'] button.product-box-add-to-cart-button")
    public WebElement addToCartElement5;

    @FindBy(css = "div.product-item[data-productid='6'] button.product-box-add-to-cart-button")
    public WebElement addToCartElement6;

    public void addSecondItemToWishList() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(addToWishListElement2));
        addToWishListElement2.click();
    }

    public void addThirdItemToWishList() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(addToWishListElement3));
        addToWishListElement3.click();
    }

    public void addFourthItemToCart() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(addToCartElement4));
        addToCartElement4.click();
    }

    public void addFifthItemToCart() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(addToCartElement5));
        addToCartElement5.click();
    }

    public void addSixthItemToCart() {
        ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(addToCartElement6));
        addToCartElement6.click();
    }

    public String getSuccessMessageAndClickXBox() {
        WebElement contentParagraph = succesMessageNottification.findElement(By.cssSelector("p.content"));
        WebElement closeSpan = succesMessageNottification.findElement(By.cssSelector("span.close"));

        String message = contentParagraph.getText();
        closeSpan.click();

        ExplicitWait.getWait().until(ExpectedConditions.invisibilityOfAllElements(succesMessageNottification, contentParagraph, closeSpan));
        return message;
    }

    public String getPageTitle() {
        ExplicitWait.getWait().until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

}
