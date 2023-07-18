package org.otus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.otus.extensions.MyExtension;
import org.otus.pages.ChatPage;
import org.otus.pages.ExercisePage;
import org.otus.pages.InitialPage;

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
                .checkLastAnswer("My name is Andy. What is your name?")
                .sendKeys("My name is Sasha")
                .sleep(2000)
                .checkPreLastAnswer("Nice to meet you, Sasha")
                .checkLastAnswer("How are you?")
                .sendKeys("Good")
                .sleep(2000)
                .checkPreLastAnswer("I'm glad to hear that\uD83D\uDE0A")
                .checkLastAnswer("Where are you from?");
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
                .checkLastAnswer("Let's learn some words! As always, you can write 'STOP' to exit the game\uD83D\uDE09");
    }
}
