package dev.s24377.lunar_bookshop.employee;

import dev.s24377.lunar_bookshop.employee.cashier.CashierDTO;
import dev.s24377.lunar_bookshop.employee.section_manager.SectionManagerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/cashiers")
    public CashierDTO createCashier(@RequestBody CashierDTO cashierDTO) {
        return employeeService.createCashier(cashierDTO);
    }

    @PostMapping("/section-managers")
    public SectionManagerDTO createSectionManager(@RequestBody SectionManagerDTO sectionManagerDTO) {
        return employeeService.createSectionManager(sectionManagerDTO);
    }
}
