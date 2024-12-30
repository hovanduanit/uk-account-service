package com.uk.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HelloController {
    @GetMapping("/")
    public String getAPI() {
        return "Hello Spring";
    }
}
