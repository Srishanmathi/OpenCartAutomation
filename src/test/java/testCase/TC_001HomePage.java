package testCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
public class TC_001HomePage extends BaseClass {
    @Test
    public void verifyHomePage() {
        logger.info("***** Starting Homepage Test *****");
        HomePage hp = new HomePage(driver);
        logger.debug("Checking homepage title");
        String title = hp.getPageTitle();
        logger.info("Page title: " + title);
        if (title.equals("Your Store")) {
            logger.info("Homepage title is correct");
        } else {
            logger.warn("Homepage title is incorrect");
        }
        Assert.assertEquals(title, "Your Store",
                "Homepage title verification failed");
        hp.clickMyAccount();
        logger.info("My Account menu clicked");
        logger.info("***** Homepage Test Completed *****");
    }
    @Test
    public void verifyIncorrectPageTitle() {
        logger.info("Starting intentional failure test");
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Invalid Title",
                "Intentional failure: Page title mismatch");
    }
    @Test
    public void verifyIncorrectHeading() {
        logger.info("Starting intentional failure test");
        Assert.assertEquals("Your Store", "My Account",
                "Intentional failure: Heading mismatch");
    }
}