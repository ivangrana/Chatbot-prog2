package com.chatbot.consulta.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://0.0.0.0:8081/")
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @PostMapping
    public ResponseEntity createUser(){

    }
}
