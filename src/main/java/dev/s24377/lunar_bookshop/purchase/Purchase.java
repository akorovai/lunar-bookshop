package dev.s24377.lunar_bookshop.purchase;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.client.Client;
import dev.s24377.lunar_bookshop.enums.PURCHASE_TYPE;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate purchaseDate;

    @Enumerated(EnumType.STRING)
    private PURCHASE_TYPE type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "purchase_books",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonManagedReference
    @OrderBy("isbn ASC")
    @NotEmpty
    private List<Book> books = new ArrayList<>();


}
