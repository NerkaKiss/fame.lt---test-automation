package pom.page;

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
        public static final By spanWomanSection = By.xpath("//span[normalize-space()='Moterims']");
        public static final By ulJeansButton = By.xpath("(//ul[@id='top-menu']/li[2]//li)[1]");
    }

    public static class LoginPageLoc {
        public static final By divErrorMessage = By.xpath("//div[@class='help-block']//li");
    }

    public static class ProductsPageLoc {
        public static final By divFirstProduct = By.xpath("//div[@id='js-product-list']//article");
    }

    public static class ProductPageLoc {
        public static final By headingProductName = By.xpath("//h1[@itemprop='name']");
        public static final By divProductPrice = By.xpath("//div[@class='current-price']/span");
        public static final By divAddToCart = By.xpath("//div[@class='add']//button");
    }

    public static class CartModuleInPageLoc {
        public static final By spanProductName = By.xpath("//span[@class='h6 product-name']");
        public static final By paragraphProductPrice = By.xpath("//p[@class='price']");
        public static final By strongMessage = By.xpath("//strong[@id='myModalLabel']");
        public static final By hyperlinkBuyCart = By.xpath("//a[@class='btn btn-primary']");
    }

    public static class CartPageLoc {
        public static final By hyperlinkProductName = By.xpath("//a[@class='label product-name']");
        public static final By spanProductPrice = By.xpath("//span[@class='product-price']");
    }
}
