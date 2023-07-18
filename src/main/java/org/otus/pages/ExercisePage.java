package org.otus.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.otus.components.ExerciseTab;
import org.otus.components.TabMenuComp;

import static com.codeborne.selenide.Selenide.$;

public class ExercisePage extends BasePageAbs<ExercisePage> {

    private final TabMenuComp exerciseTab = new ExerciseTab();

    private final SelenideElement scrollView = $(By.className("android.widget.ScrollView"));
    private final SelenideElement textOnPage = $("[text = 'Learn 5 new words today']");
    private final SelenideElement startButton = scrollView.find("[clickable = 'true']").find("[text = 'Start']");

    public ExercisePage open() {
        if (!exerciseTab.isSelected()) {
            exerciseTab.click();
        }
        return this;
    }

    public ExercisePage isVisible() {
        scrollView.should(Condition.visible);
        textOnPage.should(Condition.visible);
        startButton.should(Condition.visible);
        return this;
    }

    public ChatPage clickStartButton() {
        startButton.click();
        return new ChatPage();
    }
}
