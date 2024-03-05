package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    SelenideElement loginButton = $("[data-qa='login']"),
                    searchInput = $("[data-qa='search-input']");

    public MainPage authForm() {
        loginButton.click();

        return this;
    }

    public MainPage setSearch(String value) {
        searchInput.setValue(value).pressEnter();

        return this;
    }
}
