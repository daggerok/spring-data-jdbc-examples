package com.example.springdatajdbcmanyref;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
class User {

    @Id
    private final Long id;
    private final String name;
}

interface UserRepository extends CrudRepository<User, Long> {}

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
class Message {

    @Id
    private final Long id;
    private final String body;
    private final UserRef from, to;
}

@Table("user_ref")
@Value(staticConstructor = "of")
class UserRef {
    private final Long user;

    public static UserRef of(User user) {
        Objects.requireNonNull(user, "user may not be null.");
        Objects.requireNonNull(user.getId(), "user ID may not be null.");
        return UserRef.of(user.getId());
    }
}

interface MessageRepository extends CrudRepository<Message, Long> {}

@Log4j2
@Configuration
public class MessagingApp {
    /* stub */
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository,
                                        MessageRepository messageRepository) {
        return args -> {
            Iterable<User> users = userRepository.saveAll(
                    Stream.of("lola", "bola")
                          .map(name -> new User(null, name))
                          .collect(Collectors.toList()));
            log.info("saved: {}", users);

            Iterator<User> iterator = userRepository.findAll()
                                                    .iterator();
            User from = iterator.next();
            User to = iterator.next();

            Iterable<Message> messages = messageRepository.saveAll(
                    Stream.of("ololo", "trololo")
                          .map(body -> new Message(null, body, UserRef.of(from), UserRef.of(to)))
                          .collect(Collectors.toList()));
            log.info(messages);
        };
    }
}
