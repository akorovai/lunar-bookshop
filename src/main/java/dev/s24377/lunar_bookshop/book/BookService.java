package dev.s24377.lunar_bookshop.book;

import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO);
    void assignToPromotion(Long bookIsbn, Long promotionId);
    List<BookDTO> getAllBooks();
}
