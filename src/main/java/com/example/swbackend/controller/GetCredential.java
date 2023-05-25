package com.example.swbackend.controller;

import com.example.swbackend.config.SecurityConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/test")
public class GetCredential {
    @GetMapping()
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok().body(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
