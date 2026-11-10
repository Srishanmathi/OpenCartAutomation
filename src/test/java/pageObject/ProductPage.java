package pageObject;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class ProductPage extends BasePage{
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//ul[@class='thumbnails']//li[1]//a")
	WebElement image1;
	public void clickMainImage() {
        wait.until(ExpectedConditions.elementToBeClickable(image1));
        image1.click();
    }
	@FindBy(xpath = "//ul[@class='thumbnails']//li[position()>1]//a")
	List<WebElement> additionalimages;
	public void clickAdditionalImage(int index) {
	    wait.until(ExpectedConditions.elementToBeClickable(additionalimages.get(index)));
	    additionalimages.get(index).click();
	}
	@FindBy(xpath = "//a[@href='#tab-description']")
	WebElement description;
	public void clickDescription() {
        description.click();
    }
	@FindBy(xpath = "//a[@href='#tab-specification']")
	WebElement specificationTab;
	public void clickSpecification() {
        specificationTab.click();
    }
    @FindBy(xpath = "//a[@href='#tab-review']")
	WebElement reviewsTab;
    public void clickReviews() {
        reviewsTab.click();
    }
    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement productName;
    public String getProductName() {
        return productName.getText();
    }
    @FindBy(xpath = "//ul[@class='list-unstyled']//a")
    WebElement brandName;
    public String getBrandName() {
        return brandName.getText();
    }
    public void clickBrand() {
        brandName.click();
    }
    @FindBy(xpath = "//ul[@class='list-unstyled']//li[contains(text(),'Price')]")
    WebElement productPrice;
    public String getProductPrice() {
        return productPrice.getText();
    }
    @FindBy(xpath = "//ul[@class='list-unstyled']//li[contains(text(),'Availability')]")
    WebElement availability;
    public String getAvailability() {
        return availability.getText();
    }
    @FindBy(id = "input-quantity")
    WebElement quantity;
    public void setQuantity(String qty) {
        quantity.clear();
        quantity.sendKeys(qty);
    }
    @FindBy(id = "button-cart")
    WebElement addToCartButton;
    public void clickAddToCart() {
        addToCartButton.click();
    }
    @FindBy(xpath = "//button[@data-original-title='Add to Wish List']")
    WebElement wishListButton;
    public void clickAddToWishList() {
        wishListButton.click();
    }
    @FindBy(xpath = "//button[@data-original-title='Compare this Product']")
    WebElement compareButton;
    public void clickCompareProduct() {
        compareButton.click();
    }
    public String getSuccessMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'alert')]")));
        List<WebElement> alerts = driver.findElements(By.xpath("//div[contains(@class,'alert')]"));
        for (WebElement alert : alerts) {
            if (alert.isDisplayed()) {
                System.out.println("Alert Message: " + alert.getText());
                return alert.getText();
            }
        }
        return "";
    }
    @FindBy(xpath = "//div[@id='tab-review']//p")
    WebElement noReviewsMessage;
    public String getNoReviewsMessage() {
        return noReviewsMessage.getText();
    }
    @FindBy(id = "input-name")
    WebElement reviewName;
    public void enterReviewName(String name) {
        reviewName.clear();
        reviewName.sendKeys(name);
    }
    @FindBy(id = "input-review")
    WebElement reviewText;
    public void enterReview(String review) {
        reviewText.sendKeys(review);
    }
    @FindBy(xpath = "//input[@name='rating']")
    List<WebElement> ratings;
    public void selectRating(int rating) {
        ratings.get(rating - 1).click();
    }
    @FindBy(id = "button-review")
    WebElement continueButton;
    public void clickContinue() {
        continueButton.click();
    }
    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    WebElement warningMessage;
    public String getWarningMessage() {
        wait.until(ExpectedConditions.visibilityOf(warningMessage));
        return warningMessage.getText();
    }
    @FindBy(xpath = "//div[@id='tab-specification']//table")
    WebElement specificationDetails;
    public String getSpecificationDetails() {
        return specificationDetails.getText();
    }
    public void checkAlertMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class,'alert')]")
        ));
        List<WebElement> alerts = driver.findElements(
            By.xpath("//div[contains(@class,'alert')]")
        );
        for (WebElement alert : alerts) {
            System.out.println("Alert Message: " + alert.getText());
        }
    }
}