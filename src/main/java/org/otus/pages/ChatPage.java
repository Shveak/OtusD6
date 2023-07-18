package org.otus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByAttribute;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.otus.components.ChatTab;
import org.otus.components.TabMenuComp;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static org.otus.utils.Utils.getMaskBoundsX;

public class ChatPage extends BasePageAbs<ChatPage> {
    private final TabMenuComp chatTab = new ChatTab();
    private final SelenideElement scrollView = $(By.className("android.widget.ScrollView"));
    private final SelenideElement editText = $(AppiumBy.ByAccessibilityId.accessibilityId("Type a message..."));
    private final SelenideElement sendButton = $(AppiumBy.ByAccessibilityId.accessibilityId("send"));

    public ChatPage open() {
        if (!chatTab.isSelected()) {
            chatTab.click();
        }
        return this;
    }

    public ChatPage isVisible() {
        scrollView.should(Condition.visible);
        editText.should(Condition.visible);
        return this;
    }

    public ChatPage sendKeys(String text) {
        editText.sendKeys(text);
        sendButton.click();
        return this;
    }

    public ChatPage checkMessageInChat(String text, int index) {
        Assertions.assertEquals(text, getLastAnswer(getChat(index)));
        return this;
    }

    public ChatPage waitTextInChat(String text) {
        long end = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeOut);
        boolean result = false;
        do {
            String expectedText = getLastAnswer(getChat(1));
            if (text.equals(expectedText)) {
                result = true;
                break;
            }
            sleep(500);
        } while (System.currentTimeMillis() < end);
        Assertions.assertTrue(result, String.format("Время ожидания текста '%s' в чате истекло", text));
        return this;
    }

    private String getLastAnswer(SelenideElement element) {
        List<SelenideElement> listElementsWords = element.findAll(ByAttribute.className("android.widget.TextView"));
        return listElementsWords.stream()
                .map(x -> x.attr("text"))
                .filter(Objects::nonNull)
                .map(x -> x.replace("\uF5A4", ""))
                .collect(Collectors.joining(" ")).trim();
    }

    private SelenideElement getChat(int i) {
        List<SelenideElement> elementList = scrollView.findAll("[focusable = 'false']");
        String maskLastAnswer = getMaskBoundsX(scrollView.attr("bounds"));
        AtomicInteger index = new AtomicInteger(0);
        return elementList.stream()
                .filter(x -> getMaskBoundsX(x.attr("bounds")).equals(maskLastAnswer))
                .skip(1)
                .collect(Collectors.toMap((x) -> index.incrementAndGet(), (x) -> x)).get(i);
    }
}
