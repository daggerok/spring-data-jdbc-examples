package com.github.daggerok.twitterok.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
