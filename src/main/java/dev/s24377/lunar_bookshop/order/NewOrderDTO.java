package dev.s24377.lunar_bookshop.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewOrderDTO {

    @NotNull
    private Long supplierId;
    @NotEmpty
    private Map<Long, Integer> booksMap = new HashMap<>();
}
