package pom.test.fame;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.page.fame.HomePage;
import pom.page.fame.ProductsPage;
import pom.test.TestBase;

public class ProductOrderByTest extends TestBase {
    @BeforeMethod
    @Override
    public void setUp() {
        HomePage.open();
    }

    @Test
    public void testNegative_orderBy_expectedSortedByNameAZ() {
        boolean expectedResult = true;
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        ProductsPage.clickOnButtonOrderBy();
        ProductsPage.clickOnButtonNameAZ();
        ProductsPage.refreshPage();
        boolean actualResult = ProductsPage.isProductsSorted();

        Assert.assertEquals(actualResult, expectedResult, "Not sorted by NameAZ");
    }
}
