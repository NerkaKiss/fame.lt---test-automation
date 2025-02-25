package pom.page.fame;

import pom.page.Common;

import static pom.page.Locator.SearchPageLoc.*;

public class SearchPage {
    public static boolean isAllTitlesContains(String searchQuery) {
        Common.waitElementIsVisible(headingProductTitle, 8);
        for (String textFromElement : Common.getTextFromElements(headingProductTitle)) {
            if (!textFromElement.toLowerCase().contains(searchQuery.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public static String readNewUrl() {
        return Common.getCurrentUrl();
    }
}
