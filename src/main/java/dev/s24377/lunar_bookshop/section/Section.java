package dev.s24377.lunar_bookshop.section;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.employee.section_manager.SectionManager;
import dev.s24377.lunar_bookshop.enums.SECTION_TYPE;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double area;


    @Enumerated(EnumType.STRING)
    private SECTION_TYPE type;

    @OneToMany(mappedBy = "parentSection",cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    private List<Section> subsections;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_section_name")
    @JsonBackReference
    private Section parentSection;


    @JsonManagedReference
    @ManyToMany(mappedBy = "sections", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private SectionManager manager;

}