package org.otus.pages;

import org.otus.components.ChatTab;
import org.otus.components.ExerciseTab;
import org.otus.components.TabMenuComp;

public class InitialPage extends BasePageAbs<InitialPage> {

    public static void initApp() {
        new InitialPage()
                .waitIsOpenMainPage()
                .clickButton("Next")
                .waitPageWithText("Learn new words and grammar")
                .clickButton("Next")
                .waitPageWithText("7 days FREE")
                .clickButton("Skip >")
                .waitAlertModal()
                .clickButtonOkInModal();
    }

    //
//    public InitialPage alertModalIsVisible(boolean isVisible) {
//        if (isVisible) {
//            $(By.id("android:id/parentPanel")).should(Condition.visible);
//        } else {
//            $(By.id("android:id/parentPanel")).shouldNot(Condition.visible);
//        }
//        return this;
//    }
//    public InitialPage textOnPage(String text, boolean isVisible) {
//        if (isVisible) {
//            $(String.format("[text = '%s']", text)).should(Condition.visible);
//        } else {
//            $(String.format("[text = '%s']", text)).shouldNot(Condition.visible);
//        }
//        return this;
//    }
}