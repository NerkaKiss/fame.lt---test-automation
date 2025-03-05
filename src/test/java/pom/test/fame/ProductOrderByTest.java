package pom.test.fame;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

    @DataProvider(name = "dataProviderOrderBy")
    public Object[][] provideDataOrderBy() {
        return new Object[][]{
                {"A-Z", true, true},
                {"Z-A", true, false},
                {"LowestPrice", false, true},
                {"HighestPrice", false, false}
        };
    }

    @Test(dataProvider = "dataProviderOrderBy")
    public void testPositive_orderProductsBy(String orderBy, boolean byName, boolean isAscending) {
        boolean expectedResult = true;
        HomePage.moveMouseToWomanButton();
        HomePage.clickButtonJeans();
        ProductsPage.clickOnDropDownMenuButtonOrderBy();
        ProductsPage.clickOnButtonOrderBy(orderBy);
        boolean actualResult = byName ?
                ProductsPage.isProductsSortedByName(isAscending) : ProductsPage.isProductsSortedByPrice(isAscending);

        Assert.assertEquals(actualResult, expectedResult, "Sorted wrong");
    }
}
