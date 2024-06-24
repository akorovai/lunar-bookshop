package dev.s24377.lunar_bookshop.promotion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.enums.PROMOTION_TYPE;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "promotion")
@EntityListeners(PromotionListener.class)
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotNull
    private String title;
    @NotEmpty
    @NotNull
    private String description;

    private LocalDate startDate;

    @Min(value = 1, message = "Okres promocji musi być co najmniej 1 dzień")
    private int period;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PROMOTION_TYPE type;

    @JsonBackReference
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();


    @Transient
    private LocalDate completionDate;

    public LocalDate getCompletionDate() {
        return startDate.plusDays(period);
    }

}
