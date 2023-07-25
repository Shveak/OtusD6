package org.otus.driver;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumSelenide implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setPlatformName(Platform.ANDROID.name());
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName(System.getProperty("device.name"));
        options.setAppPackage("com.pyankoff.andy");
        options.setAppActivity("com.pyankoff.andy.MainActivity");
        options.setNewCommandTimeout(Duration.ofSeconds(Long.parseLong(System.getProperty("new.command.timeout"))));
        try {
            return new AndroidDriver(new URL(System.getProperty("remote.driver.url")), options);
        } catch (SessionNotCreatedException e) {
            if (e.getMessage().contains("'app' option is required for reinstall")) {
                options.setAppPackage(null);
                options.setAppActivity(null);
                options.setApp(System.getProperty("path.apk.app") + "\\Andy-253457-d7ad79.apk");
                try {
                    return new AndroidDriver(new URL(System.getProperty("remote.driver.url")), options);
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                throw new RuntimeException(e);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
