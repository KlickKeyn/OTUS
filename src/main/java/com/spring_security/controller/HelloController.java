package com.spring_security.controller;

import com.spring_security.service.SimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final SimpleService simpleService;

    @GetMapping("/")
    public String commonRequest() {
        return "common request";
    }

    @GetMapping("/authenticated")
    public String authenticatedRequest() {
        return "authenticated request";
    }

    @GetMapping("/user")
    public String userRequest() {
        return "user request";
    }

    @GetMapping("/admin")
    public String adminRequest() {
        return "admin request";
    }

    @GetMapping("/some")
    public String someRequest() {
        return simpleService.someMessage();
    }
}
