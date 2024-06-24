package dev.s24377.lunar_bookshop.promotion;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping("/discount/{promotionId}")
    public ResponseEntity<Double> calculateDiscount(@Valid @PathVariable Long promotionId) {
        double discount = promotionService.calculateDiscount(promotionId);
        return ResponseEntity.ok(discount);
    }

    @GetMapping("/completionDate/{promotionId}")
    public ResponseEntity<LocalDate> calculateCompletionDate(@Valid @PathVariable Long promotionId) {
        LocalDate completionDate = promotionService.calculateCompletionDate(promotionId);
        return ResponseEntity.ok(completionDate);
    }

    @PostMapping("/create")
    public ResponseEntity<PromotionDTO> createPromotion(@Valid @RequestBody NewPromotioDTO promotionDTO) {
        PromotionDTO createdPromotion = promotionService.createPromotion(promotionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPromotion);
    }

    @GetMapping("/availability/{isbn}")
    public ResponseEntity<Boolean> checkAvailability(@Valid @PathVariable Long isbn) {
        boolean isAvailable = promotionService.checkBookExistsInPromotions(isbn);
        return ResponseEntity.ok(isAvailable);
    }
}
