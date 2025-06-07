package com.example.demo

//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController

//@RestController
//@RequestMapping("/auth")
//class AuthController {
//
//    @PostMapping("/login")
//    fun login(@RequestBody user: User): ResponseEntity<String> {
//        // Dummy check
//        if (user.username == "admin" && user.password == "password") {
//            val token = JwtUtil.generateToken(user.username)
//            return ResponseEntity.ok(token)
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials")
//    }
//}
