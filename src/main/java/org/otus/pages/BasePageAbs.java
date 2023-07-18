package org.otus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePageAbs<T> {

    long timeOut = Long.parseLong(System.getProperty("timeout.open.windows"));

    public T clickButton(String text) {
        $(String.format("[text = '%s']", text)).click();
        return (T) this;
    }

    public void clickButtonOkInModal() {
        $(By.id("android:id/button1")).click();
    }

    public T waitPageWithText(String text) {
        $(String.format("[text = '%s']", text)).should(Condition.visible, Duration.ofSeconds(timeOut));
        return (T) this;
    }

    public T waitAlertModal() {
        $(By.id("android:id/parentPanel")).should(Condition.visible, Duration.ofSeconds(timeOut));
        return (T) this;
    }

    public T sleep(long milliseconds) {
        Selenide.sleep(milliseconds);
        return (T) this;
    }

    public T waitIsOpenMainPage() {
        $(By.id("android:id/content")).should(Condition.visible, Duration.ofSeconds(timeOut));
        return (T) this;
    }

}
