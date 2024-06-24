package dev.s24377.lunar_bookshop.complaint;


public interface ComplaintService {


    ComplaintDTO createComplaint(NewComplaintDTO newComplaint);


    ComplaintDTO processComplaint(Integer complaintId, String decision);
}
