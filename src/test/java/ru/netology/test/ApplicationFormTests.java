package ru.netology.test;

import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataGenerator;
import ru.netology.pageObjects.FormToReceiveCreditCard;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class ApplicationFormTests {

    private Faker faker;

    @BeforeAll
    static void setupAll() {
    }

    @BeforeEach
    void setup() {
        faker = new Faker(new Locale("ru"));
        open("http://localhost:9999/");
    }

    //Положительные тесты
    @Test
    void correctData() {  //заполнение формы корректными данными
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(3),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Перепланировать")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        $("[class='data-test-id'][type='success-notification']");
    }

    @Test
    void correctDataWithCompoundName() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(3),
                faker.name().nameWithMiddle(),
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Перепланировать")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        $("[class='data-test-id'][type='success-notification']");
    }

    @Test
    void NameWithHyphen() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(3),
                "Василий Хомяков-Крыскин",
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Перепланировать")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        $("[class='data-test-id'][type='success-notification']");
    }

    @Test
    void dateFromCalendar() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(7),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.selectDayFromCalendar(7);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
    }

    //Негативные тесты
    @Test
    void shouldFailWithЁinName() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(3),
                "Анна Селезнёва",
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
    }


    @Test
    void shouldFailWithLatinInName() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(3),
                "Anna Petrova",
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void emptyName() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(3),
                "",
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Поле обязательно для заполнения"));
    }

    @Test
    void latinInCity() {
        DataGenerator data = new DataGenerator(
                "Moscow",
                LocalDate.now().plusDays(3),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFailWithEmptyCity() {
        DataGenerator data = new DataGenerator(
                "",
                LocalDate.now().plusDays(3),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Доставка в выбранный город недоступна"));
    }

    @Test
    void longPhoneNumber() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(3),
                faker.name().fullName(),
                faker.phoneNumber().subscriberNumber(30),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения.Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."))
                .should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void wrongPhoneNumberFormat() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                "8777",
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения.Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."))
                .should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void shortPhoneNumber() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                faker.phoneNumber().subscriberNumber(3),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения.Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."))
                .should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void emptyPhoneNumber() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                faker.phoneNumber().subscriberNumber(0),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения.Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."))
                .should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void EmptyFields() {
        DataGenerator data = new DataGenerator(
                " ",
                LocalDate.now().plusDays(5),
                " ",
                " ",
                false);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Поле обязательно для заполнения")).should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void UncheckedBox() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now().plusDays(3),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                false);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $("[data-test-id='agreement']").shouldHave(Condition.cssClass("input_invalid"));
        $(Selectors.withText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).should(Condition.appear, Duration.ofSeconds(15));
    }

    @Test
    void wrongDate() {
        DataGenerator data = new DataGenerator(
                faker.address().city(),
                LocalDate.now(),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
        FormToReceiveCreditCard.fillCardForm(data);
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Заказ на выбранную дату невозможен"))
                .should(Condition.appear, Duration.ofSeconds(15));
    }
}
