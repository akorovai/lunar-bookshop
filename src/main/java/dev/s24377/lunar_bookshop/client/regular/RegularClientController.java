package dev.s24377.lunar_bookshop.client.regular;

import dev.s24377.lunar_bookshop.client.ClientDTO;
import dev.s24377.lunar_bookshop.client.NewClientDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/regular-client")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegularClientController {
    private final RegularClientSerivce regularClientSerivce;

    @PostMapping
    public ResponseEntity<RegularClientDTO> registerClient(@Valid @RequestBody NewClientDTO newClientDTO) {
        var client = regularClientSerivce.registerClient(newClientDTO.getName(), newClientDTO.getSurname(), newClientDTO.getGender());
        return ResponseEntity.ok().body(client);
    }
}
