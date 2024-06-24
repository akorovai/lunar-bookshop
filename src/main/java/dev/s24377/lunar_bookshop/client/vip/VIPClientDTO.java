package dev.s24377.lunar_bookshop.client.vip;

import dev.s24377.lunar_bookshop.client.ClientDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VIPClientDTO extends ClientDTO {
    private Long loyaltyCardNumber;
}
