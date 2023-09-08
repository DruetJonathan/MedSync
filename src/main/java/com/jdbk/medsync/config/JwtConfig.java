package com.jdbk.medsync.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    public int expireAt = 86400; // 1 Day
    private String secretString = "Yabadabadoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo";

    // Convertissez la chaîne en tableau de bytes
    private byte[] secretBytes = secretString.getBytes(StandardCharsets.UTF_8);

    // Créez une instance de SecretKey à partir du tableau de bytes
    public SecretKey secretKey = new SecretKeySpec(secretBytes, "HmacSHA512");
}
