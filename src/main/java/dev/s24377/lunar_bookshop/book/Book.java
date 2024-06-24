package dev.s24377.lunar_bookshop.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.s24377.lunar_bookshop.complaint.Complaint;
import dev.s24377.lunar_bookshop.order_book.OrderBook;
import dev.s24377.lunar_bookshop.promotion.Promotion;
import dev.s24377.lunar_bookshop.purchase.Purchase;
import dev.s24377.lunar_bookshop.section.Section;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
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
@Table(name = "book")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @Id
    private Long isbn;

    private String title;
    private String author;
    @Min(value = 0, message = "Cena musi być wieksza od 0")
    private Double price;

    @PastOrPresent(message = "Data publikacji musi być w czase przyszłym")
    private LocalDate publicationDate;

    @Min(value = 0, message = "Liczba książke w magazynie musi być wieksza od 0")
    private int booksInWarehouse;

    @JsonBackReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Complaint> complaintList = new ArrayList<>();


    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_section",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private List<Section> sections = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Promotion promotion;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<OrderBook> orderBooks = new ArrayList<>();
}
