package dev.s24377.lunar_bookshop.employee.section_manager;

import dev.s24377.lunar_bookshop.employee.Employee;
import dev.s24377.lunar_bookshop.section.Section;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "section_manager")
public class SectionManager extends Employee {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

}