package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VacanciesPage {
    
    SelenideElement subscriptionField = $(".account-login-social-icons");
    
    public VacanciesPage subscriptionFieldExistanceCheck() {
        subscriptionField.should(Condition.visible);

        return this;
    }
}
