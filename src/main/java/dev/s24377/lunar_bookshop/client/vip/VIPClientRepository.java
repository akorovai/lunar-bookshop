package dev.s24377.lunar_bookshop.client.vip;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VIPClientRepository extends JpaRepository<VIPClient, Long> {


    boolean existsByLoyaltyCardNumber(long cardNumber);
}
