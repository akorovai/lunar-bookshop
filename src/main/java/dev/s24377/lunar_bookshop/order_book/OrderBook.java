package dev.s24377.lunar_bookshop.order_book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "order_book")
public class OrderBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    @NotNull
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_isbn")
    @JsonBackReference
    @NotNull
    @OrderBy("isbn ASC")
    private Book book;
    @Min(value = 1, message = "Liczba zamówionych książek musi być wieksza od 0")
    private Integer quantity;


}
