package com.example.demo.repository

import com.example.demo.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository : JpaRepository<Role, Int> {
    fun findByName(role: String): Optional<Role>
}