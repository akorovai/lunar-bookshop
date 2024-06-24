package dev.s24377.lunar_bookshop.use_case;

import dev.s24377.lunar_bookshop.book.BookDTO;
import dev.s24377.lunar_bookshop.book.BookService;
import dev.s24377.lunar_bookshop.promotion.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UseCaseController {
    private final BookService bookService;

    private final PromotionService promotionService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/promotions/{promotionId}/books/add")
    public String addBooksList(Model model, @PathVariable String promotionId) {


        List<BookDTO> promotionsBookList = promotionService.getPromotionsBookList();

        List<BookDTO> books = bookService.getAllBooks().stream()
                .filter(book -> promotionsBookList.stream().noneMatch(promoBook -> promoBook.getIsbn().equals(book.getIsbn())))
                .toList();
        model.addAttribute("books", books);
        model.addAttribute("promotionId", promotionId);
        return "books";
    }

    @GetMapping("/promotions/{promotionId}/books")
    public String getPromotionsBookList(Model model, @PathVariable String promotionId) {
        List<BookDTO> promotionsBookList = promotionService.getPromotionBookList(Long.parseLong(promotionId));
        model.addAttribute("books", promotionsBookList);
        model.addAttribute("promotionId", promotionId);
        return "promotion_books";
    }

    @GetMapping("/promotions")
    public String promotionsList(Model model) {
        model.addAttribute("proms", promotionService.getActivePromotions().orElseThrow(() -> new RuntimeException("No promotions found")));
        return "promotions";
    }


    @PostMapping("/assignToPromotion/{isbn}/{promotionId}")
    @ResponseBody
    public List<BookDTO> addPromotion(@PathVariable String isbn, @PathVariable String promotionId) {

        bookService.assignToPromotion(Long.parseLong(isbn), Long.parseLong(promotionId));
        return bookService.getAllBooks();
    }
}
