package com.chatbot.consulta.controllers;

import com.chatbot.consulta.dtos.request.Auth.AuthRequestDto;
import com.chatbot.consulta.dtos.request.Auth.MedicoCreate;
import com.chatbot.consulta.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://0.0.0.0:8081/")
@RestController
@RequestMapping("pagamento")
public class PagamentoController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/tipo/credito")
    public ResponseEntity cadastrarTipoCredito(@Validated(MedicoCreate.class) @RequestBody AuthRequestDto authRequestDto,
                                                 @RequestHeader("Authorization") String tokenHeader){
        //TODO - autenticaar numero de cartao
        //TODO - cadastrar tipo credito
        //TODO - pegar dados de usuario e salvar novo tipo de pagamento por usuario
        //TODO - retorna mensagem de sucesso
        return null;
    }
    @PostMapping("/tipo/credito")
    public ResponseEntity cadastrarTipoDebito(@Validated(MedicoCreate.class) @RequestBody AuthRequestDto authRequestDto,
                                                 @RequestHeader("Authorization") String tokenHeader){
        //TODO - autenticaar numero de cartao
        //TODO - cadastrar tipo debito
        //TODO - pegar dados de usuario e salvar novo tipo de pagamento por usuario
        //TODO - retorna mensagem de sucesso
        return null;
    }
    @PutMapping("/pagar/credito")
    public ResponseEntity pagarTipoDebito(@Validated(MedicoCreate.class) @RequestBody AuthRequestDto authRequestDto,
                                              @RequestHeader("Authorization") String tokenHeader){
        //TODO - verificar se cartao existe
        //TODO - verificar se solicitacao existe
        //TODO - alterar status da solicitacao
        //TODO - retorna mensagem de sucesso
        return null;
    }
    @PutMapping("/pagar/credito")
    public ResponseEntity pagarTipoCredito(@Validated(MedicoCreate.class) @RequestBody AuthRequestDto authRequestDto,
                                          @RequestHeader("Authorization") String tokenHeader){
        //TODO - verificar se cartao existe
        //TODO - verificar se solicitacao existe
        //TODO - alterar status da solicitacao
        //TODO - retorna mensagem de sucesso
        return null;
    }
    @PutMapping("/pagar/credito")
    public ResponseEntity removerCartao(@Validated(MedicoCreate.class) @RequestBody AuthRequestDto authRequestDto,
                                           @RequestHeader("Authorization") String tokenHeader){
        //TODO - verificar se cartao existe
        //TODO - verificar tipo de cartao
        //TODO - remover cartao do usuario
        //TODO - retorna mensagem de sucesso
        return null;
    }
}
