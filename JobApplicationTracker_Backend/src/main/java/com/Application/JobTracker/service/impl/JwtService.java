package com.Application.JobTracker.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String secretKey="TmV3U2VjcmV0S2V5Rm9ySldUU29uZ2luZ21hc2sgZmF2b3I=";
//    private String generateSecretKey(){
//        try{
//        KeyGenerator keyGen= KeyGenerator.getInstance("HmacSHA256");
//        SecretKey key= keyGen.generateKey();
//        return Base64.getEncoder().encodeToString(key.getEncoded());
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("error in generating the key"+e);
//        }
//    }

    public String generateToken(String email) {
        Map<String,Object> claims= new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*3))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getKey(){
        byte[] byteStream= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(byteStream);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    private Date extractExpiry(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    private Boolean isExpired(String token){
        return (extractExpiry(token).before(new Date()));
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        return (userDetails.getUsername().equals(extractUsername(token)) && !isExpired(token));
    }

}
