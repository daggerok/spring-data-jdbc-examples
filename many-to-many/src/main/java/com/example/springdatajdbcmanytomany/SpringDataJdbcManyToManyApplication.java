package com.example.springdatajdbcmanytomany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@With
@Value
@Table("message_author")
@RequiredArgsConstructor
class AuthorRef {
    // id of author that the message is referencing on...
    // should be same as in SQL message_author definition...
    private final UUID author;
}

/**
 * {@link Author} will be part of many {@link Message} objects.
 * see {@link AuthorRef} as a many(authors)-to-many(messages)
 * relationship implementation.
 */
@With
@Value
@EqualsAndHashCode(callSuper = false)
class Author extends AbstractAggregateRoot<Author> implements Persistable<UUID> {

    final @Id UUID id;
    final String name, email;
    final LocalDateTime lastModifiedDateTime;

    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        Optional<UUID> maybeId = Optional.ofNullable(this.id);
        return !maybeId.isPresent() || maybeId.map(UUID::toString)
                                              .filter(id -> id.startsWith("00000000-0000-0000-0000-00000000000"))
                                              .isPresent();
    }
}

interface AuthorRepository extends CrudRepository<Author, UUID> {
    @Query(" SELECT * FROM author ORDER BY last_modified_date_time DESC ; ")
    Iterable<Author> findAllByOrderByLastModifiedDateTimeDesc();
}

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
class AuthorResource {

    private final AuthorRepository authorRepository;

    @GetMapping("/{id}")
    ResponseEntity<Author> findById(@PathVariable UUID id) {
        return authorRepository.findById(id)
                               .map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound()
                                                     .build());
    }

    @GetMapping
    Iterable<Author> findAll() {
        return authorRepository.findAllByOrderByLastModifiedDateTimeDesc();
    }

    @PostMapping
    @Transactional
    ResponseEntity<Author> save(@RequestBody Author author) {
        Author entity = authorRepository.save(author);
        UUID id = Objects.requireNonNull(entity.getId());
        return authorRepository.findById(id)
                               .map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound()
                                                     .build());
    }
}

/********/

@Data
@With
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
class Message extends AbstractAggregateRoot<Message> implements Persistable<UUID> {

    @Id UUID id;
    UUID aggregateId;
    String body;
    LocalDateTime lastModifiedDateTime;
    Set<AuthorRef> authors/* = new HashSet<>()*/;

    public Message add(Author... authors) {
        if (Objects.isNull(this.authors)) this.authors = new HashSet<>();
        this.authors.addAll(Arrays.stream(authors)
                                  .map(Author::getId)
                                  .map(AuthorRef::new).collect(Collectors.toList()));
        return this;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        Optional<UUID> maybeId = Optional.ofNullable(this.id);
        return !maybeId.isPresent() || maybeId.map(UUID::toString)
                                              .filter(id -> id.startsWith("00000000-0000-0000-0000-00000000000"))
                                              .isPresent();
    }
}

interface MessagesRepository extends CrudRepository<Message, UUID> {
    @Query(" SELECT * FROM message ORDER BY last_modified_date_time DESC ; ")
    Collection<Message> findAllByOrderByLastModifiedDateTimeDesc();
}

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
class MessageResource {

    private final MessagesRepository messagesRepository;

    @GetMapping
    Iterable<Message> findAll() {
        return messagesRepository.findAllByOrderByLastModifiedDateTimeDesc();
    }

    @GetMapping("{id}")
    ResponseEntity<Message> findById(@PathVariable UUID id) {
        Optional<Message> result = messagesRepository.findById(id);
        return result.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound()
                                           .build());
    }

    @PostMapping
    ResponseEntity<Message> save(@RequestBody Message message) {
        Message entity = messagesRepository.save(message);
        UUID id = Objects.requireNonNull(entity.getId());
        return messagesRepository.findById(id)
                                 .map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound()
                                                       .build());
    }
}

/********/

@SpringBootApplication
public class SpringDataJdbcManyToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJdbcManyToManyApplication.class, args);
    }
}
