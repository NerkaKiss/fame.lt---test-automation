package pom.test.fame;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.page.fame.HomePage;
import pom.page.fame.ProductsPage;
import pom.test.TestBase;

public class ProductFilterByTest extends TestBase {

    @BeforeMethod
    @Override
    public void setUp() {
        HomePage.open();
    }

    @Test
    public void testPositive_filteredProductListBySizeIsNotEmpty() {
        boolean expectedResult = true;
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        ProductsPage.clickOnFilterBySize("XS");
        int actualResult = ProductsPage.countFilteredProducts();
        boolean clearFilterButtonVisible = ProductsPage.isClearFilterButtonVisible();

        Assert.assertEquals(actualResult > 0, expectedResult, "No products after filter");
        Assert.assertTrue(clearFilterButtonVisible, "Clear filter button is not visible");
    }

    @Test
    public void testPositive_productCountDecreasesAfterFilteringBySize() {
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        int countBeforeFilter = ProductsPage.countProducts();
        ProductsPage.clickOnFilterBySize("XS");
        int countAfterFilter = ProductsPage.countFilteredProducts();

        Assert.assertTrue(countBeforeFilter > countAfterFilter,
                "Product count do not decreases after filtering");
    }
}
