package com.shopverse.apigateway.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Date;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final PrivateKey privateKey;



    public AuthController(@Value("file:/app/keys/private.pem") Resource keyResource) throws Exception{
        String privateKeyContent = new String(keyResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8)
                .replaceAll("-----BEGIN (.*?)-----", "")
                .replaceAll("-----END (.*?)-----", "")
                .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        this.privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    @PostMapping("/token")
    public Map<String,String> getToken(@RequestParam String username){
        Instant now = Instant.now();
        String jwt = Jwts.builder()
                .subject(username)
                .claim("roles", List.of("USER"))
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(3600)))
                .signWith(privateKey,Jwts.SIG.RS256)
                .compact();

        return Map.of("Token",jwt);
    }
}
