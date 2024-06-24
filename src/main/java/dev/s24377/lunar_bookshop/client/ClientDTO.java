package dev.s24377.lunar_bookshop.client;

import dev.s24377.lunar_bookshop.enums.GENDER;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClientDTO {
    private Integer id;
    private String name;
    private String surname;
    private GENDER gender;
    private LocalDate registrationDate;
}
