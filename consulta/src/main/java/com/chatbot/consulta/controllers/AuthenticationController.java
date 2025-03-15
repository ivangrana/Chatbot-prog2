package com.chatbot.consulta.controllers;

import com.chatbot.consulta.dtos.request.Auth.UserCreate;
import com.chatbot.consulta.dtos.request.Auth.AuthRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://0.0.0.0:8081/")
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @PostMapping("/medico")
    public ResponseEntity createUser(@Validated(UserCreate.class) @RequestBody AuthRequestDto authRequestDto){
        return null;
    }
    @PostMapping("/medico")
    public ResponseEntity createUser(@Validated(UserCreate.class) @RequestBody AuthRequestDto authRequestDto){
        return null;
    }
}
