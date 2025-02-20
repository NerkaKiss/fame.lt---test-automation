package pom.test.fame;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.page.fame.HomePage;
import pom.page.fame.LoginPage;
import pom.test.TestBase;

public class LoginLogoutTest extends TestBase {

    @BeforeMethod
    @Override
    public void setUp() {
        HomePage.open();
    }

    @Test
    public void testPositive_loginWithValidCredentials_expectedWelcomeMessage() {
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

    @DataProvider(name = "dataProviderTestEmptyCredentials")
    public Object[][] provideDataTestEmptyCredentials() {
        return new Object[][]{
                {true, "", ""},
                {true, "", "89R!t9YJCS"},
                {false, "mite.trudge7178@eagereverest.com", ""}
        };
    }

    @Test(dataProvider = "dataProviderTestEmptyCredentials")
    public void testNegative_loginWithEmptyFields_expectedMessage(boolean isEmail, String email, String password) {
        String expectedMessageResult = "Please fill out this field";
        boolean expectedLoginButton = true;
        boolean expectedInputEmail = true;
        HomePage.moveMouseToLoginRegisterButton();
        HomePage.enterEmail(email);
        HomePage.enterPassword(password);
        HomePage.clickButtonLogin();
        String actualMessageResult =
                isEmail ? HomePage.readErrorMessageFromEmailInput() : HomePage.readErrorMessageFromPasswordInput();
        boolean actualLoginButton = HomePage.isLoginButtonExists();
        boolean actualInputEmail = HomePage.isInputEmailExists();

        Assert.assertTrue(actualMessageResult.contains(expectedMessageResult),
                "\nActual: %s\nExpected contains: %s".formatted(actualMessageResult, expectedMessageResult));
        Assert.assertEquals(actualLoginButton, expectedLoginButton, "Login button is not exists");
        Assert.assertEquals(actualInputEmail, expectedInputEmail, "Input email is not exists");
    }

    @Test
    public void testNegative_loginWithWrongPassword_expectedMessageURLTitle() {
        String expectedMessageResult = "Identifikavimas nepavyko";
        String expectedTitle = "Prisijungimas";
        String expectedUrl = "https://fame.lt/prisijungimas";
        HomePage.moveMouseToLoginRegisterButton();
        HomePage.enterEmail("mite.trudge7178@eagereverest.com");
        HomePage.enterPassword("myWrongPassword");
        HomePage.clickButtonLogin();
        String actualMessageResult = LoginPage.readErrorMessage();
        String actualTitle = LoginPage.readTitle();
        String actualUrl = LoginPage.readNewUrl();

        Assert.assertTrue(actualMessageResult.contains(expectedMessageResult),
                "\nActual: %s\nExpected contains: %s".formatted(actualMessageResult, expectedMessageResult));
        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void testPositive_logout_expectedLoginButtons() {
        boolean expectedLoginButton = true;
        boolean expectedInputEmail = true;
        boolean expectedInputPassword = true;
        HomePage.login();
        HomePage.moveMouseToLoginRegisterButton();
        HomePage.clickButtonLogout();
        HomePage.moveMouseToLoginRegisterButton();
        boolean actualLoginButton = HomePage.isLoginButtonExists();
        boolean actualInputEmail = HomePage.isInputEmailExists();
        boolean actualInputPassword = HomePage.isInputPasswordExists();

        Assert.assertEquals(actualLoginButton, expectedLoginButton, "Login button is not exists");
        Assert.assertEquals(actualInputEmail, expectedInputEmail, "Input email is not exists");
        Assert.assertEquals(actualInputPassword, expectedInputPassword, "Input password is not exists");
    }
}
