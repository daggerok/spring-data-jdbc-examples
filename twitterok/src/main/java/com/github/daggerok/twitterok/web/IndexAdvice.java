package com.github.daggerok.twitterok.web;

import com.github.daggerok.twitterok.data.Author;
import com.github.daggerok.twitterok.data.AuthorRepository;
import com.github.daggerok.twitterok.web.user.UserModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class IndexAdvice {

    private final AuthorRepository authorRepository;

    @ModelAttribute("users")
    public Iterable<Author> users() {
        try (Stream<Author> stream = authorRepository.findBy()) {
            return stream.collect(Collectors.toList());
        }
    }

    @ModelAttribute("errors")
    public List<ObjectError> errors() {
        return new ArrayList<>();
    }

    @ModelAttribute("user")
    public UserModel user() {
        return new UserModel();
    }
}
