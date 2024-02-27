import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class NotificationTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    @Tag("search")
    void searchTest() {

        step("Открываем сайт", () -> {
            open("https://spb.hh.ru/");
        });

        step("Вводим в строку поиска вакансий тестовые данные", () -> {
            $("[data-qa='search-input']").setValue("selenide").pressEnter();
        });

        step("Проверяем наличие предложения о подписке на результат поиска", () -> {
            $("[data-qa='autosearch-subscribe__form']").shouldBe(Condition.visible);
        });
    }

    @Test
    @Tag("login")
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


