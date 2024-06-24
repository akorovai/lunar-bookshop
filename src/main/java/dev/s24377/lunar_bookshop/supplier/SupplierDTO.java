package dev.s24377.lunar_bookshop.supplier;

import dev.s24377.lunar_bookshop.enums.SUPPLIER_TYPE;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDTO {

    private String companyName;
    private long contactNumber;
    private SUPPLIER_TYPE type;
    private AddressDTO address;
}
