package dev.s24377.lunar_bookshop.employee.section_manager;


import dev.s24377.lunar_bookshop.enums.GENDER;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SectionManagerDTO {
    private String name;
    private String surname;
    private GENDER gender;
    private LocalDate hireDate;
    private Integer sectionId;
}

