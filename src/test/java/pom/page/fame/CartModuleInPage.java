package pom.page.fame;

import pom.page.Common;

import static pom.page.Locator.CartModuleInPageLoc.*;

public class CartModuleInPage {
    public static String readProductName() {
        Common.waitElementIsVisible(strongMessage, 8);
        return Common.getTextFromElement(spanProductName);
    }

    public static double readPrice() {
        return Double.parseDouble(Common.getTextFromElement(paragraphProductPrice)
                .replace("EUR", "").strip().replace(",", "."));
    }

    public static String readMessage() {
        return Common.getTextFromElement(strongMessage);
    }

    public static void clickOnButtonBuy() {
        Common.waitElementIsVisible(strongMessage, 8);
        Common.clickOnElement(hyperlinkBuyCart);
    }
}
