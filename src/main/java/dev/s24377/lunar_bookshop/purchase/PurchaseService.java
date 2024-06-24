package dev.s24377.lunar_bookshop.purchase;

import java.util.Optional;

public interface PurchaseService {
    Double calculateAmount(Long purchaseId);
    Optional<Purchase> getPurchase(Long purchaseId);
}
