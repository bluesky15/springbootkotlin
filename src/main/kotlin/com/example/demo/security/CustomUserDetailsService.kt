package com.example.demo.security

import com.example.demo.model.Role
import com.example.demo.model.UserEntity
import com.example.demo.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class CustomUserDetailsService(val repository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        username?.let {
            val user: UserEntity = repository.findByUsername(username)
                .orElseThrow({ UsernameNotFoundException("Username not found") })
            return User(user.username, user.password, mapRolesToAuthorities(user.role))
        }
        return null
    }

    private fun mapRolesToAuthorities(roles: List<Role?>): MutableCollection<GrantedAuthority?> {
        return roles.stream()
            .map<SimpleGrantedAuthority?> { role: Role? -> SimpleGrantedAuthority(role?.name) }
            .collect(Collectors.toList())
    }
}