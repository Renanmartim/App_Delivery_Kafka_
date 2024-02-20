package com.Client.Client.Config;

import com.Client.Client.Dto.UserLogin;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserLogin user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        if (token == null || token.isEmpty()) {
            // Handle case where token is null or empty
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT != null) {
                return decodedJWT.getSubject();
            } else {
                // Handle case where decodedJWT is null
                return null;
            }
        } catch (JWTVerificationException exception) {
            // Log the exception to aid in troubleshooting
            System.err.println("JWT Verification Exception: " + exception.getMessage());
            exception.printStackTrace();
            // Handle case where token verification fails
            return null;
        }
    }


    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
