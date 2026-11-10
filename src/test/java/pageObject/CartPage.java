package pageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class CartPage extends BasePage {
	public CartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement cartHeading;
    public String getCartHeading() {
        wait.until(ExpectedConditions.visibilityOf(cartHeading));
        return cartHeading.getText();
    }
    @FindBy(xpath = "//div[@class='table-responsive']//tbody//td[2]//a")
    WebElement cartProductName;
    public String getCartProductName() {
        return cartProductName.getText();
    }
    @FindBy(xpath = "//div[@class='table-responsive']//tbody//td[3]")
    WebElement cartProductModel;
    public String getCartProductModel() {
        return cartProductModel.getText();
    }
    @FindBy(xpath = "//div[@class='table-responsive']//tbody//input[contains(@name,'quantity')]")
    WebElement cartQuantity;
    public String getCartQuantity() {
        return cartQuantity.getAttribute("value");
    }
    @FindBy(xpath = "//div[@class='table-responsive']//tbody//td[5]")
    WebElement cartPrice;
    public String getCartPrice() {
        return cartPrice.getText();
    }
    @FindBy(xpath = "//button[@data-original-title='Update']")
    WebElement updateButton;
    public void updateQuantity(String qty) {
        cartQuantity.clear();
        cartQuantity.sendKeys(qty);
        updateButton.click();
    }
    @FindBy(xpath = "//button[@data-original-title='Remove']")
    WebElement removeButton;
    public void removeProduct() {
        removeButton.click();
    }
}