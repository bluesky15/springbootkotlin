package com.example.demo

import com.example.demo.model.Student
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(private val studentService: StudentService) {
    @PostMapping("/create")
    fun createStudent(@RequestBody student: Student): ResponseEntity<Student> {
        val savedStudent = studentService.saveStudent(student)
        return ResponseEntity.ok(savedStudent)
    }

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello from Spring Boot with Kotlin!"
    }

    @GetMapping("/students")
    fun getAllStudents(): List<Student> {
        return studentService.getAllStudents()
    }


    @GetMapping("/student/{id}")
    fun getStudentById(@PathVariable id: Int): ResponseEntity<Student> {
        val student = studentService.getStudentById(id)
        return if (student != null) {
            ResponseEntity.ok(student)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/student/by-name")
    fun getStudentById(@RequestParam name: String): ResponseEntity<List<Student>> {
        val list = studentService.getStudentByName(name)
        return if (list.isNotEmpty()) {
            ResponseEntity.ok(list)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/student/{id}")
    fun deleteStudent(@PathVariable id: Int){
        studentService.deleteStudent(id)
    }

}