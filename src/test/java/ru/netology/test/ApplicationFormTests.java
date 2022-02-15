package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.pageObjects.FormToReceiveCreditCard;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class ApplicationFormTests {

    @BeforeAll
    static void setupAll() {
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    //Положительные тесты
    @Test
    void correctData() {
        FormToReceiveCreditCard.fillCardForm();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
        String actualFirstResult = $(".notification__content").getText();
        Assertions.assertTrue(actualFirstResult.contains(LocalDate.now().plusDays(5).format(formatter)));
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Перепланировать")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        String actualResult = $(".notification__content").getText();
        Assertions.assertTrue(actualResult.contains(LocalDate.now().plusDays(5).format(formatter)));
    }

    @Test
    void correctDataWithCompoundName() {
        FormToReceiveCreditCard.fillCardFormWithCompoundName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
        String actualFirstResult = $(".notification__content").getText();
        Assertions.assertTrue(actualFirstResult.contains(LocalDate.now().plusDays(5).format(formatter)));
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Перепланировать")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        $("[class='data-test-id'][type='success-notification']");
        String actualResult = $(".notification__content").getText();
        Assertions.assertTrue(actualResult.contains(LocalDate.now().plusDays(5).format(formatter)));
    }

    @Test
    void NameWithHyphen() {
        FormToReceiveCreditCard.fillCardFormWithHyphenName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
        String actualFirstResult = $(".notification__content").getText();
        Assertions.assertTrue(actualFirstResult.contains(LocalDate.now().plusDays(5).format(formatter)));
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Перепланировать")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        $("[class='data-test-id'][type='success-notification']");
        String actualResult = $(".notification__content").getText();
        Assertions.assertTrue(actualResult.contains(LocalDate.now().plusDays(5).format(formatter)));
    }

    @Test
    void dateFromCalendar() {
        FormToReceiveCreditCard.fillCardForm();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        FormToReceiveCreditCard.selectDayFromCalendar(7);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
        String actualFirstResult = $(".notification__content").getText();
        Assertions.assertTrue(actualFirstResult.contains(LocalDate.now().plusDays(12).format(formatter)));
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Перепланировать")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        String actualResult = $(".notification__content").getText();
        Assertions.assertTrue(actualResult.contains(LocalDate.now().plusDays(12).format(formatter)));
    }

    //Негативные тесты
    @Test
    void shouldFailWithЁinName() {
        FormToReceiveCreditCard.fillCardFormWithЁ();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void shouldFailWithLatinInName() {
        FormToReceiveCreditCard.fillCardFormWithLatinInName();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void emptyName() {
        FormToReceiveCreditCard.fillCardFormWithEmptyName();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Поле обязательно для заполнения"));
    }

    @Test
    void latinInCity() {
        FormToReceiveCreditCard.fillCardFormWithLatinInCity();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFailWithEmptyCity() {
        FormToReceiveCreditCard.fillCardFormWithEmptyCity();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Доставка в выбранный город недоступна"));
    }

    @Test
    void longPhoneNumber() { //падает, так как есть валидация поля, но мы не выдаем ошибку, что пользователь не может вводить более 11 цифр
        FormToReceiveCreditCard.fillCardFormWithLongNumber();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения.Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."))
                .should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void wrongPhoneNumberFormat() { //падает, так как нет валидации
        FormToReceiveCreditCard.fillCardFormWithWrongPhoneNumberFormat();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения.Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."))
                .should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void shortPhoneNumber() { //падает, так как нет валидации
        FormToReceiveCreditCard.fillCardFormWithShortPhoneNumber();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения.Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."))
                .should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void emptyPhoneNumber() {
        FormToReceiveCreditCard.fillCardFormWithEmptyPhoneNumber();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения")).should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void EmptyFields() {
        FormToReceiveCreditCard.fillCardFormWithEmptyFields();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения")).should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void UncheckedBox() {
        FormToReceiveCreditCard.fillCardFormWithUncheckedBox();
        $(Selectors.byText("Запланировать")).click();
        $("[data-test-id='agreement']").shouldHave(Condition.cssClass("input_invalid"));
        $(Selectors.withText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void wrongDate() {
        FormToReceiveCreditCard.fillCardFormWithWrongDate();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Заказ на выбранную дату невозможен"))
                .should(Condition.appear, Duration.ofSeconds(15));
    }
}
