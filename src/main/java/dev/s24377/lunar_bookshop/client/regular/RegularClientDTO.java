package dev.s24377.lunar_bookshop.client.regular;


import dev.s24377.lunar_bookshop.client.ClientDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegularClientDTO extends ClientDTO {
    private double discount;

}

