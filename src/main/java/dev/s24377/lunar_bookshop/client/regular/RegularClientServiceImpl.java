package dev.s24377.lunar_bookshop.client.regular;

import dev.s24377.lunar_bookshop.enums.GENDER;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegularClientServiceImpl implements RegularClientSerivce {
    private final RegularClientRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public RegularClientDTO registerClient(String name, String surname, GENDER gender) {
        RegularClient client = RegularClient.builder().name(name).surname(surname).gender(gender).registrationDate(LocalDate.now()).build();

        RegularClient savedClient = repository.save(client);
        return modelMapper.map(savedClient, RegularClientDTO.class);
    }
}
