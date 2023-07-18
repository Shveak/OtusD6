package org.otus.components;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public abstract class TabMenuComp {

    private final List<SelenideElement> scrollView = $(By.className("android.widget.HorizontalScrollView")).findAll("[focusable = 'true']");
    private final String nameTab;
    private final SelenideElement elementTab;

    public TabMenuComp(String nameTab) {
        super();
        this.nameTab = nameTab;
        this.elementTab = findTab();
    }

    public boolean isSelected() {
        return elementTab.isSelected();
    }

    public void click() {
        elementTab.click();
    }

    private SelenideElement findTab() {
        return scrollView.stream()
                .filter(x -> {
                    List<SelenideElement> elements = x.lastChild().findAll("[text = " + nameTab + "]");
                    return elements.size() == 1;
                })
                .findFirst()
                .orElseThrow(() -> new AssertionError(String.format("Вкладвка '%s' не найдна", nameTab)));
    }
}
