package dev.s24377.lunar_bookshop.client;

import dev.s24377.lunar_bookshop.enums.GENDER;
import lombok.Data;

@Data
public class NewClientDTO {
    private String name;
    private String surname;
    private GENDER gender;
}
