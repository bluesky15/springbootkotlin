package com.example.demo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Student(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0, val name: String = "", val address: String = ""
)