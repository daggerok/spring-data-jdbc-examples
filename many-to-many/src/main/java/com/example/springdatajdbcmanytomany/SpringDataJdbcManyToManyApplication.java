package com.example.springdatajdbcmanytomany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
    // @Column("author") // otherwise default column name is: author_ref (according to field name)...
    // private final UUID authorRef;
    // // id of author that the message is referencing on..
    private final UUID author;
}

/**
 * {@link Author} will be part of many {@link Message} objects.
 * see {@link AuthorRef} as a many(authors)-to-many(messages)
 * relationship implementation.
 */
@With
@Value
// @EqualsAndHashCode(callSuper = false)
class Author /*extends AbstractAggregateRoot<Author> */ implements Persistable<UUID> {

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
        // Author entity = author.withLastModifiedDateTime(LocalDateTime.now());
        Author entity = authorRepository.save(author);
        UUID id = Objects.requireNonNull(entity.getId());
        return authorRepository.findById(id)
                               .map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound()
                                                     .build());
    }
}

/********/

// @Value
@Data
@With
@AllArgsConstructor
// @EqualsAndHashCode(callSuper = false)
class Message /*extends AbstractAggregateRoot<Message> */ implements Persistable<UUID> {

    @Id
    UUID id;
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
        // Message entity = message.withLastModifiedDateTime(LocalDateTime.now());
        // if (Objects.isNull(entity.getAggregateId())) /*entity = */ entity.withAggregateId(UUID.randomUUID());
        Message entity = messagesRepository.save(message);
        UUID id = Objects.requireNonNull(entity.getId());
        return messagesRepository.findById(id)
                                 .map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound()
                                                       .build());
    }
}

// /********/
//
// @Configuration
// class DataDrivenConfig {
//
//     @Bean
//     List<String> authors() {
//         return Arrays.asList(
//                 "Max", "Bob", "Alice", "Tracy",
//                 "Mike", "Maksim", "Olga",
//                 "Oleg", "Valery"
//         );
//     }
//
//     @Bean
//     List<String> messages() {
//         return Arrays.asList(
//                 "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cumque, dignissimos?",
//                 "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias asperiores culpa, distinctio dolor dolores facere id nesciunt. Deleniti, quidem, sed.",
//                 "Lorem ipsum dolor adipisicing elit. Cumque, dignissimos?",
//                 "Lorem ipsum dolor sit amet, iste iure laboriosam nobis quas sapiente sit tenetur?",
//                 "Lorem ipsum dolor sit voluptate. Praesentium?",
//                 "Lorem ipsum dolor sit amet, consectetur aperiam cupiditate deserunt eius error eum modi officia optio, quidem vitae.",
//                 "Lorem ipsum dolor sit amet, qui sint?",
//                 "Lorem ipsum dolor sit amet, nesciunt officia qui unde velit voluptates! Ab, adipisci, aliquid corporis debitis earum facilis harum illo in quos rem reprehenderit velit?",
//                 "Lorem ipsum dolor sit amet, consectetur adipisicing elit. A ab accusantium assumenda beatae blanditiis culpa distinctio dolorem ex expedita.",
//                 "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium commodi dolorum ducimus ea ex excepturi explicabo illo libero? Blanditiis culpa eos est inventore laboriosam nobis voluptatibus!"
//         );
//     }
//
//     @Bean
//     Function<List<String>, String> randomItem() {
//         return items -> {
//             int index = ThreadLocalRandom.current()
//                                          .nextInt(items.size());
//             return items.get(index);
//         };
//     }
//
//     @Bean
//     Supplier<String> randomBody(Function<List<String>, String> randomItem) {
//         return () -> randomItem.apply(messages());
//     }
//
//     @Bean
//     Supplier<Author> randomAuthor(Function<List<String>, String> randomItem) {
//         return () -> {
//             UUID id = UUID.randomUUID();
//             String name = randomItem.apply(authors());
//             String email = String.format("%s@gmail.com", name.toLowerCase());
//             return new Author(id, name, email, LocalDateTime.now());
//         };
//     }
//
//     @Bean
//     Supplier<Message> nextMessage(Supplier<Author> randomAuthor, Supplier<String> randomBody) {
//         return () -> {
//             UUID id = UUID.randomUUID();
//             UUID aggregateId = UUID.randomUUID();
//             String body = randomBody.get();
//             LocalDateTime lastModifiedDateTime = LocalDateTime.now();
//             return new Message(id, aggregateId, body, lastModifiedDateTime, new HashSet<>())
//                     .add(randomAuthor.get(), randomAuthor.get())
//                     .add(randomAuthor.get());
//         };
//     }
// }
//
// /********/
//
// @Configuration
// class ProcessorConfig {
//
//     private static final int maxSize = 1024;
//
//     @Bean
//     FluxProcessor<Message, Message> processor() {
//         return EmitterProcessor.create(maxSize);
//     }
//
//     @Bean
//     Flux<Message> subscription(FluxProcessor<Message, Message> processor) {
//         return processor.onBackpressureBuffer(maxSize)
//                         .share();
//     }
//
//     @Bean
//     Consumer<Message> publisher(FluxProcessor<Message, Message> processor) {
//         return processor::onNext;
//     }
// }

@SpringBootApplication
public class SpringDataJdbcManyToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJdbcManyToManyApplication.class, args);
    }
}
