package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TestData {
    private String city;
    private LocalDate date;
    private String fullName;
    private String phone;
    private Boolean checkbox;
}