package dev.s24377.lunar_bookshop.client.vip;


import dev.s24377.lunar_bookshop.enums.GENDER;

public interface VIPClientService {
    VIPClientDTO registerClient(String name, String surname, GENDER gender, Boolean generateLoyaltyCard);
}
