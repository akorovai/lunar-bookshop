package dev.s24377.lunar_bookshop.employee.cashier;

import dev.s24377.lunar_bookshop.employee.Employee;
import dev.s24377.lunar_bookshop.enums.ACCESS_LEVEL;
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
@Table(name = "cashier")
public class Cashier extends Employee {

    @Enumerated(EnumType.STRING)
    private ACCESS_LEVEL accessLevel;

}
