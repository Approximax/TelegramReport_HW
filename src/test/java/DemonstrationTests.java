import config.credentials.AuthCredentials;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.VacanciesPage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    void authorizationWithPassword() throws IOException {

        AuthCredentials config = ConfigFactory.create(AuthCredentials.class, System.getProperties());
        String content = "login=secret-login\npassword=secret-pass";
        Path propsPath = Paths.get("/tmp/secret.properties");
        Files.write(propsPath, content.getBytes(StandardCharsets.UTF_8));

        step("Открываем сайт", () -> {
            open("https://spb.hh.ru/");
        });

        step("Переходим на страницу авторизации", () -> {
            mainPage.authForm();
        });

        step("Вводим данные для авторизации", () -> {
            authorizationPage.setLogin(config.login())
                    .setPassword(config.password());
        });

        step("Проверяем, что авторизация прошла успешно", () -> {
            authorizationPage.successfulAuthorizationCheck();
        });

        Files.delete(propsPath);
    }
}


