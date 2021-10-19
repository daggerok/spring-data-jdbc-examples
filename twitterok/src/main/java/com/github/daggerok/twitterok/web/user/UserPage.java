package com.github.daggerok.twitterok.web.user;

import com.github.daggerok.twitterok.data.Author;
import com.github.daggerok.twitterok.data.AuthorRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Log4j2
@Controller
@RequiredArgsConstructor
public class UserPage {

    private final AuthorRepository repository;

    @PostMapping("/user/create")
    String index(@ModelAttribute("user") @Valid UserModel user,
                 BeanPropertyBindingResult bindingResult,
                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors())
                 .addAttribute("user", user);
            return "index";
        }
        log.info("{}: {}", user, bindingResult);
        Author author = new Author(null, user.getName(), user.getUsername(), user.getEmail(), Instant.now());
        //Author author = new Author(null, user.getName(), user.getUsername(), user.getEmail());
        Author savedAuthor = repository.save(author);
        log.info("saved author: {}", savedAuthor);
        // return "forward:/";
        return "redirect:/";
    }
}
