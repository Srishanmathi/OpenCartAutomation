package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import utilities.DataProviders;

public class TC_003LoginPage extends BaseClass {

    // 3 executions through DataProvider
    @Test(
        dataProvider = "loginData",
        dataProviderClass = DataProviders.class,
        groups = {"master", "regression"},
        priority = 1
    )
    public void verifyLogin(String email, String password,
                            String expectedResult) {

        logger.info("***** Starting Login DataProvider Test *****");

        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();

        LoginPage lp = new LoginPage(driver);
        lp.enterEmail(email);
        lp.enterPassword(password);
        lp.clickLogin();

        if (expectedResult.equals("valid")) {

            Assert.assertEquals(
                lp.getPageHeading(), "My Account",
                "Valid login failed"
            );

            logger.info("Valid login successful");

        } else {

            String error = lp.getErrorMessage();

            Assert.assertTrue(
                error.contains("Warning:"),
                "Expected login warning was not displayed"
            );

            logger.info("Invalid login warning verified");
        }

        logger.info("***** Login DataProvider Test Completed *****");
    }

    // 1 execution using config.properties
    @Test(
        groups = {"master", "regression"},
        priority = 2
    )
    public void verifyConfigAccountLogin() {

        logger.info("***** Starting Config Login Test *****");

        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();

        LoginPage lp = new LoginPage(driver);

        lp.enterEmail(p.getProperty("username"));
        lp.enterPassword(p.getProperty("password"));
        lp.clickLogin();

        Assert.assertEquals(
            lp.getPageHeading(), "My Account",
            "Config account login failed"
        );

        logger.info("Config account login successful");
        logger.info("***** Config Login Test Completed *****");
    }
}