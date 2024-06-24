package dev.s24377.lunar_bookshop.client.vip;

import dev.s24377.lunar_bookshop.client.ClientDTO;
import dev.s24377.lunar_bookshop.client.NewClientDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vip-client")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VIPClientController {
    private final VIPClientService service;

    @PostMapping
    public ResponseEntity<VIPClientDTO> registerClient(@Valid @RequestBody NewClientDTO newClientDTO,
                                                       @RequestParam Boolean generate) {
        var client = service.registerClient(newClientDTO.getName(), newClientDTO.getSurname(), newClientDTO.getGender(), generate);
        return ResponseEntity.ok().body(client);
    }
}
