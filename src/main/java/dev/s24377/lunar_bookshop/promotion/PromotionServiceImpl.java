package dev.s24377.lunar_bookshop.promotion;

import dev.s24377.lunar_bookshop.book.Book;
import dev.s24377.lunar_bookshop.book.BookDTO;
import dev.s24377.lunar_bookshop.enums.PROMOTION_TYPE;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ModelMapper modelMapper;

    @Override
    public double calculateDiscount(Long promotionId) {
        return PROMOTION_TYPE.valueOf(promotionRepository.findById(promotionId).orElseThrow(() -> new IllegalArgumentException("Promotion not found")).getType().name()).getDiscount();
    }


    @Override
    public LocalDate calculateCompletionDate(Long promotionId) {
        return promotionRepository.findById(promotionId).orElseThrow(() -> new IllegalArgumentException("Promotion not found")).getCompletionDate();
    }

    @Override
    @Transactional
    public PromotionDTO createPromotion(NewPromotioDTO promotionDTO) {
        Promotion promotion = Promotion.builder().title(promotionDTO.getTitle()).description(promotionDTO.getDescription()).type(promotionDTO.getType()).startDate(promotionDTO.getStartDate()).period(promotionDTO.getPeriod()).build();

        Promotion savedPromotion = promotionRepository.save(promotion);
        return modelMapper.map(savedPromotion, PromotionDTO.class);

    }

    @Override
    public boolean checkBookExistsInPromotions(Long isbn) {

        return promotionRepository.existsByBooksIsbn(isbn);
    }

    @Override
    public Optional<List<PromotionDTO>> getActivePromotions() {
        return Optional.of(promotionRepository.findAll()
                .stream()
                .filter(p -> p.getCompletionDate().isAfter(LocalDate.now()))
                .map(p -> modelMapper.map(p, PromotionDTO.class))
                .collect(Collectors.toList()));
    }



    @Override
    public List<BookDTO> getPromotionsBookList() {

        return promotionRepository.findAll().stream()
                .flatMap(promotion -> promotion.getBooks().stream())
                .map(b -> modelMapper.map(b, BookDTO.class))
                .toList();
    }

    @Override
    public List<BookDTO> getPromotionBookList(Long promotionId) {

       return promotionRepository.findById(promotionId).orElseThrow(() -> new IllegalArgumentException("Promotion not found")).getBooks().stream().map(book -> modelMapper.map(book, BookDTO.class)).toList();

    }


}
