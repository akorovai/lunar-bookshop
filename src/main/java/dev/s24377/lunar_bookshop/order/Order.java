package dev.s24377.lunar_bookshop.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.s24377.lunar_bookshop.order_book.OrderBook;
import dev.s24377.lunar_bookshop.supplier.Supplier;
import dev.s24377.lunar_bookshop.enums.ORDER_TYPE;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "[order]")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @PastOrPresent
    private LocalDate orderedDate;

    @Enumerated(EnumType.STRING)
    private ORDER_TYPE type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @NotNull
    private Supplier supplier;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @NotEmpty
    private List<OrderBook> orderBooks = new ArrayList<>();

}
