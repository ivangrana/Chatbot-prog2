package com.chatbot.consulta.controllers;

import com.chatbot.consulta.dtos.request.autenticacao.AutenticacaoRequestDto;
import com.chatbot.consulta.dtos.request.autenticacao.MedicoCreate;
import com.chatbot.consulta.dtos.request.pagamento.CreateCredito;
import com.chatbot.consulta.dtos.request.pagamento.PagamentoRequestDto;
import com.chatbot.consulta.models.Cartao;
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
        //TODO - cadastrar tipo credito e retorna mensagem de sucesso
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
    @PutMapping("/pagar/debito")
    public ResponseEntity pagarTipoDebito(@Validated(MedicoCreate.class) @RequestBody AutenticacaoRequestDto authRequestDto,
                                              @RequestHeader("Authorization") String tokenHeader){
        //TODO - verificar se cartao existe
        //TODO - verificar se solicitacao existe
        //TODO - alterar status da solicitacao
        //TODO - retorna mensagem de sucesso
        return null;
    }
    @PutMapping("/pagar/credito")
    public ResponseEntity pagarTipoCredito(@Validated(MedicoCreate.class) @RequestBody AutenticacaoRequestDto authRequestDto,
                                          @RequestHeader("Authorization") String tokenHeader){
        //TODO - verificar se cartao existe
        //TODO - verificar se solicitacao existe
        //TODO - alterar status da solicitacao
        //TODO - retorna mensagem de sucesso
        return null;
    }
    @DeleteMapping("/cartao")
    public ResponseEntity removerCartao(@Validated(MedicoCreate.class) @RequestBody AutenticacaoRequestDto authRequestDto,
                                           @RequestHeader("Authorization") String tokenHeader){
        //TODO - verificar se cartao existe
        //TODO - verificar tipo de cartao
        //TODO - remover cartao do usuario
        //TODO - retorna mensagem de sucesso
        return null;
    }
}
