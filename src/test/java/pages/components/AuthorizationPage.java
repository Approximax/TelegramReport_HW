package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage {

    SelenideElement loginField = $("[data-qa='login']"),
                    loginWithPassword = $("[data-qa='expand-login-by-password']"),
                    passwordField = $("[data-qa='login-input-password']"),
                    loginSubmit = $("[data-qa='account-login-submit']"),
                    loginSocialButtons = $(".account-login-social-icons"),
                    myEvents = $(".my-events");

    public AuthorizationPage setLogin(String value) {
        loginField.setValue(value);

        return this;
    }

    public AuthorizationPage setPassword(String value) {
        loginWithPassword.click();
        passwordField.setValue(value);

        return this;
    }

    public AuthorizationPage authSubmit() {
        loginSubmit.click();

        return this;
    }

    public void loginSocialButtonsCheck() {
        loginSocialButtons.should(Condition.visible);

    }

    public void successfulAuthorizationCheck() {
        myEvents.shouldBe(Condition.visible);
    }
}
