
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

        Assert.assertEquals(
                lp.getPageHeading(),
                "My Account",
                "Login failed before Add To Cart"
        );

        logger.info("Login successful");

        // Clear existing cart before adding a new product
        driver.get("https://tutorialsninja.com/demo/index.php?route=checkout/cart");

        CartPage cp = new CartPage(driver);

        try {
            cp.removeProduct();
            logger.info("Existing cart product removed");
        } catch (Exception e) {
            logger.info("Cart is already empty");
        }

     // Navigate to Home page after clearing cart
        driver.get("https://tutorialsninja.com/demo/");

        // Reinitialize HomePage after navigation
        hp = new HomePage(driver);

        hp.searchProduct("MacBook");

        logger.info("MacBook searched successfully");

        hp.clickMacBook();

        logger.info("MacBook product clicked");

        // Verify product details
        ProductPage pp = new ProductPage(driver);

        String product = pp.getProductName();

        Assert.assertEquals(product, "MacBook");

        logger.info("Product name verified");

        // Set quantity
        pp.setQuantity("7");

        logger.info("Quantity entered");

        // Add product to cart
        pp.clickAddToCart();

        logger.info("Add to cart button clicked");

        String message = pp.getSuccessMessage();

        Assert.assertTrue(
                message.contains("Success: You have added MacBook to your shopping cart!"),
                "Product was not added to cart"
        );

        logger.info("Product added to cart successfully");

        // Open shopping cart
        driver.get("https://tutorialsninja.com/demo/index.php?route=checkout/cart");

        Assert.assertTrue(
                cp.getCartHeading().contains("Shopping Cart"),
                "Shopping cart page not opened"
        );

        logger.info("Shopping cart page opened");

        // Verify cart details
        Assert.assertEquals(
                cp.getCartProductName(),
                "MacBook",
                "Cart product name mismatch"
        );

        logger.info("Cart product name verified");

        Assert.assertEquals(
                cp.getCartQuantity(),
                "7",
                "Cart quantity mismatch"
        );

        logger.info("Cart quantity verified successfully");

        logger.info("Product details verified in shopping cart");

        logger.info("***** Add To Cart Test Completed *****");
    }
}
