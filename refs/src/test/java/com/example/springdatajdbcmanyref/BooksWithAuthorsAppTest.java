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
class BooksWithAuthorsAppTest {

    AuthorOfBookRepository authorOfBookRepository;
    BookWithAuthorRepository bookWithAuthorRepository;

    @Test
    void test_authors() {
        Iterable<AuthorOfBook> authors = authorOfBookRepository.saveAll(
                Stream.of("alala", "tralala")
                      .map(name -> new AuthorOfBook(null, name))
                      .collect(Collectors.toList())
        );
        log.info("created authors: {}", authors);
        log.info("find all: {}", authorOfBookRepository.findAll());
    }

    @Test
    void test_books() {
        test_authors();
        Iterable<BookWithAuthors> books = bookWithAuthorRepository.saveAll(
                Stream.of("ololo", "trololo")
                      .map(title -> new BookWithAuthors(null, title))
                      .map(bookWithAuthors -> bookWithAuthors.addAll(authorOfBookRepository.findAll()))
                      .collect(Collectors.toList())
        );
        log.info("created books: {}", books);
        log.info("find all: {}", bookWithAuthorRepository.findAll());
    }
}
