package dev.s24377.lunar_bookshop.employee;

import dev.s24377.lunar_bookshop.employee.cashier.Cashier;
import dev.s24377.lunar_bookshop.employee.cashier.CashierDTO;
import dev.s24377.lunar_bookshop.employee.cashier.CashierRepository;
import dev.s24377.lunar_bookshop.employee.section_manager.SectionManager;
import dev.s24377.lunar_bookshop.employee.section_manager.SectionManagerDTO;
import dev.s24377.lunar_bookshop.employee.section_manager.SectionManagerRepository;
import dev.s24377.lunar_bookshop.section.Section;
import dev.s24377.lunar_bookshop.section.SectionRepository;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

    private final CashierRepository cashierRepository;
    private final SectionManagerRepository sectionManagerRepository;

    private final SectionRepository sectionRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CashierDTO createCashier(CashierDTO cashierDTO) {
        Cashier cashier = new Cashier();
        cashier.setName(cashierDTO.getName());
        cashier.setSurname(cashierDTO.getSurname());
        cashier.setGender(cashierDTO.getGender());
        cashier.setHireDate(cashierDTO.getHireDate());
        cashier.setAccessLevel(cashierDTO.getAccessLevel());

        Cashier savedCashier = cashierRepository.save(cashier);
        return modelMapper.map(savedCashier, CashierDTO.class);
    }

    @Override
    @Transactional
    public SectionManagerDTO createSectionManager(SectionManagerDTO sectionManagerDTO) {

        Section section = sectionRepository.findById(sectionManagerDTO.getSectionId())
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));

        if (section.getManager() != null) {
            throw new IllegalArgumentException("This section already has a manager assigned");
        }

        SectionManager sectionManager = SectionManager.builder()
                .name(sectionManagerDTO.getName())
                .section(section)
                .surname(sectionManagerDTO.getSurname())
                .gender(sectionManagerDTO.getGender())
                .hireDate(sectionManagerDTO.getHireDate())
                .section(section)
                .build();


        SectionManager savedSectionManager = sectionManagerRepository.save(sectionManager);


        section.setManager(savedSectionManager);
        sectionRepository.save(section);

        return modelMapper.map(savedSectionManager, SectionManagerDTO.class);
    }
}

