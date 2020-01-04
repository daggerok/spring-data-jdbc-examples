package com.example.springdatajdbconetomany;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@DataJdbcTest
// @SpringBootTest
@AllArgsConstructor(onConstructor_ = @Autowired)
class SpringDataJdbcManyToManyApplicationTests {

    AuthorRepository authorRepository;
    MessagesRepository messagesRepository;

    @Test
    void test() {
        Author[] authors = Stream.of("Ololo", "Trololo")
                                 .map(name -> new Author(null, name,
                                                         String.format("%s@gmail.com", name.toLowerCase()), null /*LocalDateTime.now()*/))
                                 .map(authorRepository::save)
                                 // .collect(Collectors.toList())
                                 .toArray(Author[]::new);
        Message message = messagesRepository.save(new Message(null, null, "hello!", null /*LocalDateTime.now()*/, null)
                                                          .withAuthor(authors));
        assertThat(message.getId()).isNotNull();

        Optional<Message> maybeMessage = messagesRepository.findById(message.getId());
        assertThat(maybeMessage).isPresent();

        maybeMessage.ifPresent(msg -> {
            log.info(msg);
            assertThat(msg.getAuthors()).containsExactlyInAnyOrder(
                    new AuthorRef(authors[0].getId()),
                    new AuthorRef(authors[1].getId())
            );
        });
    }
}
