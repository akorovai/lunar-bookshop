package dev.s24377.lunar_bookshop.section;

import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.book.BookDTO;
import dev.s24377.lunar_bookshop.book.BookRepository;
import dev.s24377.lunar_bookshop.enums.SECTION_TYPE;
import dev.s24377.lunar_bookshop.section.config.SectionConfig;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final BookRepository bookRepository;
    private final SectionConfig sectionConfig;
    private final ModelMapper modelMapper;

    @Override
    public Section createSection(String name, double area, SECTION_TYPE type) {
        boolean isSectionNameExists = sectionRepository.findByName(name).isPresent();
        if (isSectionNameExists) {
            throw new IllegalStateException("Section with name '" + name + "' already exists");
        }
        Section section = Section.builder().name(name).area(area).type(type).subsections(List.of()).books(List.of()).manager(null).build();

        return sectionRepository.save(section);
    }

    @Override
    @Transactional
    public List<Section> divideSection(Integer sectionId, int numberOfSubsections) {
        Section parentSection = sectionRepository.findById(sectionId).orElseThrow(() -> new IllegalArgumentException("Section not found"));

        if (numberOfSubsections > sectionConfig.getDefaultNumberOfSubsections()) {
            throw new IllegalStateException("Exceeds maximum number of subsections");
        }

        double newArea = parentSection.getArea() / (numberOfSubsections + 1);

        List<Section> newSubsections = IntStream.range(1, numberOfSubsections + 1).mapToObj(i -> Section.builder().name(parentSection.getName() + " Subsection " + i).area(newArea).parentSection(parentSection).type(parentSection.getType())

                .subsections(List.of()).books(List.of()).manager(null).build()).collect(Collectors.toList());

        parentSection.getSubsections().addAll(newSubsections);


        sectionRepository.save(parentSection);
        sectionRepository.saveAll(newSubsections);

        return newSubsections;
    }

    @Override
    public List<BookDTO> addBookToSection(Long isbn, Integer sectionId) {
        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new IllegalArgumentException("Section not found"));

        Book book = bookRepository.findById(isbn).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        book.getSections().add(section);
        section.getBooks().add(book);


        bookRepository.save(book);
        var a = sectionRepository.findById(sectionId);
        var b = a.get().getBooks();
        var c = b.stream()
                .map(bo -> modelMapper.map(bo, BookDTO.class))
                .toList();
        return c;
    }

}
