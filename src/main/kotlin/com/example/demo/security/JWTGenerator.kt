package com.example.demo.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JWTGenerator {

    companion object {
        private val key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS512)
    }

    fun generateToken(authentication: Authentication): String {
        val username = authentication.name
        val currentDate = Date()
        val expireDate = Date(currentDate.time + 70000)

        val token = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(currentDate)
            .setExpiration(expireDate)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()

        println("New token:")
        println(token)

        return token
    }

    fun getUsernameFromJWT(token: String): String {
        val claims: Claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

        return claims.subject
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            throw AuthenticationCredentialsNotFoundException("JWT was expired or incorrect", ex.fillInStackTrace())
        }
    }
}
