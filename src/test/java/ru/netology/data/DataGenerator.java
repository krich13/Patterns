package ru.netology.data;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {

    private static Faker faker = new Faker (new Locale("ru"));
    private static String [] cities = {"Москва", "Краснодар", "Улан-Удэ", "Владивосток", "Пермь", "Омск", "Самара", "Томск", "Санкт-Петербург"};
    private static int randomCity = (int)Math. floor(Math. random() * cities.length);

    public static TestData GenerateUserForPositiveChecks () {
        TestData UserForPositiveChecks = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
    return UserForPositiveChecks;
    }

    public static TestData GenerateUserWithCompoundName () {
        TestData UserWithCompoundName = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                faker.name().nameWithMiddle(),
                faker.phoneNumber().phoneNumber(),
                true);
        return UserWithCompoundName;
    }

    public static TestData GenerateUserWithHyphenName () {
        TestData UserWithHyphenName = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                "Василий Хомяков-Крыскин",
                faker.phoneNumber().phoneNumber(),
                true);
        return UserWithHyphenName;
    }

    public static TestData GenerateUserWithЁ () {
        TestData UserWithЁ = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                "Василий Рёдкин",
                faker.phoneNumber().phoneNumber(),
                true);
        return UserWithЁ;
    }

    public static TestData GenerateUserLatinInName () {
        TestData UserWithLatinInName = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                "Hannah Murray",
                faker.phoneNumber().phoneNumber(),
                true);
        return UserWithLatinInName;
    }

    public static TestData GenerateUserWithEmptyName () {
        TestData UserWithWithEmptyName = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                "",
                faker.phoneNumber().phoneNumber(),
                true);
        return UserWithWithEmptyName;
    }

            public static TestData GenerateUserWithLatinInCity () {
        TestData UserWithWithWithLatinInCity  = new TestData(
                "Moscow",
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
        return UserWithWithWithLatinInCity;
    }
    public static TestData GenerateUserWithEmptyCity () {
        TestData UserWithEmptyCity = new TestData(
                "Moscow",
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
        return UserWithEmptyCity;
    }

    public static TestData GenerateUserWithLongNumber () {
        TestData UserWithLongNumber = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                faker.phoneNumber().subscriberNumber(30),
                true);
        return UserWithLongNumber;
    }
    public static TestData GenerateUserWithWrongPhoneNumberFormat () {
        TestData UserWithWrongPhoneNumberFormat = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                "8777",
                true);
        return UserWithWrongPhoneNumberFormat;
    }

    public static TestData GenerateUserWithShortPhoneNumberFormat () {
        TestData UserWithShortPhoneNumberFormat = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                faker.phoneNumber().subscriberNumber(3),
                true);
        return UserWithShortPhoneNumberFormat ;
    }
    public static TestData GenerateUserWithEmptyPhoneNumber () {
        TestData UserWithEmptyPhoneNumber = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                "",
                true);
        return UserWithEmptyPhoneNumber;
    }

    public static TestData GenerateUserWithEmptyFields () {
        TestData UserWithEmptyFields = new TestData(
                "",
                LocalDate.now().plusDays(5),
                "",
                "",
                false);
        return UserWithEmptyFields;
    }

    public static TestData GenerateUserWithUncheckedBox () {
        TestData UserWithUncheckedBox = new TestData(
                cities [randomCity],
                LocalDate.now().plusDays(5),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                false);
        return UserWithUncheckedBox;
    }

    public static TestData GenerateUserWithWrongDate () {
        TestData UserWithWrongDate = new TestData(
                cities [randomCity],
                LocalDate.now(),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber(),
                true);
        return UserWithWrongDate;
    }
}

