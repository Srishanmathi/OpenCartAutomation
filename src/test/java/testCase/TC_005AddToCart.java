package testCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductPage;
import pageObject.CartPage;
public class TC_005AddToCart extends BaseClass {
    @Test(groups = {"regression"})
    public void verifyAddToCart() {
        logger.info("***** Starting Add To Cart Test *****");
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();
        LoginPage lp = new LoginPage(driver);
        lp.enterEmail(p.getProperty("username"));
        lp.enterPassword(p.getProperty("password"));
        lp.clickLogin();
        Assert.assertEquals(lp.getPageHeading(), "My Account", "Login failed before Add To Cart");
        hp.clickHome();
        hp.searchProduct("MacBook");
        logger.info("MacBook searched successfully");
        hp.clickMacBook();
        logger.info("MacBook product clicekd");
        ProductPage pp = new ProductPage(driver);
        logger.info("Product details page opened");
        String product = pp.getProductName();
        Assert.assertEquals(product, "MacBook");
        logger.info("Product name verified");
        pp.setQuantity("7");
        logger.info("Quantity entered");
        pp.clickAddToCart();
        logger.info("Add to cart button clicked");
        String message = pp.getSuccessMessage();
        pp.checkAlertMessage();
        Assert.assertTrue(
                message.contains("Success: You have added MacBook to your shopping cart!"),
                "Product was not added to cart"
        );
        logger.info("Product added to cart successfully");
        driver.get("https://tutorialsninja.com/demo/index.php?route=checkout/cart");
        CartPage cp = new CartPage(driver);
        Assert.assertTrue(cp.getCartHeading().contains("Shopping Cart"));
        logger.info("Shopping cart page opened");
        Assert.assertEquals(cp.getCartProductName(), "MacBook");
        logger.info("Cart product name verified");
        Assert.assertEquals(cp.getCartQuantity(), "7");
        logger.info("Cart quantity verified successfully");
        logger.info("Product details verified in shopping cart");
        logger.info("***** Add To Cart Test Completed *****");
    }
}