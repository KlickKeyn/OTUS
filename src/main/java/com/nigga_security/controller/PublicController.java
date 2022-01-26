package com.nigga_security.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("")
public class PublicController {

    @GetMapping(path = "auth-public")
    public String getAuthPublicPage() {
        return "Auth public page";
    }
}
