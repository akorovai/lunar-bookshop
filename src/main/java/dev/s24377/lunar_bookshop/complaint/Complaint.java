package dev.s24377.lunar_bookshop.complaint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.client.Client;
import dev.s24377.lunar_bookshop.enums.COMPLAINT_STATUS;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String problemDescription;

    @NotNull
    private LocalDate submittedDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private COMPLAINT_STATUS status;
    
    private String decision;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Client client;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Book book;
}
