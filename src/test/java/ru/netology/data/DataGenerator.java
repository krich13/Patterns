package ru.netology.data;

import java.time.LocalDate;

public class DataGenerator {
    private String city;
    private LocalDate date;
    private String fullName;
    private String phone;


//конструктор
    public DataGenerator(String city, LocalDate date, String fullName, String phone, Boolean checkbox) {
        this.city = city;
        this.date = date;
        this.fullName = fullName;
        this.phone = phone;
        this.checkbox = checkbox;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }

    private Boolean checkbox;
}
