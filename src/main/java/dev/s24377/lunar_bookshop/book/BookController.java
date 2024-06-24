package dev.s24377.lunar_bookshop.book;


import dev.s24377.lunar_bookshop.promotion.PromotionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final PromotionService promotionService;

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO addedBook = bookService.addBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
    }

    @PostMapping("/{bookIsbn}/assignPromotion/{promotionId}")
    public ResponseEntity<?> assignToPromotion(
            @PathVariable Long bookIsbn,
            @PathVariable Long promotionId
    ) {
        if (promotionService.checkBookExistsInPromotions(bookIsbn)) {
            return ResponseEntity.badRequest().build();
        }

        bookService.assignToPromotion(bookIsbn, promotionId);
        return ResponseEntity.ok().build();
    }
}
