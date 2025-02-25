package pom.test.fame;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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

    @Test
    public void testPositive_testSearchQuery_expectedUrlTitleNameContains() {
        String searchQuery = "suknelė";
        String expectedUrl = "https://fame.lt/paieska";
        HomePage.enterSearchQuery(searchQuery);
        HomePage.clickButtonSubmitSearch();
        boolean actualTitlesContains = SearchPage.isAllTitlesContains(searchQuery);
        String actualUrl = SearchPage.readNewUrl();

        Assert.assertTrue(actualTitlesContains, "Not all titles contains %s".formatted(searchQuery));
        Assert.assertTrue(actualUrl.contains(expectedUrl),
                "\nActual: %s\nExpected contains: %s".formatted(actualUrl, expectedUrl));
    }
}
