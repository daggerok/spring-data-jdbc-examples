package com.example.springdatajdbcmanyref;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.repository.CrudRepository;

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
class Book {

    @Id
    private final Long id;
    private final String title;
}

interface BookRepository extends CrudRepository<Book, Long> { }

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
class Author {

    @Id
    private final Long id;
    private final String name;
}

interface AuthorRepository extends CrudRepository<Author, Long> { }

@Configuration
public class BooksAndAuthorsApp { /* stub */ }
