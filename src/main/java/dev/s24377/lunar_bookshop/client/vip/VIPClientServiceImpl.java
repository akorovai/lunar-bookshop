package dev.s24377.lunar_bookshop.client.vip;

import dev.s24377.lunar_bookshop.enums.GENDER;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VIPClientServiceImpl implements VIPClientService {
    private final VIPClientRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public VIPClientDTO registerClient(String name, String surname, GENDER gender, Boolean generateLoyaltyCard) {
        Long cardNumber = null;
        if(generateLoyaltyCard) {

            boolean isUnique = false;
            do {
                cardNumber = VIPClient.generateLoyaltyCardNumber();
                isUnique = !repository.existsByLoyaltyCardNumber(cardNumber);
            } while (!isUnique);
        }
        VIPClient client = VIPClient.builder()
                .name(name)
                .surname(surname)
                .gender(gender)
                .registrationDate(LocalDate.now())
                .loyaltyCardNumber(cardNumber)
                .build();

        VIPClient savedClient = repository.save(client);
        return modelMapper.map(savedClient, VIPClientDTO.class);
    }

}
