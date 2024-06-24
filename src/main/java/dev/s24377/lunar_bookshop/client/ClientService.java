package dev.s24377.lunar_bookshop.client;

import dev.s24377.lunar_bookshop.enums.GENDER;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients();
    double calculateDiscount(Long regularClientId);
}
