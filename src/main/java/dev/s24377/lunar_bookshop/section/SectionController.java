package dev.s24377.lunar_bookshop.section;

import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.book.BookDTO;
import dev.s24377.lunar_bookshop.enums.SECTION_TYPE;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SectionController {


    private final SectionService sectionService;

    @PostMapping("/create")
    public Section createSection(@RequestParam String name, @RequestParam double area, @RequestParam SECTION_TYPE type) {
        return sectionService.createSection(name, area, type);
    }

    @PostMapping("/divide/{sectionId}")
    public List<Section> divideSection(@PathVariable Integer sectionId, @RequestParam int numberOfSubsections) {
        return sectionService.divideSection(sectionId, numberOfSubsections);
    }

    @PostMapping("/addBook")
    public List<BookDTO> addBookToSection(@RequestParam Long isbn, @RequestParam Integer sectionId) {
        return sectionService.addBookToSection(isbn, sectionId);
    }
}
