package dev.s24377.lunar_bookshop.promotion;

import dev.s24377.lunar_bookshop.book.BookDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionService {

    double calculateDiscount(Long promotionId);

    LocalDate calculateCompletionDate(Long promotionId);

    PromotionDTO createPromotion(NewPromotioDTO promotionDTO);

    boolean checkBookExistsInPromotions(Long isbn);

    Optional<List<PromotionDTO>> getActivePromotions();



    List<BookDTO> getPromotionsBookList();

    List<BookDTO> getPromotionBookList(Long promotionId);
}
