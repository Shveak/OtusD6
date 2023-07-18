package org.otus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByAttribute;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.otus.components.ChatTab;
import org.otus.components.TabMenuComp;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static org.otus.utils.Utils.getMaskBoundsX;
import static org.otus.utils.Utils.getMaskBoundsY;

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

    public ChatPage checkPreLastAnswer(String text) {
        Assertions.assertEquals(text.trim(), getLastAnswer(getChat().get(2)).replace("\uF5A4", "").trim());
        return this;
    }
    public ChatPage checkLastAnswer(String text) {
        Assertions.assertEquals(text.trim(), getLastAnswer(getChat().get(1)).replace("\uF5A4", "").trim());
        return this;
    }

    public ChatPage checkLastQuestion(String text) {
        Assertions.assertEquals(text.trim(), getLastAnswer(getChat().get(2)).replace("\uF5A4", "").trim());
        return this;
    }

    private String getAnswerChat() {
        ElementsCollection collection = this.scrollView.findAll(ByAttribute.className("android.widget.TextView"));
        String maskLastAnswer = getMaskBoundsY(collection.first().attr("bounds"));
        return collection.stream()
                .filter(x -> getMaskBoundsY(x.attr("bounds")).equals(maskLastAnswer))
                .map(x -> x.attr("text"))
                .collect(Collectors.joining(" "));
    }

    private String getLastAnswer(SelenideElement element) {
        List<SelenideElement> listElementsWords = element.findAll(ByAttribute.className("android.widget.TextView"));
//                By.cssSelector("android.widget.TextView"));
        return listElementsWords.stream()
//                .limit(listElementsWords.size() - 1)
                .map(x -> x.attr("text"))
                .collect(Collectors.joining(" "));
    }

    private Map<Integer, SelenideElement> getChat() {
        List<SelenideElement> elementList = scrollView.findAll("[focusable = 'false']");
        String maskLastAnswer = getMaskBoundsX(scrollView.attr("bounds"));
        AtomicInteger index = new AtomicInteger(0);
        return elementList.stream()
                .skip(1)
                .filter(x -> getMaskBoundsX(x.attr("bounds")).equals(maskLastAnswer))
                .collect(Collectors.toMap((x) -> index.incrementAndGet(), (x) -> x));
    }
}
