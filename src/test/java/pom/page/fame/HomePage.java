package pom.page.fame;

import pom.page.Common;

import static pom.page.fame.Locator.HomePageLoc.*;

public class HomePage {
    public static void open() {
        Common.setUpChrome(8);
        Common.openUrl("https://fame.lt/");
    }

    public static void moveMouseToLoginRegisterButton() {
        Common.waitForPageLoadAndAjaxComplete(8);
        Common.moveMouseToLocatorWithActions(hyperlinkRegisterLogin);
    }

    public static void enterEmail(String mail) {
        Common.sendKeysToElement(formEmail, mail);
    }

    public static void enterPassword(String password) {
        Common.sendKeysToElement(formPassword, password);
    }

    public static void clickButtonLogin() {
        Common.clickOnElement(buttonSignIn);
    }

    public static String readWelcomeMessage() {
        return Common.getTextFromElement(divWelcomeMessage);
    }

    public static boolean isLogoutButtonExists() {
        Common.waitElementIsVisible(buttonSignOut, 8);
        return Common.isElementDisplayed(buttonSignOut);
    }
}
