package pom.page.fame;

import pom.page.Common;

import static pom.page.Locator.ProductPageLoc.*;

public class ProductPage {
    public static String readProductName() {
        Common.waitElementIsVisible(headingProductName, 8);
        return Common.getTextFromElement(headingProductName);
    }

    public static double readProductPrice() {
        return Double.parseDouble(Common.getTextFromElement(divProductPrice)
                .replace("EUR", "").strip().replace(",", "."));
    }

    public static void clickButtonAddToCart() {
        Common.clickOnElement(divAddToCart);
    }

    public static boolean isSizeInProductDescriptionOrVariants(String size) {
        Common.waitElementIsVisible(headingProductName, 8);
        if (Common.isElementDisplayedBySize(divDescriptionBody)) {
            for (String text : Common.getTextFromElements(divDescriptionBody)) {
                if (text.replace("-", "/").contains(size)
                        || text.replace("/", "-").contains(size)) {
                    return true;
                }
            }
        }
        for (String variantText : Common.getTextFromElements(divProductVariants)) {
            if (variantText.contains(size)) {
                return true;
            }
        }
        return false;
    }
}
