package pom.page.fame;

import pom.page.Common;

import static pom.page.Locator.CartPageLoc.*;

public class CartPage {
    public static String readProductName() {
        Common.waitElementIsVisible(hyperlinkProductName, 8);
        return Common.getTextFromElement(hyperlinkProductName);
    }

    public static String readPrice() {
        return Common.getTextFromElement(spanProductPrice);
    }

    public static String readNewUrl() {
        return Common.getCurrentUrl();
    }
}
