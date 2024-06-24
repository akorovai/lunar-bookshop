package dev.s24377.lunar_bookshop.client;


import dev.s24377.lunar_bookshop.client.regular.RegularClient;
import dev.s24377.lunar_bookshop.client.regular.RegularClientRepository;
import dev.s24377.lunar_bookshop.client.vip.VIPClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientServiceImpl implements ClientService {

    private final RegularClientRepository regularClientRepository;
    private final VIPClientRepository VIPClientRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ClientDTO> getAllClients() {
        return Stream.concat(regularClientRepository.findAll().stream().map(rc -> modelMapper.map(rc, ClientDTO.class)), VIPClientRepository.findAll().stream().map(oc -> modelMapper.map(oc, ClientDTO.class))).collect(Collectors.toList());
    }

    @Override
    public double calculateDiscount(Long regularClientId) {
        RegularClient client = regularClientRepository.findById(regularClientId).orElseThrow(() -> new IllegalArgumentException("Regular client not found"));
        return client.getDiscount();
    }


}
