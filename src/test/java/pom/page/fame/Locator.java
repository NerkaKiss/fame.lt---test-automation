package pom.page.fame;

import org.openqa.selenium.By;

public class Locator {
    public static class HomePageLoc {
        public static final By hyperlinkRegisterLogin = By.xpath("//a[@title='Užsiregistruoti']");
        public static final By formEmail = By.xpath("//form[@id='login-form']//input[@name='email']");
        public static final By formPassword = By.xpath("//form[@id='login-form']//input[@name='password']");
        public static final By buttonSignIn = By.xpath("//button[@data-link-action='sign-in']");
        public static final By buttonSignOut = By.xpath(
                "//div[@class='indent']/a[@href='https://fame.lt/?mylogout=']");
        public static final By divWelcomeMessage = By.xpath("//div[@class='indent']");
    }

    public static class LoginPageLoc {
        public static final By divErrorMessage = By.xpath("//div[@class='help-block']//li");
    }
}
