package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataGenerator;
import ru.netology.data.TestData;
import ru.netology.pageObjects.FormToReceiveCreditCard;

import java.time.LocalDate;


import static com.codeborne.selenide.Selenide.*;


public class ApplicationFormTests {

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
    void corectDataPlusDoubleClickOnBook() {
        TestData data = DataGenerator.generateUserForPositiveChecks();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.successLoginPage(date);
        FormToReceiveCreditCard.clickBookForASecondTime(date);
    }

    @Test
    void correctDataWithCompoundName() {
        TestData data = DataGenerator.generateUserWithCompoundName();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.successLoginPage(date);
    }

    @Test
    void nameWithHyphen() {
        TestData data = DataGenerator.generateUserWithHyphenName();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.successLoginPage(date);
    }

    @Test
    void nameWithЁInName() { //падает так как нельзя использовать имя с ё
        TestData data = DataGenerator.generateUserWithЁ();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.successLoginPage(date);
    }

    //Негативные тесты

    @Test
    void latinInName() {
        TestData data = DataGenerator.generateUserLatinInName();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.failLoginPageWithWrongCredentials();
    }

    @Test
    void latinInCity() {
        TestData data = DataGenerator.generateUserWithLatinInCity();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.deliveryIsNotAvailable();
    }

    @Test
    void emptyName() {
        TestData data = DataGenerator.generateUserWithEmptyName();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.fieldMustBeFilled();
    }


    @Test
    void emptyCity() {
        TestData data = DataGenerator.generateUserWithEmptyCity();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.deliveryIsNotAvailable();
    }

    @Test
    void emptyPhoneNumber() {
        TestData data = DataGenerator.generateUserWithEmptyPhoneNumber();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.fieldMustBeFilled();
    }


    @Test
    void emptyFields() {
        TestData data = DataGenerator.generateUserWithEmptyFields();
        FormToReceiveCreditCard.fillCardFormWithOutCheckBox(data, date);
        FormToReceiveCreditCard.fieldMustBeFilled();
    }


    @Test
    void longPhoneNumber() { //падает, так как есть валидация поля, но мы не выдаем ошибку, что пользователь не может вводить более 11 цифр
        TestData data = DataGenerator.generateUserWithLongNumber();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.putCorrectNumber();
    }

    @Test
    void wrongPhoneNumberFormat() { //падает, так как нет валидации
        TestData data = DataGenerator.generateUserWithWrongPhoneNumberFormat();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.putCorrectNumber();
    }

    @Test
    void shortPhoneNumber() { //падает, так как нет валидации
        TestData data = DataGenerator.generateUserWithShortPhoneNumberFormat();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.putCorrectNumber();
    }

    @Test
    void uncheckedBox() {
        TestData data = DataGenerator.generateUserForPositiveChecks();
        FormToReceiveCreditCard.fillCardFormWithOutCheckBox(data, date);
        FormToReceiveCreditCard.failLoginWithoutCheckbox();
    }

    @Test
    void wrongDate() {
        LocalDate date = LocalDate.now();
        TestData data = DataGenerator.generateUserForPositiveChecks();
        FormToReceiveCreditCard.fillCardForm(data, date);
        FormToReceiveCreditCard.bookForWrongDate();
    }
}

