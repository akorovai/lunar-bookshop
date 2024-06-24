package dev.s24377.lunar_bookshop.complaint;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewComplaintDTO {
    @NotEmpty
    private String description;
    @NotNull
    private Long isbn;
    @NotNull
    private Long clientId;
}
