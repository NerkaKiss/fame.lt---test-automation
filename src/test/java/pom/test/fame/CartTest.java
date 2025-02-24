package pom.test.fame;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.page.fame.*;
import pom.test.TestBase;

public class CartTest extends TestBase {
    @BeforeMethod
    @Override
    public void setUp() {
        HomePage.open();
        HomePage.login();
    }

    @Test
    public void testPositive_addToCart_cartModuleInPage_expectedMessageNamePrice() {
        String expectedMessage = "Prekė sėkmingai pridėta į krepšelį";
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        ProductsPage.clickOnFirstProductInJeansCategory();
        String expectedItemName = ProductPage.readProductName();
        String expectedPrice = ProductPage.readProductPrice();

        ProductPage.clickButtonAddToCart();
        String actualItemName = CartModuleInPage.readProductName();
        String actualPrice = CartModuleInPage.readPrice();
        String actualMessage = CartModuleInPage.readMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "\nActual: %s\nExpected contains: %s".formatted(actualMessage, expectedMessage));
        Assert.assertEquals(actualItemName, expectedItemName, "Product name not matched");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price not matched");
    }

    @Test
    public void testPositive_addToCart_cartPage_expectedUrlNamePrice() {
        String expectedUrl = "https://fame.lt/krepselis";
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        ProductsPage.clickOnFirstProductInJeansCategory();
        String expectedItemName = ProductPage.readProductName();
        String expectedPrice = ProductPage.readProductPrice();

        ProductPage.clickButtonAddToCart();
        CartModuleInPage.clickOnButtonBuy();
        String actualItemName = CartPage.readProductName();
        String actualPrice = CartPage.readPrice();
        String actualUrl = CartPage.readNewUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrl),
                "\nActual: %s\nExpected contains: %s".formatted(actualUrl, expectedUrl));
        Assert.assertEquals(actualItemName, expectedItemName, "Product name not matched");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price not matched");
    }

    @Test
    public void testPositive_refreshCartAfterAddToCart_cartPage_expectedUrlNamePrice() {
        String expectedUrl = "https://fame.lt/krepselis";
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        ProductsPage.clickOnFirstProductInJeansCategory();
        String expectedItemName = ProductPage.readProductName();
        String expectedPrice = ProductPage.readProductPrice();

        ProductPage.clickButtonAddToCart();
        CartModuleInPage.clickOnButtonBuy();
        CartPage.refreshPage();
        String actualItemName = CartPage.readProductName();
        String actualPrice = CartPage.readPrice();
        String actualUrl = CartPage.readNewUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrl),
                "\nActual: %s\nExpected contains: %s".formatted(actualUrl, expectedUrl));
        Assert.assertEquals(actualItemName, expectedItemName, "Product name not matched");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price not matched");
    }

    @Test
    public void testPositive_checkout_toCheckoutPage_expectedUrlProductNameProductPrice() {
        String expectedUrl = "https://fame.lt/u%C5%BEsakymas";
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        ProductsPage.clickOnFirstProductInJeansCategory();
        String expectedItemName = ProductPage.readProductName();
        String expectedPrice = ProductPage.readProductPrice();

        ProductPage.clickButtonAddToCart();
        CartModuleInPage.clickOnButtonBuy();
        CartPage.clickOnButtonBuy();
        String actualItemName = CheckoutPage.readProductName();
        String actualPrice = CheckoutPage.readPrice();
        String actualUrl = CheckoutPage.readNewUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrl),
                "\nActual: %s\nExpected contains: %s".formatted(actualUrl, expectedUrl));
        Assert.assertEquals(actualItemName, expectedItemName, "Product name not matched");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price not matched");
    }
}
