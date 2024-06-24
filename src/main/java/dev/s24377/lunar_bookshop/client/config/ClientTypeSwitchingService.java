package dev.s24377.lunar_bookshop.client.config;

import dev.s24377.lunar_bookshop.client.Client;
import dev.s24377.lunar_bookshop.client.ClientRepository;
import dev.s24377.lunar_bookshop.client.regular.RegularClient;
import dev.s24377.lunar_bookshop.client.regular.RegularClientRepository;
import dev.s24377.lunar_bookshop.client.vip.VIPClient;
import dev.s24377.lunar_bookshop.client.vip.VIPClientRepository;
import dev.s24377.lunar_bookshop.purchase.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClientTypeSwitchingService {


    private final ClientRepository clientRepository;
    private final RegularClientRepository regularClientRepository;
    private final VIPClientRepository vipClientRepository;

    @Transactional
    public void checkAndSwitchClientTypes() {
        switchVipToRegularIfNeeded();
        switchRegularToVipIfNeeded();
    }

    private void switchVipToRegularIfNeeded() {
        LocalDate oneWeekAgo = LocalDate.now().minusWeeks(1);

        vipClientRepository.findAll().stream()
                .filter(client -> client.getPurchases().stream()
                        .map(Purchase::getPurchaseDate)
                        .min(LocalDate::compareTo)
                        .orElse(LocalDate.MIN)
                        .isBefore(oneWeekAgo))
                .forEach(this::convertToRegularClient);
    }

    private void switchRegularToVipIfNeeded() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        regularClientRepository.findAll().stream()
                .filter(regularClient -> regularClient.getPurchases().stream()
                        .filter(purchase -> purchase.getPurchaseDate().isAfter(oneMonthAgo))
                        .count() >= 15)
                .forEach(this::convertToVipClient);

    }

    private void convertToRegularClient(VIPClient vipClient) {
        Client newRegularClient = new RegularClient(vipClient, clientRepository);
        clientRepository.save(newRegularClient);
    }

    private void convertToVipClient(RegularClient regularClient) {
        Client newVipClient = new VIPClient(regularClient, false, clientRepository);
        clientRepository.save(newVipClient);
    }
}
