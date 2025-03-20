package com.chatbot.consulta.controllers;

import com.chatbot.consulta.dtos.request.autenticacao.AutenticacaoRequestDto;
import com.chatbot.consulta.dtos.request.autenticacao.MedicoCreate;
import com.chatbot.consulta.dtos.request.consulta.ConsultaCreate;
import com.chatbot.consulta.dtos.request.consulta.ConsultaRequestDto;
import com.chatbot.consulta.models.Usuario;
import com.chatbot.consulta.services.AutenticacaoService;
import com.chatbot.consulta.services.ConsultaService;
import com.chatbot.consulta.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//CrossOrigin(origins = "http://0.0.0.0:8081/")
@RestController
@RequestMapping("consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/")
    public ResponseEntity createConsulta(@RequestBody @Validated(ConsultaCreate.class) ConsultaRequestDto consultaRequest, @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token - ok
        Usuario usuario = autenticacaoService.findUsuario(tokenService.decodeToken(tokenHeader).getId());
        //TODO - Verifica a disponibilidade do medico e choque de horario e consulta com status de vencido e ja aproveita para deletar da base de dados
        consultaService.verificarAgendaMedico(consultaRequest.getIdMedico(), consultaRequest.getDataConsulta());
        consultaService.verificarAgendaPaciente(usuario.getId(), consultaRequest.getDataConsulta());
        //TODO - verificar especialidade médico
        consultaService.verificarEspecialidadeMedico(consultaRequest.getIdMedico(), consultaRequest.getEspecialidade());
        //TODO - Cria consulta com status de pagamento pendente
        //TODO - retorna dados de pagamento
        return null;
    }
    @PutMapping("/date")
    public ResponseEntity updateConsultaData(@Validated(MedicoCreate.class) @RequestBody AutenticacaoRequestDto authRequestDto){
        //TODO - Verifica a disponibilidade do medico e choque de horario e consulta com status de vencido e ja aproveita para deletar da base de dados
        //TODO - atualiza a data se possivel
        //TODO - retorna dados da consulta
        return null;
    }
    @DeleteMapping("/")
    public ResponseEntity removeConsulta(@Validated(MedicoCreate.class) @RequestBody AutenticacaoRequestDto authRequestDto){
        //TODO - Verifica se o prazo de cancelamento ja passou (umas 24hrs antes), inclusive colocar esse prazo como atributo
        //TODO - remove a consulta se possivel
        //TODO - retorna dados da consulta e cancelamento
        return null;
    }

}
