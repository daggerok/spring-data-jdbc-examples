package com.github.daggerok.twitterok.web;

import com.github.daggerok.twitterok.data.Author;
import com.github.daggerok.twitterok.data.AuthorRepository;
import com.github.daggerok.twitterok.web.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class IndexAdvice {

    private final AuthorRepository authorRepository;

    @ModelAttribute("users")
    public Iterable<Author> users() {
        return authorRepository.findAll();
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
