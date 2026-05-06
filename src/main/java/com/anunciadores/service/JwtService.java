package com.anunciadores.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    // ⚠️ Cambia esto por una clave secreta larga en producción
    private static final String SECRET = "adcIglesiaDigitalSecretKey2026!!";

    // Token válido por 8 horas
    private static final long EXPIRATION_MS = 8 * 60 * 60 * 1000L;

    // Genera token JWT
    public String generarToken(String cedula, String rol, String nombre) {
        return Jwts.builder()
                .setSubject(cedula)
                .claim("rol",    rol)
                .claim("nombre", nombre)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
    }

    // Extrae la cédula del token
    public String getCedulaDesdeToken(String token) {
        return getClaims(token).getSubject();
    }

    // Valida que el token sea correcto y no haya expirado
    public boolean esTokenValido(String token) {
        try {
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extrae todos los claims
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
