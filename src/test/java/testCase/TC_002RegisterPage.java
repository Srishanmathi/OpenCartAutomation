package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.RegisterPage;

public class TC_002RegisterPage extends BaseClass {

    @Test(groups = {"master", "regression"})
    public void verifyRegistration() {

        logger.info("***** Starting Registration Test *****");

        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        RegisterPage rp = new RegisterPage(driver);

        rp.enterFirstName("Test");
        rp.enterLastName("User");
        rp.enterEmail(p.getProperty("username"));
        rp.enterTelephone("9876543210");
        rp.enterPassword(p.getProperty("password"));
        rp.enterConfirmPassword(p.getProperty("password"));
        rp.acceptPrivacyPolicy();

        rp.clickContinue();

        String page = driver.getPageSource();

        Assert.assertTrue(
            page.contains("Warning: E-Mail Address is already registered!"),
            "Expected duplicate email warning was not displayed"
        );

        logger.info("Already registered email warning verified");
        logger.info("***** Registration Test Completed *****");
    }
}