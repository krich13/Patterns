package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataGenerator;
import ru.netology.data.TestData;
import ru.netology.pageObjects.FormToReceiveCreditCard;

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
        TestData data = DataGenerator.generateUserForPositiveChecks();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.clickBookForASecondTime(data);
    }

    @Test
    void correctDataWithCompoundName() {
        TestData data = DataGenerator.generateUserWithCompoundName();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.clickBookForASecondTime(data);
    }

    @Test
    void nameWithHyphen() {
        TestData data = DataGenerator.generateUserWithHyphenName();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.clickBookForASecondTime(data);
    }

    @Test
    void nameWithЁInName() { //падает так как нельзя использовать имя с ё
        TestData data = DataGenerator.generateUserWithЁ();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.successLoginPage(data);
    }

    //Негативные тесты

    @Test
    void latinInName() {
        TestData data = DataGenerator.generateUserLatinInName();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.failLoginPageWithWrongCredentials(data);
    }

    @Test
    void latinInCity() {
        TestData data = DataGenerator.generateUserWithLatinInCity();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.deliveryIsNotAvailable(data);
    }

    @Test
    void emptyName() {
        TestData data = DataGenerator.generateUserWithEmptyName();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.fieldMustBeFilled(data);
    }


    @Test
    void emptyCity() {
        TestData data = DataGenerator.generateUserWithEmptyCity();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.deliveryIsNotAvailable(data);
    }

    @Test
    void emptyPhoneNumber() {
        TestData data = DataGenerator.generateUserWithEmptyPhoneNumber();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.fieldMustBeFilled(data);
    }


    @Test
    void emptyFields() {
        TestData data = DataGenerator.generateUserWithEmptyFields();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.fieldMustBeFilled(data);
    }


    @Test
    void longPhoneNumber() { //падает, так как есть валидация поля, но мы не выдаем ошибку, что пользователь не может вводить более 11 цифр
        TestData data = DataGenerator.generateUserWithLongNumber();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.putCorrectNumber(data);
    }

    @Test
    void wrongPhoneNumberFormat() { //падает, так как нет валидации
        TestData data = DataGenerator.generateUserWithWrongPhoneNumberFormat();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.putCorrectNumber(data);
    }

    @Test
    void shortPhoneNumber() { //падает, так как нет валидации
        TestData data = DataGenerator.generateUserWithShortPhoneNumberFormat();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.putCorrectNumber(data);
    }

    @Test
    void uncheckedBox() {
        TestData data = DataGenerator.generateUserWithUncheckedBox();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.failLoginWithoutCheckbox(data);
    }

    @Test
    void wrongDate() {
        TestData data = DataGenerator.generateUserWithWrongDate();
        FormToReceiveCreditCard.fillCardForm(data);
        FormToReceiveCreditCard.bookForWrongDate(data);
    }
}

