package pageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "input-email")
    WebElement txtEmail;
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(txtEmail));
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }
    @FindBy(id = "input-password")
    WebElement txtPassword;
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(txtPassword));
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }
    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();
    }
    public String getPageHeading() {
        By heading = By.xpath("//div[@id='content']//h2");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(heading)).getText();
    }
    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    WebElement txtErrorMessage;
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(txtErrorMessage));
        return txtErrorMessage.getText();
    }
}