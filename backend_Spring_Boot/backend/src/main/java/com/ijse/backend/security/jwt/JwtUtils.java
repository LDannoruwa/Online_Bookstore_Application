package com.ijse.backend.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    //Logging : put a note when an error is found in the system
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    //define secret key
    @Value("${backendapp.jwt.secret}") //get value of variable backendapp.jwt.secret in /resources/application.properties
    private String jwtSecret;

    //define expiration time of secret key
    @Value("${backendapp.jwt.jwtExpiration}")
    private int jwtExpirationMs;

    //--------------- [Start: generate jwt token]--------------------------------------------
    public String generateJwtToken(Authentication authentication){

        //get principal data of user from UserDetails object in springframework.security.core
        UserDetails  userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject((userPrincipal.getUsername()))
            .setIssuedAt(new Date())

            //expiration time = current time + jwtExpirationMs
            .setExpiration(new Date((new Date()).getTime()+jwtExpirationMs))

            //key() has been defined below
            .signWith(key(), SignatureAlgorithm.HS256)

            //convert token as a string
            .compact();
    }

    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)); //cryptophic function
    }
    //--------------- [End: generate jwt token]--------------------------------------------


    //--------------- [Start: jwt token validation]----------------------------------------
    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT Token : {}", e.getMessage());
        }catch (ExpiredJwtException e) {
            logger.error("JWT Token is Expired : {}", e.getMessage());
        }catch (UnsupportedJwtException e) {
            logger.error("Unsuppoeted JWT : {}", e.getMessage());
        }catch (IllegalArgumentException e) {
            logger.error("JWT payload is empty : {}", e.getMessage());
        }
        return false;
    }
    //--------------- [End: jwt token validation]------------------------------------------
    
    
    //--------------- [Start: Extract usename from JWT token]----------------------------------------
    public String getUsernameFromJwtToken(String authToken){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken).getBody().getSubject();
    }
    //--------------- [End: Extract usename from JWT token]----------------------------------------
}
