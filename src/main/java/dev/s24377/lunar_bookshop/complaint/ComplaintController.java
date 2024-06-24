package dev.s24377.lunar_bookshop.complaint;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ComplaintDTO createComplaint(@RequestBody NewComplaintDTO complaintDTO) {
        return complaintService.createComplaint(complaintDTO);
    }

    @PutMapping("/{complaintId}/process")
    public ComplaintDTO processComplaint(@PathVariable Integer complaintId, @RequestBody String decision) {
        return complaintService.processComplaint(complaintId, decision);
    }
}
