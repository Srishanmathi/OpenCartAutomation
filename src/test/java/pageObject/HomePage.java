package pageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@id='top-links']//a[contains(@class,'dropdown-toggle')]")
    WebElement myAccount;
    public void clickMyAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccount));
        myAccount.click();
    }
    @FindBy(xpath = "//div[@id='top-links']//a[contains(@href,'route=account/register')]")
    WebElement registerLink;
    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        registerLink.click();
    }
    @FindBy(xpath = "//div[@id='top-links']//a[contains(@href,'route=account/login')]")
    WebElement loginLink;
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }
    @FindBy(xpath = "//div[@id='logo']//a")
    WebElement homeButton;
    public void clickHome() {
        wait.until(ExpectedConditions.elementToBeClickable(homeButton));
        homeButton.click();
    }
    public String getPageTitle() {
        return driver.getTitle();
    }
    @FindBy(name = "search")
    WebElement txtSearch;
    @FindBy(xpath = "//button[contains(@class,'btn-lg') and .//i[contains(@class,'fa-search')]]")
    WebElement btnSearch;
    public void searchProduct(String product) {
    	Actions act = new Actions(driver);
    	act.moveToElement(txtSearch).perform();
    	txtSearch.sendKeys(product);
    	btnSearch.click();
    }
    @FindBy(id = "input-search")
    WebElement txtSearchCriteria;
    public void enterSearchCriteria(String product) {
    	wait.until(ExpectedConditions.visibilityOf(txtSearchCriteria));
    	txtSearchCriteria.clear();
    	txtSearchCriteria.sendKeys(product);
    }
    @FindBy(xpath = "//div[@class='product-thumb']//a[text()='MacBook']")
    WebElement macBookProduct;
    public void clickMacBook() {
        wait.until(ExpectedConditions.elementToBeClickable(macBookProduct));
        macBookProduct.click();
    }
    @FindBy(name = "category_id")
    WebElement drpCategory;
    public void selectCategory(String category) {
        wait.until(ExpectedConditions.visibilityOf(drpCategory));
        Select cat = new Select(drpCategory);
        cat.selectByVisibleText(category);
    }
    @FindBy(name = "sub_category")
    WebElement chkSubCategory;
    public void clickSubCategory() {
    	if (!chkSubCategory.isSelected()) {
    		chkSubCategory.click();
    	}
    }
    @FindBy(name = "description")
    WebElement chkDescription;
    public void clickDescription() {
    	if (!chkDescription.isSelected()) {
    		chkDescription.click();
    	}
    }
    @FindBy(xpath = "//input[@id='button-search']")
    WebElement search;
    public void clicksearch() {
        wait.until(ExpectedConditions.elementToBeClickable(search));
        search.click();
    }
    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement pageHeading;
    public String getSearchPageHeading() {
        wait.until(ExpectedConditions.visibilityOf(pageHeading));
        return pageHeading.getText();
    }
}