package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataGenerator;
import ru.netology.data.TestData;
import ru.netology.pageObjects.PageObject;

import java.time.LocalDate;


import static com.codeborne.selenide.Selenide.*;


public class ApplicationFormTests {
    PageObject pageObject = new PageObject();

    @BeforeAll
    static void setupAll() {
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    LocalDate date = DataGenerator.generateDate();

    //Положительные тесты
    @Test
    void correctDataPlusDoubleClickOnBook() {
        TestData data = DataGenerator.generateUserForPositiveChecks();
        pageObject.fillCardForm(data, date);
        pageObject.successLoginPage(date);
        pageObject.clickBookForASecondTime(date);
    }

    @Test
    void correctDataWithCompoundName() {
        TestData data = DataGenerator.generateUserWithCompoundName();
        pageObject.fillCardForm(data, date);
        pageObject.successLoginPage(date);
    }

    @Test
    void nameWithHyphen() {
        TestData data = DataGenerator.generateUserWithHyphenName();
        pageObject.fillCardForm(data, date);
        pageObject.successLoginPage(date);
    }

    @Test
    void nameWithЁInName() { //падает так как нельзя использовать имя с ё
        TestData data = DataGenerator.generateUserWithЁ();
        pageObject.fillCardForm(data, date);
        pageObject.successLoginPage(date);
    }

    //Негативные тесты

    @Test
    void latinInName() {
        TestData data = DataGenerator.generateUserLatinInName();
        pageObject.fillCardForm(data, date);
        pageObject.failLoginPageWithWrongCredentials();
    }

    @Test
    void latinInCity() {
        TestData data = DataGenerator.generateUserWithLatinInCity();
        pageObject.fillCardForm(data, date);
        pageObject.deliveryIsNotAvailable();
    }

    @Test
    void emptyName() {
        TestData data = DataGenerator.generateUserWithEmptyName();
        pageObject.fillCardForm(data, date);
        pageObject.fieldMustBeFilled();
    }


    @Test
    void emptyCity() {
        TestData data = DataGenerator.generateUserWithEmptyCity();
        pageObject.fillCardForm(data, date);
        pageObject.deliveryIsNotAvailable();
    }

    @Test
    void emptyPhoneNumber() {
        TestData data = DataGenerator.generateUserWithEmptyPhoneNumber();
        pageObject.fillCardForm(data, date);
        pageObject.fieldMustBeFilled();
    }


    @Test
    void emptyFields() {
        TestData data = DataGenerator.generateUserWithEmptyFields();
        pageObject.fillCardFormWithOutCheckBox(data, date);
        pageObject.fieldMustBeFilled();
    }


    @Test
    void longPhoneNumber() { //падает, так как есть валидация поля, но мы не выдаем ошибку, что пользователь не может вводить более 11 цифр
        TestData data = DataGenerator.generateUserWithLongNumber();
        pageObject.fillCardForm(data, date);
        pageObject.putCorrectNumber();
    }

    @Test
    void wrongPhoneNumberFormat() { //падает, так как нет валидации
        TestData data = DataGenerator.generateUserWithWrongPhoneNumberFormat();
        pageObject.fillCardForm(data, date);
        pageObject.putCorrectNumber();
    }

    @Test
    void shortPhoneNumber() { //падает, так как нет валидации
        TestData data = DataGenerator.generateUserWithShortPhoneNumberFormat();
        pageObject.fillCardForm(data, date);
        pageObject.putCorrectNumber();
    }

    @Test
    void uncheckedBox() {
        TestData data = DataGenerator.generateUserForPositiveChecks();
        pageObject.fillCardFormWithOutCheckBox(data, date);
        pageObject.failLoginWithoutCheckbox();
    }

    @Test
    void wrongDate() {
        LocalDate date = LocalDate.now();
        TestData data = DataGenerator.generateUserForPositiveChecks();
        pageObject.fillCardForm(data, date);
        pageObject.bookForWrongDate();
    }
}

