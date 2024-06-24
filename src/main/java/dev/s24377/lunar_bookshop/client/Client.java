package dev.s24377.lunar_bookshop.client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.s24377.lunar_bookshop.complaint.Complaint;
import dev.s24377.lunar_bookshop.purchase.Purchase;
import dev.s24377.lunar_bookshop.enums.GENDER;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "custom_sequence",
            sequenceName = "client_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private GENDER gender;
    @NotNull
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Complaint> complaints = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Purchase> purchases = new ArrayList<>();


}
