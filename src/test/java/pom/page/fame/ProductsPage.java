package pom.page.fame;

import pom.page.Common;

import static pom.page.Locator.ProductsPageLoc.*;

public class ProductsPage {
    public static void clickOnFirstProductInJeansCategory() {
        Common.waitForPageLoadAndAjaxComplete(8);
        Common.waitElementIsVisible(divFirstProduct, 8);
        Common.clickOnElement(divFirstProduct);
    }
}
