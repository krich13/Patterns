package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("ru"));
    private final static String[] cities = {"Москва", "Краснодар", "Улан-Удэ", "Владивосток", "Пермь", "Омск", "Самара", "Томск", "Санкт-Петербург"};
    private static int randomCity = (int) Math.floor(Math.random() * cities.length);


    public static String generateCity() {
        String city = cities[randomCity];
        return city;
    }

    public static String generateName() {
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static LocalDate generateDate() {
        LocalDate date = LocalDate.now().plusDays(5);
        return date;
    }

    public static TestData generateUserForPositiveChecks() {
        TestData UserForPositiveChecks = new TestData(generateCity(), generateName(), generatePhone());
        return UserForPositiveChecks;
    }

    public static TestData generateUserWithCompoundName() {
        TestData UserForPositiveChecks = new TestData(generateCity(), faker.name().nameWithMiddle(), generatePhone());
        return UserForPositiveChecks;
    }

    public static TestData generateUserWithHyphenName() {
        TestData userWithHyphenName = new TestData(generateCity(), "Василий Хомяков-Крыскин", generatePhone());
        return userWithHyphenName;
    }

    public static TestData generateUserWithЁ() {
        TestData userWithЁ = new TestData(generateCity(), "Василий Рёдкин", generatePhone());
        return userWithЁ;
    }

    public static TestData generateUserLatinInName() {
        TestData userLatinInName = new TestData(generateCity(), "Hannah Murray", generatePhone());
        return userLatinInName;
    }

    public static TestData generateUserWithEmptyName() {
        TestData userWithEmptyName = new TestData(generateCity(), "", generatePhone());
        return userWithEmptyName;
    }

    public static TestData generateUserWithLatinInCity() {
        TestData userWithLatinInCity = new TestData("Moscow", generateName(), generatePhone());
        return userWithLatinInCity;
    }

    public static TestData generateUserWithLongNumber() {
        TestData userWithEmptyCity = new TestData(generateCity(), generateName(), faker.phoneNumber().subscriberNumber(30));
        return userWithEmptyCity;
    }

    public static TestData generateUserWithWrongPhoneNumberFormat() {
        TestData data = new TestData(generateCity(), generateName(), "877");
        return data;
    }

    public static TestData generateUserWithShortPhoneNumberFormat() {
        TestData data = new TestData(generateCity(), generateName(), faker.phoneNumber().subscriberNumber(3));
        return data;
    }

    public static TestData generateUserWithEmptyPhoneNumber() {
        TestData data = new TestData(generateCity(), generateName(), "");
        return data;
    }

    public static TestData generateUserWithEmptyFields() {
        TestData data = new TestData("", "", "");
        return data;
    }

    public static TestData generateUserWithEmptyCity() {
        TestData data = new TestData("", generateName(), generatePhone());
        return data;
    }
}


