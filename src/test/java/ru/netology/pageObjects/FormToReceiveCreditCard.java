package ru.netology.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.openqa.selenium.Keys;
import ru.netology.data.TestData;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class FormToReceiveCreditCard {

    private static final long BETWEEN_DAY = 86_400_000;

    public static void fillCardForm(TestData data, LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(date.format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void clickBookForASecondTime(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).should(Condition.appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text(date.format(formatter)));
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.byText("Перепланировать")).should(Condition.appear, Duration.ofSeconds(15));
        $(Selectors.byText("Перепланировать")).click();
        $(".notification__content").shouldHave(text(date.format(formatter)));
    }

    public static void fillCardFormWithOutCheckBox(TestData data, LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(date.format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        $(Selectors.byText("Запланировать")).click();
    }

    public static void successLoginPage(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $(Selectors.byText("Запланировать")).click();;
        $(".notification__content").shouldHave(text(date.format(formatter)));
    }

    public static void failLoginPageWithWrongCredentials() {
        $(Selectors.byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    public static void fieldMustBeFilled() {
        $(Selectors.byText("Поле обязательно для заполнения"));

    }

    public static void deliveryIsNotAvailable() {
        $(Selectors.byText("Доставка в выбранный город недоступна"));
    }

    public static void putCorrectNumber() {
        $(Selectors.withText("Поле обязательно для заполнения.Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."))
                .should(Condition.appear, Duration.ofSeconds(15));
    }

    public static void failLoginWithoutCheckbox() {
        $("[data-test-id='agreement']").shouldHave(Condition.cssClass("input_invalid"));
        $(Selectors.withText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).should(Condition.appear, Duration.ofSeconds(15));
    }

    public static void bookForWrongDate() {
        $(Selectors.withText("Заказ на выбранную дату невозможен"))
                .should(Condition.appear, Duration.ofSeconds(15));
    }
}


