package pom.test.fame;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.page.fame.HomePage;
import pom.page.fame.SearchPage;
import pom.test.TestBase;

public class ProductSearchTest extends TestBase {
    @BeforeMethod
    @Override
    public void setUp() {
        HomePage.open();
    }

    @DataProvider(name = "dataProviderPositiveSearchQueries")
    public Object[][] provideDataPositiveSearchQueries() {
        return new Object[][]{
                {"daili suknelė", "daili suknelė", "Exact product name"}, //This test Asserts failed,
                // bad search logic in website
                {"suknelė", "suknelė", "Partial search"},
                {"100298439", "suknelė", "Search by SKU"},
                {"suknelė!@#", "suknelė", "Search with special characters"},
                {"SuKnElĖ", "suknelė", "Case-insensitive search"},
                {"   suknelė   ", "suknelė", "Search with spaces"}
        };
    }

    @Test(dataProvider = "dataProviderPositiveSearchQueries")
    public void testPositive_searchQueries_expectedUrlTitleNameContains(String searchQuery, String expectedResult, String description) {
        String expectedUrl = "https://fame.lt/paieska";
        String expectedTitle = "Paieška";
        HomePage.enterSearchQuery(searchQuery);
        HomePage.clickButtonSubmitSearch();
        boolean actualTitlesContains = SearchPage.isAllTitlesContains(expectedResult);
        String actualUrl = SearchPage.readNewUrl();
        String actualTitle = SearchPage.readTitle();

        Assert.assertTrue(actualTitlesContains, "\nTest: %s\nNot all titles contains %s".formatted(description, searchQuery));
        Assert.assertTrue(actualUrl.contains(expectedUrl),
                "\nActual: %s\nExpected contains: %s".formatted(actualUrl, expectedUrl));
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @DataProvider(name = "dataProviderNegativeSearchQueries")
    public Object[][] provideDataNegativeSearchQueries() {
        return new Object[][]{
                {"Xyz123!@#"},
                {"!@#$%^&*()"},
                {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}
        };
    }

    @Test(dataProvider = "dataProviderNegativeSearchQueries")
    public void testNegative_searchQueries(String searchQuery) {
        String expectedMessage = "There are no products in this category.";
        String expectedUrl = "https://fame.lt/paieska";
        String expectedTitle = "Paieška";
        HomePage.enterSearchQuery(searchQuery);
        HomePage.clickButtonSubmitSearch();
        String actualMessage = SearchPage.readNoProductsMessage();
        String actualUrl = SearchPage.readNewUrl();
        String actualTitle = SearchPage.readTitle();

        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "\nActual: %s\nExpected contains: %s".formatted(actualMessage, expectedMessage));
        Assert.assertTrue(actualUrl.contains(expectedUrl),
                "\nActual: %s\nExpected contains: %s".formatted(actualUrl, expectedUrl));
        Assert.assertEquals(actualTitle, expectedTitle);
    }

}