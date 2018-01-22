package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagesController {

        @GetMapping("/hello")
        public String helloWorld() {
            return "Hello World!";
        }
}
