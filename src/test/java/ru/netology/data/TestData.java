package ru.netology.data;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TestData {
    String city;
    LocalDate date;
    String fullName;
    String phone;
    Boolean checkbox;
}