package dev.s24377.lunar_bookshop.client.regular;

import dev.s24377.lunar_bookshop.client.Client;
import dev.s24377.lunar_bookshop.client.ClientRepository;
import dev.s24377.lunar_bookshop.enums.GENDER;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "regular_client")
public class RegularClient extends Client {

    @Transient
    private final int HIGH_THRESHOLD = 10;
    @Transient
    private final int LOW_THRESHOLD = 5;
    @Transient
    private final double HIGH_DISCOUNT = 0.10;
    @Transient
    private final double LOW_DISCOUNT = 0.05;



    public RegularClient(Client client, ClientRepository clientRepository) {
        super(client.getId(), client.getName(), client.getSurname(), client.getGender(), client.getRegistrationDate(), client.getComplaints(), client.getPurchases());
        if (clientRepository != null) {
            clientRepository.deleteById(client.getId());
        }
        calculateDiscount();
    }

    public RegularClient(String name, String surname, GENDER gender, LocalDate registrationDate) {
        super.setName(name);
        super.setSurname(surname);
        super.setGender(gender);
        super.setRegistrationDate(registrationDate);
        calculateDiscount();
    }


    @Transient
    private double discount;

    @PostLoad
    @PostConstruct
    @PostUpdate
    public void calculateDiscount() {
        this.discount = Objects.isNull(this.getPurchases())
                ? 0.0 : this.getPurchases().size() > 10
                ? HIGH_DISCOUNT : this.getPurchases().size() > 5
                ? LOW_DISCOUNT : 0.0;
    }


}
