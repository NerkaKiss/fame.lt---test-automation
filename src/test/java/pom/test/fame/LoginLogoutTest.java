package pom.test.fame;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.page.fame.HomePage;
import pom.test.TestBase;

public class LoginLogoutTest extends TestBase {

    @BeforeMethod
    @Override
    public void setUp() {
        HomePage.open();
    }

    @Test
    public void testPositive_LoginWithValidCredentials_ExpectedWelcomeMessage() {
        String expectedResult = "SVEIKI, NERIJUS KIS";
        boolean expectedLogoutButton = true;
        HomePage.moveMouseToLoginRegisterButton();
        HomePage.enterEmail("mite.trudge7178@eagereverest.com");
        HomePage.enterPassword("89R!t9YJCS");
        HomePage.clickButtonLogin();
        HomePage.moveMouseToLoginRegisterButton();

        boolean actualLogoutButton = HomePage.isLogoutButtonExists();
        String actualResult = HomePage.readWelcomeMessage();
        Assert.assertTrue(actualResult.contains(expectedResult),
                "\nActual: %s\nExpected contains: %s".formatted(actualResult, expectedResult));
        Assert.assertEquals(actualLogoutButton, expectedLogoutButton, "Logout button is not visible");
    }
}
