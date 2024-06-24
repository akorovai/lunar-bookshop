package dev.s24377.lunar_bookshop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PROMOTION_TYPE {
    ONE_TIME("One Time Promotion", 0.1),
    SEASONAL("Seasonal Promotion", 0.2);

    private final String name;
    private final double discount;


}
