package com.chatbot.consulta.services;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chatbot.consulta.models.User;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
@Transactional
public class TokenService {
    @Value("${token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            ArrayList<String> roles = new ArrayList<>();
            for(var role: user.getAuthorities()){roles.add(role.getAuthority());}

            Algorithm algorithm = Algorithm.HMAC256(secret);
            //token
            return JWT.create()
                    .withIssuer("chatbot")
                    .withSubject(user.getEmail())
                    .withClaim("id_user", user.getId())
                    .withClaim("roles", roles)
                    .withExpiresAt(genAccessExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token", exception);//tratar essa exceção depois
        }
    }
    public Long validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String userId = JWT.require(algorithm)
                    .withIssuer("chatbot")
                    .build()
                    .verify(token)
                    .getSubject();

            return Long.parseLong(userId);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido ou expirado", exception); // Lança exceção adequada
        } catch (NumberFormatException e) {
            throw new RuntimeException("Token inválido: ID do usuário não é um número", e);
        }
    }
    public User decodeToken(String token){
        try{
            token = token.replace("Bearer ", "");
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.decode(token);
            User user = new User();
            user.setId(jwt.getClaim("id_user").asLong());
            return user;
        } catch (JWTDecodeException exception){
            throw new RuntimeException("Erro ao decodificar token", exception);
        }
    }
    private Instant genAccessExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
