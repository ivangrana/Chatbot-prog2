package com.chatbot.consulta.controllers;

import com.chatbot.consulta.dtos.request.consulta.CancelConsulta;
import com.chatbot.consulta.dtos.request.consulta.ConsultaCreate;
import com.chatbot.consulta.dtos.request.consulta.ConsultaRequestDto;
import com.chatbot.consulta.dtos.request.consulta.UpdateData;
import com.chatbot.consulta.enums.TipoAgendamento;
import com.chatbot.consulta.models.*;
import com.chatbot.consulta.services.AutenticacaoService;
import com.chatbot.consulta.services.ConsultaService;
import com.chatbot.consulta.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/")
    public ResponseEntity createConsulta(@RequestBody @Validated(ConsultaCreate.class) ConsultaRequestDto consultaRequest, @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token - ok
        Usuario usuario = consultaService.findUsuario(tokenService.decodeToken(tokenHeader).getId());

        //TODO - Verifica a disponibilidade do medico e choque de horario e consulta com status de vencido e ja aproveita para deletar da base de dados - ok
        consultaService.verificarAgendaMedico(consultaRequest.getIdMedico(), consultaRequest.getDataConsulta());  //ok
        consultaService.verificarConsultaPacienteByAgendamento(usuario.getId(), consultaRequest.getDataConsulta()); //ok

        // TODO - buscar medico e paciente - ok
        Medico medico = consultaService.getMedicoById(consultaRequest.getIdMedico()); //ok
        Paciente paciente = consultaService.getPacienteById(usuario.getId()); //ok

        //TODO - verificar especialidade médico - ok
        consultaService.verificarEspecialidadeMedico(medico, consultaRequest.getEspecialidade());

        //TODO - verificar se a data da consulta é de no mínimo 24hrs antes do horário atual - ok
        consultaService.verificarPrazoCriacao(consultaRequest.getDataConsulta());

        //TODO - Cria agendamento para a consulta - ok
        Agenda novoAgendamento = new Agenda(
                consultaRequest.getDataConsulta(),
                TipoAgendamento.CONSULTA,
                medico
        );

        //TODO - Cria consulta com status de pagamento pendente - ok
        Consulta novaConsulta = new Consulta(
                medico,
                paciente,
                consultaRequest.getEspecialidade(),
                novoAgendamento
        );
        //TODO - salva consulta no banco de dados e retorna dados de pagamento - ok
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.criarNovaConsulta(novaConsulta));
    }
    @PutMapping("/date")
    public ResponseEntity updateConsultaData(@Validated(UpdateData.class) ConsultaRequestDto consultaRequest, @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token - ok
        Usuario usuario = consultaService.findUsuario(tokenService.decodeToken(tokenHeader).getId());

        //TODO - verificar se existe consulta com o id fornecido e com o paciente solicitante
        consultaService.IsPacienteFromConsulta(consultaRequest.getIdConsulta(), usuario.getId());

        //TODO - Verifica a disponibilidade do medico e choque de horario e consulta com status de vencido e ja aproveita para deletar da base de dados - ok
        consultaService.verificarAgendaMedico(consultaRequest.getIdMedico(), consultaRequest.getDataConsulta());  //ok
        consultaService.verificarConsultaPacienteByAgendamento(usuario.getId(), consultaRequest.getDataConsulta()); //ok

        //TODO - buscar consulta pelo id do paciente e id da consulta - ok
       Consulta consulta = consultaService.findConsulta(usuario.getId(), consultaRequest.getIdConsulta());

        //TODO - verificar se a data da consulta é de no mínimo 24hrs antes do horário atual - ok
        consultaService.verificarPrazoCriacao(consultaRequest.getDataConsulta());

        //TODO - atualiza a data se possivel e retorna dados da consulta - ok
        consulta.getAgendamento().setDataInicial(consultaRequest.getDataConsulta());
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.atualizarDataConsulta(consulta));
    }
    @DeleteMapping("/")
    public ResponseEntity removeConsulta(@Validated(CancelConsulta.class) ConsultaRequestDto consultaRequest, @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token - ok
        Usuario usuario = consultaService.findUsuario(tokenService.decodeToken(tokenHeader).getId());

        //TODO - verificar se existe consulta com o id fornecido e com o paciente solicitante - ok
        consultaService.IsPacienteFromConsulta(consultaRequest.getIdConsulta(), usuario.getId());

        //TODO - buscar consulta pelo id do paciente e id da consulta - ok
        Consulta consulta = consultaService.findConsulta(usuario.getId(), consultaRequest.getIdConsulta());

        //TODO - Verifica se o prazo de cancelamento ja passou (umas 24hrs antes) - ok
        consultaService.verificarPrazoCancelamento(consulta.getAgendamento().getDataInicial());

        //TODO - remove a consulta se possivel e  retorna dados da consulta e cancelamento - ok
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.deleteConsulta(consulta));
    }

}
