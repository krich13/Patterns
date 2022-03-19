package ru.netology.data;
import lombok.Value;

import java.time.LocalDate;

@Value
public class TestData {
    public TestData(String city, String fullName, String phone) {
        this.city = city;
        this.fullName = fullName;
        this.phone = phone;
    }

    private String city;
    private String fullName;
    private String phone;

}
