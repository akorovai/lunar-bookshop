package dev.s24377.lunar_bookshop.section;

import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.book.BookDTO;
import dev.s24377.lunar_bookshop.enums.SECTION_TYPE;

import java.util.List;

public interface SectionService {



    Section createSection(String name, double area, SECTION_TYPE type);

    List<Section> divideSection(Integer sectionId, int numberOfSubsections);

    List<BookDTO> addBookToSection(Long isbn, Integer sectionId);
}
