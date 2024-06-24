package dev.s24377.lunar_bookshop.client.vip;

import dev.s24377.lunar_bookshop.client.Client;
import dev.s24377.lunar_bookshop.client.ClientRepository;
import dev.s24377.lunar_bookshop.enums.GENDER;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "vip_client")
public class VIPClient extends Client {

    @Column(name = "loyalty_card_number", unique = true)
    @Nullable
    private Long loyaltyCardNumber;

    public VIPClient(String name, String surname, GENDER gender, LocalDate registrationDate, boolean generateLoyaltyCard){
        super.setName(name);
        super.setSurname(surname);
        super.setGender(gender);
        super.setRegistrationDate(registrationDate);
        this.loyaltyCardNumber = generateLoyaltyCard ? VIPClient.generateLoyaltyCardNumber() : null;
    }


    public VIPClient(Client client, boolean generateLoyaltyCard, ClientRepository clientRepository) {

        super(client.getId(), client.getName(), client.getSurname(), client.getGender(), client.getRegistrationDate(), client.getComplaints(), client.getPurchases());
        if (clientRepository != null) {
            clientRepository.deleteById(client.getId());
        }

        this.loyaltyCardNumber = generateLoyaltyCard ? VIPClient.generateLoyaltyCardNumber() : null;
    }
    public static long generateLoyaltyCardNumber() {

        return (long) ((Math.random() * (999999999 - 100000000)) + 100000000);
    }
}
