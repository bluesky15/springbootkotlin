package com.example.demo.controller

import com.example.demo.model.AuthResponseDTO
import com.example.demo.model.RegisterDto
import com.example.demo.model.Role
import com.example.demo.model.UserEntity
import com.example.demo.repository.RoleRepository
import com.example.demo.repository.UserRepository
import com.example.demo.security.JWTGenerator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Collections


@RestController
@RequestMapping("/api/auth")
class AuthController(
    val authenticationManager: AuthenticationManager,
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val passwordEncoder: PasswordEncoder,
    val jwtGenerator: JWTGenerator
) {

    @PostMapping("login")
    fun login(@RequestBody loginDto: RegisterDto): ResponseEntity<AuthResponseDTO> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginDto.username,
                loginDto.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val token: String? = jwtGenerator.generateToken(authentication)
        return ResponseEntity<AuthResponseDTO>(AuthResponseDTO(token ?: ""), HttpStatus.OK)
    }

    @PostMapping("register")
    fun registerUser(@RequestBody registerDto: RegisterDto): ResponseEntity<String> {
        if (userRepository.existsByUsername(registerDto.username)) {
            return ResponseEntity("User Taken", HttpStatus.BAD_REQUEST)
        }

        val user = UserEntity()
        user.username = registerDto.username
        user.password = passwordEncoder.encode(registerDto.password)

        val roles: Role = roleRepository.findByName("USER").get()
        user.role = Collections.singletonList(roles)

        userRepository.save<UserEntity?>(user)

        return ResponseEntity<String>("User registered success!", HttpStatus.OK)

    }
}
