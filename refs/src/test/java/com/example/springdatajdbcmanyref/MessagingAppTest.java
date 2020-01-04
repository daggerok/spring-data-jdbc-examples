package com.example.springdatajdbcmanyref;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@DataJdbcTest
@AllArgsConstructor(onConstructor_ = @Autowired)
class MessagingAppTest {

    private UserRepository userRepository;
    private MessageRepository messageRepository;

    @Test
    void test() {
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
    }
}
