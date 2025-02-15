package com.cybersoft.oshaneat.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtilHelper {

    @Value("${jwt.privateKey}")
    private String privateKey;  // Move the @Value annotation to a class field

    public String generateToken(String data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        String jws = Jwts.builder().subject(data).signWith(key).compact();
        return jws;
    }
    public boolean verifyToken(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jwts.parser().
                    setSigningKey(key).
                    build().
                    parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public String getDataFromToken(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            return Jwts.parser().
                    setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e){
            return "";
        }
    }
}
