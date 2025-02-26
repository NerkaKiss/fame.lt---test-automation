package pom.page.fame;

import pom.page.Common;

import java.util.List;

import static pom.page.Locator.ProductsPageLoc.*;

public class ProductsPage {
    public static void clickOnFirstProductInJeansCategory() {
        Common.waitForPageLoadAndAjaxComplete(8);
        Common.waitElementIsVisible(divProductList, 8);
        Common.clickOnElement(divProductList);
    }

    public static void clickOnButtonOrderBy() {
        Common.waitForPageLoadAndAjaxComplete(8);
        Common.waitElementIsVisible(divProductList, 8);
        Common.clickOnElement(hyperlinkDropDownMenu);
    }

    public static void clickOnButtonNameAZ() {
        Common.waitElementIsVisible(divOrderByNameAZ, 2);
        Common.clickOnElement(divOrderByNameAZ);
        Common.waitForTextToChange(hyperlinkDropDownMenu, 2);
    }

    public static String testukasAntras() {
        System.out.println(Common.getTextFromElement(hyperlinkDropDownMenu));
        Common.waitForTextToChange(hyperlinkDropDownMenu, 2);
        Common.waitForPageLoadAndAjaxComplete(5);
        System.out.println(Common.getTextFromElement(hyperlinkDropDownMenu));
        return Common.getTextFromElement(divProductTitle);
    }

    public static void refreshPage() {
        Common.refreshPage();
    }

    public static boolean isProductsSorted() {
        return Common.isSorted(Common.getTextFromElements(divProductTitle), true);
    }
}
