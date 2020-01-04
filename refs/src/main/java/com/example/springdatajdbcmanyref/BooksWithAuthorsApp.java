package com.example.springdatajdbcmanyref;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
class BookWithAuthors {

    @Id
    private final Long id;
    private final String title;
    private final Set<AuthorOfBookRef> authors;

    public BookWithAuthors(Long id, String title) {
        this(id, title, new CopyOnWriteArraySet<>());
    }

    public BookWithAuthors addAll(Iterable<AuthorOfBook> authors) {
        return addAll(StreamSupport.stream(authors.spliterator(), false)
                                   .toArray(AuthorOfBook[]::new));
    }

    public BookWithAuthors addAll(AuthorOfBook... authors) {
        this.authors.addAll(Arrays.stream(authors)
                                  .map(AuthorOfBook::getId)
                                  .map(AuthorOfBookRef::of)
                                  .collect(Collectors.toList())
        );
        return this;
    }
}

interface BookWithAuthorRepository extends CrudRepository<BookWithAuthors, Long> {
}

@With
@Value
@RequiredArgsConstructor(onConstructor_ = @PersistenceConstructor)
class AuthorOfBook {

    @Id
    private final Long id;
    private final String name;
}

interface AuthorOfBookRepository extends CrudRepository<AuthorOfBook, Long> {
}

@Value(staticConstructor = "of")
@Table("author_of_book_ref")
class AuthorOfBookRef {
    private final Long author;
}

@Configuration
public class BooksWithAuthorsApp { /* stub */
}
