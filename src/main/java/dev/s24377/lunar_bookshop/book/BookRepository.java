package dev.s24377.lunar_bookshop.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String theGreatGatsby);

    Optional<Book> findByIsbn(long l);
}
