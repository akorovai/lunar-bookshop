package dev.s24377.lunar_bookshop.employee;


import dev.s24377.lunar_bookshop.section.Section;
import dev.s24377.lunar_bookshop.enums.GENDER;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    @PastOrPresent
    private LocalDate hireDate;

}
