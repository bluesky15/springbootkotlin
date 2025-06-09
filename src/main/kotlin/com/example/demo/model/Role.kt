package com.example.demo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "role")
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int=0,
    val name: String=""
)

