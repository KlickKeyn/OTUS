package com.spring_security.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SimpleServiceImpl implements SimpleService {

    @Override
    @PreAuthorize("hasRole('ADMIN') && {new java.util.Random.nextInt() % 2 == 0}")
//    @Secured("ROLE_ADMIN")
    public String someMessage() {
        return "Some message";
    }
}
