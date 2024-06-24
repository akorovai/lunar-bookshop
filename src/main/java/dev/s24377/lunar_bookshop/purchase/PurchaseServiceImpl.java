package dev.s24377.lunar_bookshop.purchase;

import dev.s24377.lunar_bookshop.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;


    @Override
    @Transactional(readOnly = true)
    public Double calculateAmount(Long purchaseId) {

        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase not found with id: " + purchaseId))
                .getBooks()
                .stream()
                .mapToDouble(b -> {
                    if(Objects.isNull(b.getPromotion())){
                        return b.getPrice() ;
                    }
                    return b.getPrice() * b.getPromotion().getType().getDiscount();
                }).sum();
    }

    @Override
    public Optional<Purchase> getPurchase(Long purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }


}
