import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class NotificationTest {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    void searchTest() {

        step("Открываем сайт", () -> {
            open("https://spb.hh.ru/")
        });

        step("Вводим в строку поиска вакансий тестовые данные", () -> {
            $("#a11y-search-input").setValue("selenide").pressEnter();
        });

        step("Проверяем наличие предложения о подписке на результат поиска", () -> {
            $("#vacancy-serp-subscription").shouldBe(Condition.visible);
        });
    }

    @Test
    void alternativeLoginOptionsTest() {

        step("Открываем сайт", () -> {
            open("https://spb.hh.ru/");
        });

        step("Переходим на страницу авторизации", () -> {
            $("[data-qa='login']").click();
        });
        
        step("Проверяем, что есть кнопки авторизации через сторонние ресурсы", () -> {
            $(".account-login-social-icons").should(Condition.visible);
        });
    }
}


