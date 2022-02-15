package ru.netology.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.data.TestData;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

/**
 * Метод для того, чтобы заполнить форму на получение кредитной карты
 */

public class FormToReceiveCreditCard {

    private static final long BETWEEN_DAY = 86_400_000;

    public static void fillCardForm() {
        TestData data = DataGenerator.GenerateUserForPositiveChecks();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
            $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
            $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
            $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
            $("[name=\"name\"]").setValue(data.getFullName());
            $("[name=\"phone\"]").setValue(data.getPhone());
            if (data.getCheckbox())
                $(Selectors.byClassName("checkbox__box")).click();
            $(Selectors.byText("Запланировать")).click();
        }

    public static void fillCardFormWithCompoundName() {
        TestData data = DataGenerator.GenerateUserWithCompoundName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithHyphenName() {
        TestData data = DataGenerator.GenerateUserWithHyphenName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithЁ() {
        TestData data = DataGenerator.GenerateUserWithЁ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithLatinInName() {
        TestData data = DataGenerator.GenerateUserLatinInName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithEmptyName() {
        TestData data = DataGenerator.GenerateUserWithEmptyName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithLatinInCity () {
        TestData data = DataGenerator.GenerateUserWithLatinInCity();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithEmptyCity () {
        TestData data = DataGenerator.GenerateUserWithEmptyCity();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }
    public static void fillCardFormWithLongNumber () {
        TestData data = DataGenerator.GenerateUserWithLongNumber();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithWrongPhoneNumberFormat () {
        TestData data = DataGenerator.GenerateUserWithWrongPhoneNumberFormat();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithShortPhoneNumber () {
        TestData data = DataGenerator.GenerateUserWithShortPhoneNumberFormat();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithEmptyPhoneNumber () {
        TestData data = DataGenerator.GenerateUserWithEmptyPhoneNumber();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithEmptyFields () {
        TestData data = DataGenerator.GenerateUserWithEmptyFields();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithUncheckedBox () {
        TestData data = DataGenerator.GenerateUserWithUncheckedBox();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void fillCardFormWithWrongDate () {
        TestData data = DataGenerator.GenerateUserWithWrongDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        $("[class='input__control'][type='tel']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][type='tel']").append(data.getDate().format(formatter)).pressEnter();
        $(Selectors.byAttribute("type", "text")).setValue(data.getCity());
        $("[name=\"name\"]").setValue(data.getFullName());
        $("[name=\"phone\"]").setValue(data.getPhone());
        if (data.getCheckbox())
            $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.byText("Запланировать")).click();
    }

    public static void setNextDay(LocalDate date) {
        $("[class='input__control'][type='tel']").setValue(date.toString());
    }

    /**
     * Метод, который меняет дату в календаре
     */

    public static void selectDayFromCalendar(long day) {
        $(Selectors.byClassName("input_type_tel")).click();
        String data = $(Selectors.byClassName("calendar__day_state_current")).getAttribute("data-day");
        long newDate = Long.valueOf(data) + BETWEEN_DAY * day;
        $(Selectors.byAttribute("data-day", String.valueOf(newDate))).click();
    }

}
