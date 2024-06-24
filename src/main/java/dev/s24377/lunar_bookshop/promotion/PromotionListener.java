package dev.s24377.lunar_bookshop.promotion;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostUpdate;

import java.time.LocalDate;
import java.util.ArrayList;

public class PromotionListener {

    @PostLoad
    @PostUpdate
    public void updateBookPromotionStatus(Promotion promotion) {
        LocalDate currentDate = LocalDate.now();
        if (promotion.getCompletionDate().isBefore(currentDate)) {
            promotion.getBooks().forEach(book -> book.setPromotion(null));
            promotion.setBooks(new ArrayList<>());
        }
    }
}
