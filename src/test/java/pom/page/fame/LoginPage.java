package pom.page.fame;

import pom.page.Common;

import static pom.page.fame.Locator.LoginPageLoc.*;

public class LoginPage {

    public static String readErrorMessage() {
        Common.waitElementIsVisible(divErrorMessage, 8);
        return Common.getTextFromElement(divErrorMessage);
    }

    public static String readTitle() {
        return Common.getTitle();
    }

    public static String readNewUrl() {
        return Common.getCurrentUrl();
    }
}
