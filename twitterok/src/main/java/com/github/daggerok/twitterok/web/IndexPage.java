package com.github.daggerok.twitterok.web;

import com.github.daggerok.twitterok.web.user.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexPage {

    @GetMapping({ "/404" })
    String fallback() {
        return "redirect:/"; // return "forward:/";
    }

    @GetMapping("/")
    String index() {
        return "index";
    }
}
