
package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
//import utilities.DataProviders;

public class TC_004SearchPage extends BaseClass {

//    @Test(dataProvider = "searchLoginData",
//          dataProviderClass = DataProviders.class)
	@Test(groups = {"master", "regression"})
    public void verifySearch() {
		

        logger.info("***** Starting Search Test *****");

        HomePage hp = new HomePage(driver);

        hp.clickMyAccount();
        hp.clickLogin();

        LoginPage lp = new LoginPage(driver);

        lp.enterEmail(p.getProperty("username"));
        lp.enterPassword(p.getProperty("password"));
        lp.clickLogin();

        logger.info("Login button clicked");

        String heading = lp.getPageHeading();

        Assert.assertEquals(heading, "My Account",
                "Login failed");

        logger.info("Customer logged in successfully");

        hp = new HomePage(driver);

        hp.clickHome();

        logger.info("Home page opened");

        hp.searchProduct("iMac");

        logger.info("Product searched");

        hp.enterSearchCriteria("iMac");

        hp.selectCategory("Desktops");

        logger.info("Category selected");

        hp.clickSubCategory();
        hp.clickDescription();

        logger.info("Sub-category and Description selected");

        hp.clicksearch();

        logger.info("Advanced search performed");

        String searchHeading = hp.getSearchPageHeading();

        Assert.assertEquals(searchHeading, "Search - iMac",
                "Search result page heading is incorrect");

        logger.info("Search result verified successfully");

        logger.info("***** Search Test Completed *****");
    }
}
