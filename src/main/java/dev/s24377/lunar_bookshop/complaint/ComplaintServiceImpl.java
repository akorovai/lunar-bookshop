package dev.s24377.lunar_bookshop.complaint;


import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.book.BookRepository;
import dev.s24377.lunar_bookshop.client.Client;
import dev.s24377.lunar_bookshop.client.ClientRepository;
import dev.s24377.lunar_bookshop.enums.COMPLAINT_STATUS;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComplaintServiceImpl implements ComplaintService {

    private final ClientRepository clientRepository;
    private final ComplaintRepository complaintRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    @Override
    @Transactional
    public ComplaintDTO createComplaint(NewComplaintDTO complaintDTO) {
        Client client = clientRepository.findById(complaintDTO.getClientId()).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        Book book = bookRepository.findByIsbn(complaintDTO.getIsbn()).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Complaint complaint = Complaint
                .builder()
                .problemDescription(complaintDTO.getDescription())
                .client(client)
                .status(COMPLAINT_STATUS.UNDER_CONSIDERATION)
                .book(book)
                .submittedDate(LocalDate.now())
                .build();



        client.getComplaints().add(complaint);
        clientRepository.save(client);

        return modelMapper.map(complaint, ComplaintDTO.class);
    }
    @Override
    @Transactional
    public ComplaintDTO processComplaint(Integer complaintId, String decision) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new IllegalArgumentException("Complaint not found"));

        complaint.setDecision(decision);
        complaint.setStatus(COMPLAINT_STATUS.POSITIVELY_ACCEPTED);

        complaintRepository.save(complaint);

        return modelMapper.map(complaint, ComplaintDTO.class);
    }

}