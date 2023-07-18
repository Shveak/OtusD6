package org.otus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.otus.extensions.MyExtension;
import org.otus.pages.ChatPage;
import org.otus.pages.ExercisePage;

@ExtendWith(MyExtension.class)
public class TestAndy {

    @Test
    @DisplayName("Проверяем вкладку Chat")
    public void checkTabChat() {
        ChatPage chatPage = new ChatPage()
                .open()
                .isVisible();
        chatPage
                .sendKeys("Hello")
                .waitTextInChat("My name is Andy. What is your name?")
                .checkMessageInChat("Hello", 2)
                .sendKeys("My name is Sasha")
                .waitTextInChat("How are you?")
                .checkMessageInChat("My name is Sasha", 3)
                .checkMessageInChat("Nice to meet you, Sasha", 2)
                .sendKeys("Good")
                .waitTextInChat("Where are you from?")
                .checkMessageInChat("Good", 3)
                .checkMessageInChat("I'm glad to hear that\uD83D\uDE0A", 2);
    }

    @Test
    @DisplayName("Проверяем вкладку Exercise")
    public void checkTabExercise() {
        ExercisePage exercisePage = new ExercisePage()
                .open()
                .isVisible();
        ChatPage chatPage = exercisePage
                .clickStartButton();
        chatPage
                .isVisible()
                .waitPageWithText("\uD83E\uDD14 Explain")
                .checkMessageInChat("Hello\uD83D\uDE03", 3)
                .checkMessageInChat("Let's learn some words! As always, you can write 'STOP' to exit the game\uD83D\uDE09", 2);
    }
}
