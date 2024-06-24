package dev.s24377.lunar_bookshop.employee.cashier;

import dev.s24377.lunar_bookshop.enums.ACCESS_LEVEL;
import dev.s24377.lunar_bookshop.enums.GENDER;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CashierDTO {
    private String name;
    private String surname;
    private GENDER gender;
    private LocalDate hireDate;
    private ACCESS_LEVEL accessLevel;
}
