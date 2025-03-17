package com.chatbot.consulta.controllers;

import com.chatbot.consulta.dtos.request.Auth.AuthRequestDto;
import com.chatbot.consulta.dtos.request.Auth.MedicoCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://0.0.0.0:8081/")
@RestController
@RequestMapping("consulta")
public class ConsultaController {

    @PostMapping("/")
    public ResponseEntity createConsulta(@Validated(MedicoCreate.class) @RequestBody AuthRequestDto authRequestDto){
        //TODO - Verifica a disponibilidade do medico e choque de horario e consulta com status de vencido e ja aproveita para deletar da base de dadosg
        //TODO - Cria consulta com status de pagamento pendente
        //TODO - retorna dados de pagamento
        return null;
    }
    @PutMapping("/date")
    public ResponseEntity updateConsultaData(@Validated(MedicoCreate.class) @RequestBody AuthRequestDto authRequestDto){
        //TODO - Verifica a disponibilidade do medico e choque de horario e consulta com status de vencido e ja aproveita para deletar da base de dados
        //TODO - atualiza a data se possivel
        //TODO - retorna dados da consulta
        return null;
    }
    @DeleteMapping("/")
    public ResponseEntity removeConsulta(@Validated(MedicoCreate.class) @RequestBody AuthRequestDto authRequestDto){
        //TODO - Verifica se o prazo de cancelamento ja passou (umas 24hrs antes), inclusive colocar esse prazo como atributo
        //TODO - remove a consulta se possivel
        //TODO - retorna dados da consulta e cancelamento
        return null;
    }

}
