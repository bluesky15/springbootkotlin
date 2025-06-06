package com.example.demo

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date

object JwtUtil {
    private const val SECRET_KEY = "secret"

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

    fun validateToken(token: String, username: String): Boolean {
        val claims = getClaims(token)
        return claims.subject == username && !isTokenExpired(claims)
    }

    private fun getClaims(token: String): Claims =
        Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).body

    private fun isTokenExpired(claims: Claims): Boolean =
        claims.expiration.before(Date())
}
