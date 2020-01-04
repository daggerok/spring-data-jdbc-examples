package com.example.springdatajdbcmanyref;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@DataJdbcTest
@AllArgsConstructor(onConstructor_ = @Autowired)
class BooksAndAuthorsAppTest {

    BookRepository bookRepository;
    AuthorRepository authorRepository;

    @Test
    void test_authors() {
        Iterable<Author> authors = authorRepository.saveAll(
                Stream.of("alala", "tralala")
                      .map(name -> new Author(null, name))
                      .collect(Collectors.toList())
        );
        log.info("created authors: {}", authors);
        log.info("find all: {}", authorRepository.findAll());
    }

    @Test
    void test_books() {
        Iterable<Book> books = bookRepository.saveAll(
                Stream.of("ololo", "trololo")
                      .map(title -> new Book(null, title))
                      .collect(Collectors.toList())
        );
        log.info("created books: {}", books);
        log.info("find all: {}", bookRepository.findAll());
    }
}
