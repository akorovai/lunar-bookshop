package dev.s24377.lunar_bookshop.supplier;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.s24377.lunar_bookshop.enums.SUPPLIER_TYPE;
import dev.s24377.lunar_bookshop.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private long contactNumber;

    @Enumerated(EnumType.STRING)
    private SUPPLIER_TYPE type;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "address_id", referencedColumnName = "id", unique = true)
    @JsonManagedReference
    private Address address;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Order> orders;
}

