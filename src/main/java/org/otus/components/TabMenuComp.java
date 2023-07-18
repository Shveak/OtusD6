package org.otus.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public abstract class TabMenuComp {

    private final List<SelenideElement> scrollView = $(By.className("android.widget.HorizontalScrollView")).findAll("[focusable = 'true']");
    private String nameTab;
    private SelenideElement elementTab;

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
        return scrollView.stream().filter(x -> {
            List<SelenideElement> elements = x.lastChild().findAll("[text = " + nameTab + "]");
            if (elements.size() == 1) {
                return true;
            }
            return false;
        }).findFirst().get();
    }
}
