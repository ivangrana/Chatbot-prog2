package com.chatbot.consulta.controllers;

import com.chatbot.consulta.dtos.request.autenticacao.*;
import com.chatbot.consulta.dtos.response.LoginResponseDto;
import com.chatbot.consulta.dtos.response.BaseResponseDto;
import com.chatbot.consulta.enums.TipoUsuario;
import com.chatbot.consulta.models.Especialidade;
import com.chatbot.consulta.models.Medico;
import com.chatbot.consulta.models.Paciente;
import com.chatbot.consulta.models.Usuario;
import com.chatbot.consulta.services.AutenticacaoService;
import com.chatbot.consulta.services.PagamentoService;
import com.chatbot.consulta.services.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;

//@CrossOrigin(origins = "http://0.0.0.0:8081/")
@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AutenticacaoService autenticacaoService;
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/helloWorld")
    public ResponseEntity<BaseResponseDto> helloWorld(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BaseResponseDto("Hello World!"));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDto> login(@RequestBody @Validated(LoginRequest.class) AutenticacaoRequestDto authRequestDto){
        // 1. Autenticação com email e senha fornecidos
        var usernamePassword = new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        // 2. Define a autenticação no contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(auth);
        // 5. Retorna o token gerado em um objeto de resposta
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BaseResponseDto(new LoginResponseDto(token)));
    }

    @PostMapping("/medico")
    public ResponseEntity createMedico(@Validated(MedicoCreate.class) @RequestBody AutenticacaoRequestDto authRequestDto){

        //TODO - Verificar se usuario existe - ok
        autenticacaoService.emailJaCadastrado(authRequestDto.getEmail());

        //TODO - Buscar especialidades - ok
        List<Especialidade> especialidades = autenticacaoService.buscarEspecialidades(authRequestDto.getIdEspecialidades());

        //TODO - Criar novo usuario - ok
        return ResponseEntity.status(HttpStatus.CREATED).body(
                autenticacaoService.criarNovoMedico(
                        new Medico(authRequestDto.getName(),
                        authRequestDto.getEmail(),
                        passwordEncoder.encode(authRequestDto.getPassword()),
                        authRequestDto.getIdade(),
                        authRequestDto.getCrm(), especialidades)
                )
        );
    }

    @PostMapping("/paciente")
    public ResponseEntity createPaciente(@Validated(PacienteCreate.class) @RequestBody AutenticacaoRequestDto authRequestDto){

        //TODO - Verificar se usuario existe - ok
        autenticacaoService.emailJaCadastrado(authRequestDto.getEmail());

        //TODO - Criar novo paciente - ok
        return ResponseEntity.status(HttpStatus.CREATED).body(
                autenticacaoService.criarNovoPaciente(
                        new Paciente(authRequestDto.getName(),
                                authRequestDto.getEmail(),
                                passwordEncoder.encode(authRequestDto.getPassword()),
                                authRequestDto.getIdade(),
                                authRequestDto.getPlanoSaude()
                        )
                )
        );
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteUser(@Validated(LoginRequest.class) AutenticacaoRequestDto authRequestDto,
                                     @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token - ok
        Usuario usuario = autenticacaoService.findUsuario(tokenService.decodeToken(tokenHeader).getId());

        //TODO - verificar pendencias de pagamentos - ok
        pagamentoService.existePendencias(usuario.getId());

        //TODO - deletar primeiro tabela de tipo de usuario e depois usuario - ok
        return ResponseEntity.status(HttpStatus.OK).body(autenticacaoService.deleteCascadeUsuario(usuario));
    }

    @PutMapping("/medico")
    public ResponseEntity<BaseResponseDto> createMedicoUsuarioExistente(@Validated(LoginRequest.class) AutenticacaoRequestDto authRequestDto){
        //TODO - decodificar token - ok
        Usuario usuario = autenticacaoService.findUsuario(authRequestDto.getIdUsuario());

        //TODO - verificar o tipo de usuario - ok
        usuario.getTipoUsuario().isMedico();

        //TODO - adicionar valor - ok
        usuario.setTipoUsuario(TipoUsuario.MEDICO_PACIENTE);

        //TODO - Buscar especialidades - ok
        List<Especialidade> especialidades = autenticacaoService.buscarEspecialidades(authRequestDto.getIdEspecialidades());

        //TODO - Criar medico com usuario antigo - ok
        return ResponseEntity.status(HttpStatus.OK).body(autenticacaoService.criarMedicoUserAntigo(
                new Medico(usuario, authRequestDto.getCrm(), especialidades)
            )
        );
    }

    @PutMapping("/paciente")
    public ResponseEntity<BaseResponseDto> createPacienteUsuarioExistente(@Validated(LoginRequest.class) AutenticacaoRequestDto authRequestDto,
                                                                       @RequestHeader("Authorization") String tokenHeader){
        //TODO - decodificar token - ok
        Usuario usuario = autenticacaoService.findUsuario(tokenService.decodeToken(tokenHeader).getId());

        //TODO - verificar o tipo de usuario - ok
        usuario.getTipoUsuario().isPaciente();

        //TODO - adicionar valor - ok
        usuario.setTipoUsuario(TipoUsuario.MEDICO_PACIENTE);

        //TODO - Criar paciente com usuario antigo - ok
        return ResponseEntity.status(HttpStatus.OK).body(autenticacaoService.criarPacienteUserAntigo(
                        new Paciente(usuario, authRequestDto.getPlanoSaude())
                )
        );
    }
}
