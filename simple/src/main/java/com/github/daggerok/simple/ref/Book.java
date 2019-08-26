package com.github.daggerok.simple.ref;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@AllArgsConstructor
public class Book {

  @Id
  private Long id;
  private String title;
  private Set<AuthorRef> authors = new HashSet<>();

  public Book withAuthor(Author author) {
    AuthorRef ref = createAuthorRef(author);
    if (Objects.isNull(authors)) authors = new HashSet<>();
    authors.add(ref);
    return this;
  }

  static private AuthorRef createAuthorRef(Author author) {
    Objects.requireNonNull(author, "author is required.");
    Long id = Objects.requireNonNull(author.id, "author id is required.");
    AuthorRef ref = new AuthorRef();
    ref.author = id;
    return ref;
  }

  public static Book empty() {
    return new Book(null, null, new HashSet<>());
  }

  public static Book create(String title) {
    return new Book(null, title, new HashSet<>());
  }

  public static Book create(String title, Author author) {
    return create(title).withAuthor(author);
  }
}
