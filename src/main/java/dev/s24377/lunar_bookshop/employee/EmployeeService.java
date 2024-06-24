package dev.s24377.lunar_bookshop.employee;


import dev.s24377.lunar_bookshop.employee.cashier.CashierDTO;
import dev.s24377.lunar_bookshop.employee.section_manager.SectionManagerDTO;

public interface EmployeeService {
    CashierDTO createCashier(CashierDTO cashierDTO);
    SectionManagerDTO createSectionManager(SectionManagerDTO sectionManagerDTO);
}
