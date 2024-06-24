package dev.s24377.lunar_bookshop.purchase;

import dev.s24377.lunar_bookshop.client.ClientService;
import dev.s24377.lunar_bookshop.client.regular.RegularClient;
import dev.s24377.lunar_bookshop.promotion.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PurchaseController {

    private final PurchaseService purchaseService;


    @GetMapping("/{purchaseId}/amount")
    public Double calculatePurchaseAmount(@PathVariable Long purchaseId) {
        var amount = purchaseService.calculateAmount(purchaseId);
        var client = purchaseService.getPurchase(purchaseId).get().getClient();
        if (client.getClass().equals(RegularClient.class)) {
            amount *= 1 - ((RegularClient) client).getDiscount();
        }


        return amount;
    }
}
