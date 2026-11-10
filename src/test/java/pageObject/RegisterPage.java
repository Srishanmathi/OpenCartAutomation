package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "input-firstname")
    WebElement txtFirstName;
    public void enterFirstName(String fname) {
        wait.until(ExpectedConditions.visibilityOf(txtFirstName));
        txtFirstName.sendKeys(fname);
    }
    @FindBy(id = "input-lastname")
    WebElement txtLastName;
    public void enterLastName(String lname) {
        wait.until(ExpectedConditions.visibilityOf(txtLastName));
        txtLastName.sendKeys(lname);
    }
    @FindBy(id = "input-email")
    WebElement txtEmail;
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(txtEmail));
        txtEmail.sendKeys(email);
    }
    @FindBy(id = "input-telephone")
    WebElement txtTelephone;
    public void enterTelephone(String telephone) {
        wait.until(ExpectedConditions.visibilityOf(txtTelephone));
        txtTelephone.sendKeys(telephone);
    }
    @FindBy(id = "input-password")
    WebElement txtPassword;
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(txtPassword));
        txtPassword.sendKeys(password);
    }
    @FindBy(id = "input-confirm")
    WebElement txtConfirmPassword;
    public void enterConfirmPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(txtConfirmPassword));
        txtConfirmPassword.sendKeys(password);
    }
    @FindBy(name = "agree")
    WebElement chkPrivacyPolicy;
    public void acceptPrivacyPolicy() {
        chkPrivacyPolicy.click();
    }
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
        btnContinue.click();
    }
    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement txtHeading;
    public String getPageHeading() {
        wait.until(ExpectedConditions.visibilityOf(txtHeading));
        return txtHeading.getText();
    }
    public String getErrorMessage() {
        By error = By.xpath("//div[contains(@class,'alert-danger')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(error));
        return driver.findElement(error).getText();
    }
}