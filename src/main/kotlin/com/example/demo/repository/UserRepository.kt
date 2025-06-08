package com.example.demo.repository

import com.example.demo.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findByUsername(name: String): Optional<UserEntity>
    fun existsByUsername(name: String): Boolean
}