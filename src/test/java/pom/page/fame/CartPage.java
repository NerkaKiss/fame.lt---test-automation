package pom.page.fame;

import org.openqa.selenium.Keys;
import pom.page.Common;

import static pom.page.Locator.CartPageLoc.*;

public class CartPage {
    public static String readProductName() {
        Common.waitElementIsVisible(hyperlinkProductName, 8);
        return Common.getTextFromElement(hyperlinkProductName);
    }

    public static double readPrice() {
        Common.waitForPageLoadAndAjaxComplete(8);
        return Double.parseDouble(Common.getTextFromElement(spanProductPrice)
                .replace("EUR", "").strip().replace(",", "."));
    }

    public static String readNewUrl() {
        return Common.getCurrentUrl();
    }

    public static void refreshPage() {
        Common.waitElementIsVisible(hyperlinkProductName, 8);
        Common.refreshPage();
    }

    public static void clickOnButtonBuy() {
        Common.waitElementIsVisible(hyperlinkProductName, 8);
        Common.clickOnElement(hyperlinkBuyCart);
    }

    public static void changeItemQuantityTo(int itemQuantity) {
        Common.waitElementIsVisible(hyperlinkProductName, 8);
        Common.sendKeysToElement(inputProductQuantity, Keys.BACK_SPACE);
        Common.sendKeysToElement(inputProductQuantity, String.valueOf(itemQuantity));
        Common.sendKeysToElement(inputProductQuantity, Keys.ENTER);
    }

    public static double readNewPrice() {
        Common.waitForTextToChange(spanProductPrice, 8);
        return Double.parseDouble(Common.getTextFromElement(spanProductPrice)
                .replace("EUR", "").strip().replace(",", "."));
    }

    public static int readQuantity() {
        return Integer.parseInt(Common.getElementValueByTag(inputProductQuantity, "value"));
    }

    public static void clickOnRemoveFromCart() {
        Common.waitElementIsVisible(hyperlinkBuyCart, 8);
        Common.clickOnElement(hyperlinkRemoveFromCart);
    }

    public static boolean isCartEmpty() {
        return !Common.isElementDisplayedBySize(divProductGrid);
    }

    public static String readMessage() {
        Common.waitElementIsVisible(spanMessageNoItems, 8);
        return Common.getTextFromElement(spanMessageNoItems);
    }
}
