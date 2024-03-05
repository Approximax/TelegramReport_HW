import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.components.AuthorizationPage;
import pages.components.MainPage;
import pages.components.VacanciesPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class DemonstrationTests extends TestBase {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    VacanciesPage vacanciesPage = new VacanciesPage();

    @Test
    @Owner("v.kryukov")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка наличия предложения о подписке на результаты поискового запроса")
    @Tag("search")
    void searchSubscriptionTest() {

        step("Открываем сайт", () -> {
            open("https://spb.hh.ru/");
        });

        step("Вводим в строку поиска вакансий тестовые данные", () -> {
            mainPage.setSearch("selenide");
        });

        step("Проверяем наличие предложения о подписке на результат поиска", () -> {
            vacanciesPage.subscriptionFieldExistanceCheck();
        });
    }

    @Test
    @Owner("v.kryukov")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка наличия возможности авторизации через сторонние ресурсы")
    @Tag("login")
    void alternativeLoginOptionsTest() {

        step("Открываем сайт", () -> {
            open("https://spb.hh.ru/");
        });

        step("Переходим на страницу авторизации", () -> {
            mainPage.authForm();
        });

        step("Проверяем, что есть кнопки авторизации через сторонние ресурсы", () -> {
            authorizationPage.loginSocialButtonsCheck();
        });
    }
    @Test
    @Owner("v.kryukov")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка наличия возможности авторизации через сторонние ресурсы")
    @Tag("login")
    void authorizationWithPassword() {

        step("Открываем сайт", () -> {
            open("https://spb.hh.ru/");
        });

        step("Переходим на страницу авторизации", () -> {
            mainPage.authForm();
        });

        step("Вводим данные для авторизации", () -> {
            authorizationPage.setLogin()
                    .setPassword();
        });

        step("Проверяем, что авторизация прошла успешно", () -> {
            authorizationPage.successfulAuthorizationCheck();
        });
    }
}


