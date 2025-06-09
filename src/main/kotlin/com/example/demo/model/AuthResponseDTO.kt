package com.example.demo.model

data class AuthResponseDTO(
    val accessToken: String = "",
    val tokenType: String = "Bearer "
)
