package com.github.daggerok.simple.onetoone;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class OneToOneRunner implements ApplicationRunner {

  final OneToOneRepository oneToOneRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("save entities...");
    OneToOneAggregateRoot saved = oneToOneRepository.save(new OneToOneAggregateRoot());
    log.info("saved: {}", saved);
    Long id = saved.id;
    oneToOneRepository.findById(id).ifPresent(oneToOneAggregateRoot -> {
      OneToOneAggregateRoot ololo = oneToOneRepository.save(oneToOneAggregateRoot.withName("ololo"));
      log.info("updated 1: {}", ololo);
      log.info("updated 2: {}", () -> oneToOneRepository.save(ololo.withName("trololo")));
    });
    log.info("entities saved.");
    oneToOneRepository.findAll()
                      .forEach(log::info);
  }
}
