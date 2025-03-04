package pom.page.fame;

import org.openqa.selenium.By;
import pom.page.Common;

import static pom.page.Locator.ProductsPageLoc.*;

public class ProductsPage {
    public static By divOrderByGenerator(String orderBy) {
        int orderNo = switch (orderBy) {
            case "A-Z" -> 2;
            case "Z-A" -> 3;
            case "LowestPrice" -> 4;
            case "HighestPrice" -> 5;
            default -> 2;
        };
        return By.xpath("//div[@class='dropdown-menu']/a[%s]".formatted(orderNo));
    }


    public static void clickOnFirstProductInJeansCategory() {
        Common.waitForPageLoadAndAjaxComplete(8);
        Common.waitElementIsVisible(divProductList, 8);
        Common.clickOnElement(divProductList);
    }

    public static void clickOnDropDownMenuButtonOrderBy() {
        Common.waitForPageLoadAndAjaxComplete(8);
        Common.waitElementIsVisible(divProductList, 8);
        Common.clickOnElement(hyperlinkDropDownMenu);
    }

    public static void clickOnButtonOrderBy(String orderBy) {
        By divOrderBy = divOrderByGenerator(orderBy);
        Common.waitElementIsVisible(divOrderBy, 2);
        Common.clickOnElement(divOrderBy);
        Common.waitForTextToChange(hyperlinkDropDownMenu, 2);
    }

    public static boolean isProductsSortedByName(boolean isAscending) {
        return Common.isSorted(Common.getTextFromElements(divProductTitle), isAscending);
    }

    public static boolean isProductsSortedByPrice(boolean isAscending) {
        return Common.isSorted(Common.getTextFromElements(divProductPrice), isAscending);
    }
}
