
package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Main Product Image
    @FindBy(xpath = "//ul[@class='thumbnails']//li[1]//a")
    WebElement image1;

    public void clickMainImage() {
        wait.until(ExpectedConditions.elementToBeClickable(image1));
        image1.click();
    }

    // Additional Product Images
    @FindBy(xpath = "//ul[@class='thumbnails']//li[position()>1]//a")
    List<WebElement> additionalimages;

    public void clickAdditionalImage(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(additionalimages.get(index)));
        additionalimages.get(index).click();
    }

    // Product Description
    @FindBy(xpath = "//a[@href='#tab-description']")
    WebElement description;

    public void clickDescription() {
        description.click();
    }

    // Product Specification
    @FindBy(xpath = "//a[@href='#tab-specification']")
    WebElement specificationTab;

    public void clickSpecification() {
        specificationTab.click();
    }

    // Product Reviews
    @FindBy(xpath = "//a[@href='#tab-review']")
    WebElement reviewsTab;

    public void clickReviews() {
        reviewsTab.click();
    }

    // Product Name
    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement productName;

    public String getProductName() {
        return productName.getText();
    }

    // Brand Name
    @FindBy(xpath = "//ul[@class='list-unstyled']//a")
    WebElement brandName;

    public String getBrandName() {
        return brandName.getText();
    }

    public void clickBrand() {
        brandName.click();
    }

    // Product Price
    @FindBy(xpath = "//ul[@class='list-unstyled']//li[contains(text(),'Price')]")
    WebElement productPrice;

    public String getProductPrice() {
        return productPrice.getText();
    }

    // Product Availability
    @FindBy(xpath = "//ul[@class='list-unstyled']//li[contains(text(),'Availability')]")
    WebElement availability;

    public String getAvailability() {
        return availability.getText();
    }

    // Product Quantity
    @FindBy(id = "input-quantity")
    WebElement quantity;

    public void setQuantity(String qty) {
        wait.until(ExpectedConditions.visibilityOf(quantity));
        quantity.clear();
        quantity.sendKeys(qty);
    }

    // Add To Cart
    @FindBy(id = "button-cart")
    WebElement addToCartButton;

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }

    // Add To Wishlist
    @FindBy(xpath = "//button[@data-original-title='Add to Wish List']")
    WebElement wishListButton;

    public void clickAddToWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(wishListButton));
        wishListButton.click();
    }

    // Compare Product
    @FindBy(xpath = "//button[@data-original-title='Compare this Product']")
    WebElement compareButton;

    public void clickCompareProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(compareButton));
        compareButton.click();
    }

    // Success Message
    public String getSuccessMessage() {

        By alertMessage = By.cssSelector(
            ".alert-success, .alert-danger"
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));

        WebElement alert = driver.findElement(alertMessage);

        String message = alert.getText();

        System.out.println("Alert Message: " + message);

        return message;
    }

    // No Reviews Message
    @FindBy(xpath = "//div[@id='tab-review']//p")
    WebElement noReviewsMessage;

    public String getNoReviewsMessage() {
        return noReviewsMessage.getText();
    }

    // Review Name
    @FindBy(id = "input-name")
    WebElement reviewName;

    public void enterReviewName(String name) {
        reviewName.clear();
        reviewName.sendKeys(name);
    }

    // Review Text
    @FindBy(id = "input-review")
    WebElement reviewText;

    public void enterReview(String review) {
        reviewText.sendKeys(review);
    }

    // Review Rating
    @FindBy(xpath = "//input[@name='rating']")
    List<WebElement> ratings;

    public void selectRating(int rating) {
        ratings.get(rating - 1).click();
    }

    // Submit Review
    @FindBy(id = "button-review")
    WebElement continueButton;

    public void clickContinue() {
        continueButton.click();
    }

    // Warning Message
    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    WebElement warningMessage;

    public String getWarningMessage() {
        wait.until(ExpectedConditions.visibilityOf(warningMessage));
        return warningMessage.getText();
    }

    // Product Specification Details
    @FindBy(xpath = "//div[@id='tab-specification']//table")
    WebElement specificationDetails;

    public String getSpecificationDetails() {
        return specificationDetails.getText();
    }
}
