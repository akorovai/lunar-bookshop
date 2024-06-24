package dev.s24377.lunar_bookshop.promotion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    boolean existsByBooksIsbn(Long isbn);

    Promotion getByTitle(String summerSale);


    Optional<Promotion> findByTitle(String summerSale);
}
