
package dev.s24377.lunar_bookshop.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private Long isbn;
    private String title;
    private String author;
    @Min(value = 0, message = "Cena musi być wieksza od 0")
    private Double price;
    @PastOrPresent(message = "Data publikacji musi być w czase przyszłym")
    private LocalDate publicationDate;
    @Min(value = 0, message = "Liczba książek w magazynie musi być wieksza od 0")
    private int booksInWarehouse;
}
