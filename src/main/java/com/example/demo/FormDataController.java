package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormDataController {

    @PostMapping("/comments")
    public String TestCreateComment(@RequestParam String author, @RequestParam String content) {
        return String.format("%s said %s!",author,content);
    }
}
