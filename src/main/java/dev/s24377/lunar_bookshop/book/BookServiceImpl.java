package dev.s24377.lunar_bookshop.book;

import dev.s24377.lunar_bookshop.promotion.Promotion;
import dev.s24377.lunar_bookshop.promotion.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final PromotionRepository promotionRepository;

    @Override
    @Transactional
    public BookDTO addBook(BookDTO bookDTO) {
        boolean isIsbnNotUnique = bookRepository.findByIsbn(bookDTO.getIsbn()).isPresent();
        if (isIsbnNotUnique) {
            throw new IllegalStateException("Isbn not unique");
        }
        Book book = Book.builder()
                .isbn(bookDTO.getIsbn())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .price(bookDTO.getPrice())
                .publicationDate(bookDTO.getPublicationDate())
                .booksInWarehouse(bookDTO.getBooksInWarehouse())
                .build();

        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    @Transactional
    public void assignToPromotion(Long bookIsbn, Long promotionId) {

        Optional<Book> optionalBook = bookRepository.findByIsbn(bookIsbn);

        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new IllegalArgumentException("Promotion not found"));
        optionalBook.ifPresent(value -> value.setPromotion(promotion));
        optionalBook.ifPresent(bookRepository::save);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList();
    }


}
