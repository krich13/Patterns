package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("ru"));
    private final static String[] cities = {"Москва", "Краснодар", "Улан-Удэ", "Владивосток", "Пермь", "Омск", "Самара", "Томск", "Санкт-Петербург"};
    private static int randomCity = (int) Math.floor(Math.random() * cities.length);

    public static TestData generateUserForPositiveChecks() {
        TestData UserForPositiveChecks = new TestData(
                cities[randomCity],
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
        return UserForPositiveChecks;
    }

    public static TestData generateUserWithCompoundName() {
        TestData data = generateUserForPositiveChecks();
        data.setFullName(faker.name().nameWithMiddle());
        return data;
    }

    public static TestData generateUserWithHyphenName() {
        TestData data = generateUserForPositiveChecks();
        data.setFullName("Василий Хомяков-Крыскин");
        return data;
    }

    public static TestData generateUserWithЁ() {
        TestData data = generateUserForPositiveChecks();
        data.setFullName("Василий Рёдкин");
        return data;
    }

    public static TestData generateUserLatinInName() {
        TestData data = generateUserForPositiveChecks();
        data.setFullName("Hannah Murray");
        return data;
    }

    public static TestData generateUserWithEmptyName() {
        TestData data = generateUserForPositiveChecks();
        data.setFullName("");
        return data;
    }

    public static TestData generateUserWithLatinInCity() {
        TestData data = generateUserForPositiveChecks();
        data.setCity("Moscow");
        return data;
    }

    public static TestData generateUserWithEmptyCity() {
        TestData data = generateUserForPositiveChecks();
        data.setCity("");
        return data;
    }

    public static TestData generateUserWithLongNumber() {
        TestData data = generateUserForPositiveChecks();
        data.setPhone(faker.phoneNumber().subscriberNumber(30));
        return data;
    }

    public static TestData generateUserWithWrongPhoneNumberFormat() {
        TestData data = generateUserForPositiveChecks();
        data.setPhone("8777");
        return data;
    }

    public static TestData generateUserWithShortPhoneNumberFormat() {
        TestData data = generateUserForPositiveChecks();
        data.setPhone(faker.phoneNumber().subscriberNumber(3));
        return data;
    }

    public static TestData generateUserWithEmptyPhoneNumber() {
        TestData data = generateUserForPositiveChecks();
        data.setPhone("");
        return data;
    }

    public static TestData generateUserWithEmptyFields() {
        TestData data = generateUserForPositiveChecks();
        data.setPhone("");
        data.setDate(LocalDate.now().plusDays(5));
        data.setFullName("");
        data.setCity("");
        data.setCheckbox(false);
        return data;
    }

    public static TestData generateUserWithUncheckedBox() {
        TestData data = generateUserForPositiveChecks();
        data.setCheckbox(false);
        return data;
    }

    public static TestData generateUserWithWrongDate() {
        TestData data = generateUserForPositiveChecks();
        data.setDate(LocalDate.now());
        return data;
    }
}

