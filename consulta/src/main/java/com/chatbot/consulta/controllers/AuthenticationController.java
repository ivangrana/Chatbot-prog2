package com.chatbot.consulta.controllers;

import com.chatbot.consulta.dtos.request.Auth.AuthRequestDto;
import com.chatbot.consulta.dtos.request.Auth.LoginRequest;
import com.chatbot.consulta.dtos.request.Auth.MedicoCreate;
import com.chatbot.consulta.dtos.request.Auth.PacienteCreate;
import com.chatbot.consulta.dtos.response.Auth.LoginResponse;
import com.chatbot.consulta.dtos.response.BaseResponseDto;
import com.chatbot.consulta.models.User;
import com.chatbot.consulta.services.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

@CrossOrigin(origins = "http://0.0.0.0:8081/")
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    //autenticadas
    @PostMapping("/paciente")
    public ResponseEntity createMedico(@Validated(MedicoCreate.class) @RequestBody AuthRequestDto authRequestDto){
        //TODO - Verificar se usuario existe
        //TODO - Criar novo usuario
        //TODO - Criar novo medico
        return null;
    }
    @PostMapping("/medico")
    public ResponseEntity createPaciente(@Validated(PacienteCreate.class) @RequestBody AuthRequestDto authRequestDto){
        //TODO - Verificar se usuario existe
        //TODO - Criar novo usuario
        //TODO - Criar novo paciente
        return null;
    }
    @PostMapping("/login")
    public ResponseEntity<BaseResponseDto> login(@Validated(LoginRequest.class) @RequestBody AuthRequestDto authRequestDto){
        // 1. Autenticação com email e senha fornecidos
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        // 2. Define a autenticação no contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(auth);
        // 5. Retorna o token gerado em um objeto de resposta
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BaseResponseDto(new LoginResponse(token)));
    }
    @DeleteMapping("/user")
    public ResponseEntity<BaseResponseDto> deleteUser(@Validated(LoginRequest.class) @RequestBody AuthRequestDto authRequestDto,
                                                      @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token
        //TODO - pegar usuario
        //TODO - verificar o tipo de usuario
        //TODO - deletar primeiro tabela de tipo de usuario e depois usuario
        return ResponseEntity.status(HttpStatus.ACCEPTED).body();
    }
    @PutMapping("/medico")
    public ResponseEntity<BaseResponseDto> createMedicoUsuarioExistente(@Validated(LoginRequest.class) @RequestBody AuthRequestDto authRequestDto,
                                                      @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token
        //TODO - pegar usuario
        //TODO - verificar o tipo de usuario
        //TODO - se nao existir o tipo de usuario, cria-lo, caso contrario, retornar mensagem de usuario ja é medico
        return ResponseEntity.status(HttpStatus.ACCEPTED).body();
    }
    @PutMapping("/paciente")
    public ResponseEntity<BaseResponseDto> createPacienteUsuarioExistente(@Validated(LoginRequest.class) @RequestBody AuthRequestDto authRequestDto,
                                                                       @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token
        //TODO - pegar usuario
        //TODO - verificar o tipo de usuario
        //TODO - se nao existir o tipo de usuario, cria-lo, caso contrario, retornar mensagem de usuario ja é paciente
        return ResponseEntity.status(HttpStatus.ACCEPTED).body();
    }
}
