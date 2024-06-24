package dev.s24377.lunar_bookshop.promotion;

import dev.s24377.lunar_bookshop.enums.PROMOTION_TYPE;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionDTO {
    private Long id;
    private String title;
    private String description;
    private PROMOTION_TYPE type;
    private LocalDate startDate;
    private int period;
}
