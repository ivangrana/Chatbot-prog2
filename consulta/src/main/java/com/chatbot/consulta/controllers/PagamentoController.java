package com.chatbot.consulta.controllers;

import com.chatbot.consulta.dtos.request.pagamento.*;
import com.chatbot.consulta.models.Cartao;
import com.chatbot.consulta.models.Consulta;
import com.chatbot.consulta.models.Usuario;
import com.chatbot.consulta.services.PagamentoService;
import com.chatbot.consulta.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://0.0.0.0:8081/")
@RestController
@RequestMapping("pagamento")
public class PagamentoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/cartao")
    public ResponseEntity cadastrar(@Validated(CreateCredito.class) @RequestBody PagamentoRequestDto pagamentoRequest,
                                               @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token - ok
        Usuario usuario = pagamentoService.findUsuario(tokenService.decodeToken(tokenHeader).getId());
        //TODO - cadastrar tipo credito e retorna mensagem de sucesso - ok
        Cartao cartao = new Cartao(
                usuario,
                pagamentoRequest.getNumeroCartao(),
                pagamentoRequest.getNomeTitular(),
                pagamentoRequest.getValidade(),
                pagamentoRequest.getCvv(),
                pagamentoRequest.getTipoCartao()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.criarNovoCartao(cartao));
    }
    @PutMapping("/pagar")
    public ResponseEntity realizarPagamento(@Validated(RealizarPagamento.class) @RequestBody PagamentoRequestDto pagamentoRequest,
                                          @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token - ok
        Usuario usuario = pagamentoService.findUsuario(tokenService.decodeToken(tokenHeader).getId());

        //TODO - verificar se cartao existe e se cartao é do usuario - ok
        pagamentoService.existeCartaoByUser(pagamentoRequest.getIdCartao(), usuario);

        //TODO - buscar consulta pelo id do paciente e id da consulta - ok
        Consulta consulta = pagamentoService.findConsulta(usuario.getId(), pagamentoRequest.getIdConsulta());

        //TODO - alterar status da solicitacao e retorna mensagem de sucesso - ok
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.pagarConsulta(consulta));
    }
    @DeleteMapping("/cartao")
    public ResponseEntity removerCartao(@Validated(RealizarPagamento.class) @RequestBody PagamentoRequestDto pagamentoRequest,
                                        @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token - ok
        Usuario usuario = pagamentoService.findUsuario(tokenService.decodeToken(tokenHeader).getId());

        //TODO - verificar se cartao existe e se cartao é do usuario - ok
        pagamentoService.existeCartaoByUser(pagamentoRequest.getIdCartao(), usuario);

        //TODO - remover cartao do usuario e retorna mensagem de sucesso - ok
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.removerCartao(pagamentoRequest.getIdCartao()));
    }
}
