package pom.page.fame;

import pom.page.Common;

import static pom.page.Locator.ProductPageLoc.*;

public class ProductPage {
    public static String readProductName() {
        Common.waitElementIsVisible(headingProductName, 8);
        return Common.getTextFromElement(headingProductName);
    }

    public static String readProductPrice() {
        return Common.getTextFromElement(divProductPrice);
    }

    public static void clickButtonAddToCart() {
        Common.clickOnElement(divAddToCart);
    }
}
