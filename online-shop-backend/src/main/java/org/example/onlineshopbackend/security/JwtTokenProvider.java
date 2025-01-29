package org.example.onlineshopbackend.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.onlineshopbackend.exception.JwtTokenExpirationException;
import org.example.onlineshopbackend.exception.JwtTokenInvalidateException;
import org.example.onlineshopbackend.model.utils.ApplicationTokenProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final ApplicationTokenProperties tokenProperties;
    private final SecretKey key = Jwts.SIG.HS512.key().build();

    public enum Type {
        Access, Refresh
    }

    public Authentication parse(String token, Type checkType) {

        try {
            if (StringUtils.hasLength(token)) {
                var jwt = Jwts.parser()
                        .verifyWith(key)
                        .requireIssuer(tokenProperties.getIssuer())
                        .build()
                        .parseSignedClaims(token);

                var type = jwt.getPayload().get("type");

                if (null == type || !type.equals(checkType.name())) {
                    throw new JwtTokenInvalidateException("Invalid token type", null);
                }

                var username = jwt.getPayload().getSubject();
                var authorities = Arrays.stream(jwt.getPayload().get("role").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                System.out.println(authorities);
                return UsernamePasswordAuthenticationToken.authenticated(username, null, authorities);
            }
        }catch (ExpiredJwtException e) {
            throw new JwtTokenExpirationException("Access token is expired. Please refresh token again.", e);
        }catch (JwtException e) {
            throw new JwtTokenInvalidateException(e.getMessage(), e);
        }
        return null;
    }

    public String generateToken(Authentication authentication, Type type) {

        Date issuedAt = new Date();

        var calendar = Calendar.getInstance();
        calendar.setTime(issuedAt);
        calendar.add(Calendar.MINUTE, type == Type.Access ? tokenProperties.getLife().getAccess()
                : tokenProperties.getLife().getRefresh());

        Date expiresAt = calendar.getTime();
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(authentication.getName())
                .issuer(tokenProperties.getIssuer())
                .issuedAt(issuedAt)
                .expiration(expiresAt)
                .signWith(key)
                .claim("type", type)
                .claim("role", authorities)
                .compact();
    }


}
