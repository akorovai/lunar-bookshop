package dev.s24377.lunar_bookshop.complaint;


import dev.s24377.lunar_bookshop.enums.COMPLAINT_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO {

    private Long id;
    private String problemDescription;
    private LocalDate submittedDate;
    private COMPLAINT_STATUS status;
    private String decision;
    private Long clientId;
    private Long bookIsbn;

}
