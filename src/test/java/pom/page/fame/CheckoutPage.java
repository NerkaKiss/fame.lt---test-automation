package pom.page.fame;

import pom.page.Common;

import static pom.page.Locator.CheckoutPageLoc.*;

public class CheckoutPage {
    public static String readProductName() {
        Common.waitElementIsVisible(paragraphProductName, 10);
        String productName = Common.getTextFromElement(paragraphProductName);
        return productName.substring(0, productName.length() - 20);
    }

    public static double readPrice() {
        return Double.parseDouble(Common.getTextFromElement(spanProductPrice)
                .replace("EUR", "").strip().replace(",", "."));
    }

    public static String readNewUrl() {
        return Common.getCurrentUrl();
    }
}
