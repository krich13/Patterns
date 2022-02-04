package ru.netology.pageObjects;

import com.codeborne.selenide.Selectors;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

/**
 * Метод для того, чтобы заполнить форму на получение кредитной карты
 */

public class FormToReceiveCreditCard {

    private static final long BETWEEN_DAY = 86_400_000;

    public static void fillCardForm(DataGenerator data) {
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
