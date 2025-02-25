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
        double expectedPrice = ProductPage.readProductPrice();

        ProductPage.clickButtonAddToCart();
        String actualItemName = CartModuleInPage.readProductName();
        double actualPrice = CartModuleInPage.readPrice();
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
        double expectedPrice = ProductPage.readProductPrice();

        ProductPage.clickButtonAddToCart();
        CartModuleInPage.clickOnButtonBuy();
        String actualItemName = CartPage.readProductName();
        double actualPrice = CartPage.readPrice();
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
        double expectedPrice = ProductPage.readProductPrice();

        ProductPage.clickButtonAddToCart();
        CartModuleInPage.clickOnButtonBuy();
        CartPage.refreshPage();
        String actualItemName = CartPage.readProductName();
        double actualPrice = CartPage.readPrice();
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
        double expectedPrice = ProductPage.readProductPrice();

        ProductPage.clickButtonAddToCart();
        CartModuleInPage.clickOnButtonBuy();
        CartPage.clickOnButtonBuy();
        String actualItemName = CheckoutPage.readProductName();
        double actualPrice = CheckoutPage.readPrice();
        String actualUrl = CheckoutPage.readNewUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrl),
                "\nActual: %s\nExpected contains: %s".formatted(actualUrl, expectedUrl));
        Assert.assertEquals(actualItemName, expectedItemName, "Product name not matched");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price not matched");
    }

    @Test
    public void testPositive_changeItemQuantityInCartPage_expectedQuantityTotalPrice() {
        int itemQuantity = 2;
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        ProductsPage.clickOnFirstProductInJeansCategory();
        double expectedPrice = ProductPage.readProductPrice() * 2;

        ProductPage.clickButtonAddToCart();
        CartModuleInPage.clickOnButtonBuy();
        CartPage.changeItemQuantityTo(itemQuantity);
        double actualPrice = CartPage.readNewPrice();
        int actualQuantity = CartPage.readQuantity();

        Assert.assertEquals(actualPrice, expectedPrice, "Product price not matched");
        Assert.assertEquals(actualQuantity, itemQuantity, "Product quantity not matched");
    }

    @Test
    public void testPositive_deleteItemQuantityInCartPage_expectedEmptyCart() {
        String expectedMessage = "Jūsų krepšelyje nėra prekių";
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        ProductsPage.clickOnFirstProductInJeansCategory();

        ProductPage.clickButtonAddToCart();
        CartModuleInPage.clickOnButtonBuy();
        CartPage.clickOnRemoveFromCart();
        String actualMessage = CartPage.readMessage();
        boolean emptyCart = CartPage.isCartEmpty();

        Assert.assertTrue(emptyCart, "Cart is not empty");
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "\nActual: %s\nExpected contains: %s".formatted(actualMessage, expectedMessage));
    }
}
