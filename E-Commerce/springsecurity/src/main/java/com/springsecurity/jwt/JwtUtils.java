package com.springsecurity.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    // Secret Key -  custom to user

    @Value("${spring.app.secret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // Extract JWt token from Header

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        // bearerToken => Bearer dfghjkhgfdrtylkjhgfdfghj
        logger.debug("Authorization Header : {}", bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // extract the Token

            System.out.println( " Token " + bearerToken);
            return bearerToken.substring(7);
        }
        System.out.println("Bearer token is not there.");
        return null;
    }

    // Generate Token from Username

    public String generateTokenFromUsername(UserDetails userDetails) {
        String username = userDetails.getUsername();

        //  Jwt
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();

    }

    public String getUsernameFromJwtToken(String token) {

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    public boolean validateJwtToken(String authToken) {


        try {
            System.out.println("Validate method ");

            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(authToken);
            System.out.println( " valid JWT ");
            return true;
        } catch (MalformedJwtException e) {
            logger.error("invalid JWT Token :{}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Jwt token is expired :{}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("unsupported JWT Token :{}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Jwt claims String is empty :{}", e.getMessage());
        }

        System.out.println("Invalid JWT");
        return false;
    }


}
