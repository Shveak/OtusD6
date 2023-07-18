package org.otus.pages;

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
}