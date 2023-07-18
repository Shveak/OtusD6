package org.otus.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.otus.driver.AppiumSelenide;

import static org.otus.pages.InitialPage.initApp;

public class MyExtension implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        Configuration.browserSize = null;
        Configuration.browser = AppiumSelenide.class.getName();
        Configuration.timeout = Long.parseLong(System.getProperty("selenide.timeout"));
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Selenide.closeWebDriver();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Selenide.open();
        initApp();
    }
}
