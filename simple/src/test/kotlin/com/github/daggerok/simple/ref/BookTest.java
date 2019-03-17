package com.github.daggerok.simple.ref;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@ExtendWith(SpringExtension.class)
class BookTest {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  AuthorRepository authorRepository;

  @Test
  void test() {
    Book empty = bookRepository.save(Book.empty());
    Book titled = bookRepository.save(Book.create("test boot"));
    System.out.println("empty book = " + empty);
    System.out.println("titled book = " + titled);

    Author author = authorRepository.save(new Author());
    System.out.println("author = " + author);
    System.out.println("authored empty book = " + bookRepository.save(empty.withAuthor(author)));
    System.out.println("authored titled book = " + bookRepository.save(titled.withAuthor(author)));

    Author oneMoreAuthor = authorRepository.save(new Author());
    empty = bookRepository.save(empty.withAuthor(oneMoreAuthor));
    System.out.println("empty book with one more genius = " + empty);
    assertThat(empty.getAuthors()).hasSize(2);
  }
}
