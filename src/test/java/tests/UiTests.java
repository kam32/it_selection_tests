package tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class UiTests extends TestBaseUi {

    @Test
    @AllureId("")
    @Feature("Header")
    @DisplayName("Проверить размер и текст заголовка")
    void checkHeader() {
        step("Открываем главную страницу", () -> {
            open("");
        });
        step("Проверить, что заголовок содержит \"IT SELECTION AGENCY\" ", () -> {
            $(".t-container", 0).shouldHave(text("IT SELECTION AGENCY"));
        });
        step("Проверить, что размер заголовка равен 102px", () -> {
            $(".t-container", 0).$(byText("IT SELECTION AGENCY"))
                    .shouldHave(cssValue("font-size", "102px"));
        });
    }

    @Test
    @AllureId("")
    @Feature("Our Services")
    @DisplayName("Проверить появления текста при наведении мыши на блоке в разделе \"Our Services\"")
    void checkOurServicesFrame() {
        step("Открываем главную страницу", () -> {
            open("");
        });
        step("Навести мышь на блок с \"HR SERVICES\"", () -> {
            $(".t-container", 2).$(byText("HR SERVICES")).hover();
        });
        step("Проверить, что блок раскрылся, тест появился", () -> {
            $(".t-container", 2).$(withText("Outsourcing of HR processes")).shouldBe(visible);
        });
    }

    @Test
    @AllureId("")
    @Feature("OUR CORE TEAM")
    @DisplayName("Проверить видимость фотографии сотрудника в разделе \"OUR CORE TEAM\" ")
    void checkPhotoVisibility() {
        step("Открываем главную страницу", () -> {
            open("");
        });
        step("Проверить видимость фотографии первого сотрудника слева", () -> {
            $(".t-container > div", 5).$("[data-original*='jpg']").scrollTo().shouldBe(visible);
        });
    }

    @Test
    @AllureId("")
    @Feature("OUR CLIENTS")
    @DisplayName("Проверить количество клиентов в разделе \"OUR CLIENTS\" в первом блоке")
    void checkClientsBlockSize() {
        step("Открываем главную страницу", () -> {
            open("");
        });
        step("Проверить, что количество клиентов в первом блоке равно 6", () -> {
            $(".t-slds__container").scrollTo().$$("[class*='row']").filter(visible).shouldHaveSize(6);
        });
    }

    @Test
    @AllureId("")
    @Feature("Sing up Form")
    @DisplayName("Ввести в форму обратной связи некорректные данные")
    void unsuccessfulSignUpFormTest() {
        step("Открываем главную страницу", () -> {
            open("");
        });
        step("Ввести имя, некорректный адрес почты, телефон ", () -> {
            $(byName("Name")).val("Test");
            $(byName("Email")).val("pcjapjpjfapfj");
            $(byName("Phone")).val("ajpfjpajca");

        });
        step("Нажать 'Sign up'", () -> {
            $(".t-form__submit").click();
        });

        step("Проверить, что появилось сообщение \"Please put a correct e-mail\"", () -> {
            $(".t-form__submit").click();
            $(".s-errorbox-all").$(byText("Please put a correct e-mail"));
        });

        step("Проверить, что появилось сообщение \"Please put a correct phone number\"", () -> {
            $(".t-form__submit").click();
            $(".s-errorbox-all").$(byText("Please put a correct phone number"));
        });
    }
}


